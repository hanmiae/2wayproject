package com.example.twoway_movie.Service.park;

import com.example.twoway_movie.Entity.ParkingEntity;
import com.example.twoway_movie.Repository.ParkingRipository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service

public class ParkServiceimp implements ParkService{
    @Autowired
    ParkingRipository parkingRipository;

    @Override
    public void insertsave(ParkingEntity pe) {
        parkingRipository.save(pe);
    }


    @Override
    public Page<ParkInterface> alloutpage(int page) {
        Page<ParkInterface> list=parkingRipository.alloutpage2(PageRequest.of(page,3));
        return list;
    }

}
