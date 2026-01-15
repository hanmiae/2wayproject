package com.example.twoway_movie.Controller;

import com.example.twoway_movie.DTO.ParkingDTO;
import com.example.twoway_movie.Entity.ParkingEntity;
import com.example.twoway_movie.Service.park.ParkInterface;
import com.example.twoway_movie.Service.park.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ParkingController {
    @Autowired
    ParkService parkService;

    @GetMapping("/parkgo")
    public String pk1(){
        return "/parking/parkinput";
    }

    @PostMapping("/parksave")
    public String pk2(ParkingDTO dto){
        ParkingEntity pe=dto.toentity();
        parkService.insertsave(pe);
        return "redirect:/";
    }
    /*
    @GetMapping("/parkout")
    public String pk3(Model model,@RequestParam(required = false, defaultValue = "0", value = "page")int page ){
        List<ParkInterface> list=parkService.allout();
        model.addAttribute("list",list);
        return "/parking/parkout";
    }
    */

    @GetMapping("/parkout")
    public String pk4(Model model,@RequestParam(name="page", defaultValue = "0")int page){
       // List<ParkInterface> list=parkService.allout();
        Page<ParkInterface> listpage=parkService.alloutpage(page);
        model.addAttribute("nowPage", listpage.getNumber() + 1); // ✅ HTML에서도 nowPage 사용
        model.addAttribute("list", listpage.getContent());
        model.addAttribute("totalPage", listpage.getTotalPages());
        return "/parking/parkout";
    }



}
