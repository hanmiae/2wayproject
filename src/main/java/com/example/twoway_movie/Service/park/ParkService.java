package com.example.twoway_movie.Service.park;

import com.example.twoway_movie.Entity.ParkingEntity;
import org.springframework.data.domain.Page;


public interface ParkService {
    void insertsave(ParkingEntity pe);


    Page<ParkInterface> alloutpage(int page);
}
