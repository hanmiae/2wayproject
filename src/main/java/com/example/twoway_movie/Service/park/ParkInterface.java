package com.example.twoway_movie.Service.park;

public interface ParkInterface {
    String getCarnum();
    String getInpark();
    String getOutpark();
    int getParktime(); //주차시간
    String getParktimetext();
    int getParkpay();
}
