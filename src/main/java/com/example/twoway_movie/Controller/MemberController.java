package com.example.twoway_movie.Controller;


import com.example.twoway_movie.DTO.MemberDTO;
import com.example.twoway_movie.Entity.MemberEntity;
import com.example.twoway_movie.Service.Member.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/member_inputgo")
    public String member1(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/memberinput";
    }

    @PostMapping("/memberinputsave")
    public String member2(@Valid @ModelAttribute("memberDTO") MemberDTO memberDTO,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(e ->
                    log.info("VALIDATION ERROR field={} msg={} value={}",
                            e.getField(), e.getDefaultMessage(), e.getRejectedValue())
            );
            return "member/memberinput";
        }

        log.info("VALIDATION OK -> 저장 시도");
        memberService.memberinsert(memberDTO);
        return "redirect:/login";
    }





    @GetMapping("/admin/item")
    public String adminItem() {
        return "admin/item";
    }



    //엔티티 클래스 자료출력  ..페이징 처리
    @GetMapping("/member_outgo")
    public String mm3(Model mo, @RequestParam(required = false,defaultValue = "0",value = "page")int page)
    {
        // 페이징 처리
        Page<MemberEntity> listPage = memberService.entitypage(page);
        int totalPage = listPage.getTotalPages();//전체 데이타수를 3으로 나눈 페이지수 를 올림
        int nowpage = listPage.getPageable().getPageNumber() + 1;//현재페이지//
        mo.addAttribute("nowpage", nowpage);
        mo.addAttribute("list", listPage.getContent());
        mo.addAttribute("totalPage", totalPage);
        return "/member/memberOut";
    }


@GetMapping("/map_go")
    public String mm6(){

        return "/map";
}

@GetMapping("/member_searchgo")
    public String mm7(){
        return "/member/membersearch";
}

    @PostMapping("/membersearchsave")
    public String sa10(@RequestParam("mkey")String mkey,
                       @RequestParam("mvalue")String mvalue,
                       Model mo){
        List<MemberEntity> list=memberService.search(mkey,mvalue);
        mo.addAttribute("list",list);
        return "/member/membersearchout";
    }

}