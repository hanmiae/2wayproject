package com.example.twoway_movie.Repository;

import com.example.twoway_movie.Entity.ParkingEntity;
import com.example.twoway_movie.Service.park.ParkInterface;
import com.example.twoway_movie.Entity.ParkingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingRipository extends JpaRepository<ParkingEntity,String> {

    /*@Query(value = """
    select carnum,inpark,outpark,
    round((to_date(outpark,'HH24:MI')-to_date(inpark,'HH24:MI'))*24*60,0) as parktime
    from parking
    """,nativeQuery = true)
    List<ParkInterface> allout2();

    */



    /*
    @Query(value = """
    /* 분 -> 시간 / 주차요금 계산8
    SELECT carnum,
           inpark,
           outpark,
           parktime,

           CASE
               WHEN parktime < 60 THEN parktime || '분'
               ELSE FLOOR(parktime / 60) || '시간 ' || MOD(parktime, 60) || '분'
           END AS parktimetext,

           /* 주차요금
           CASE
               WHEN parktime <= 30
                   THEN 1000
               ELSE
                   1000 + CEIL((parktime - 30) / 10) * 500
           END AS parkpay

    FROM (
        SELECT carnum,
               inpark,
               outpark,

               /* 주차시간(분)
               FLOOR(
                   (TO_DATE(outpark,'HH24:MI') - TO_DATE(inpark,'HH24:MI')) * 1440
               ) AS parktime

        FROM parking

    """, nativeQuery = true)
    */

     @Query(value ="""
        SELECT
        carnum,
        inpark,
        outpark,
        ROUND((TO_DATE(outpark,'HH24:MI') - TO_DATE(inpark,'HH24:MI')) * 24 * 60, 0) AS parktime,
        CASE
            WHEN ROUND((TO_DATE(outpark,'HH24:MI') - TO_DATE(inpark,'HH24:MI')) * 24 * 60, 0) <= 30
                THEN 1000
            ELSE
                1000
                + CEIL(
                    (ROUND((TO_DATE(outpark,'HH24:MI') - TO_DATE(inpark,'HH24:MI')) * 24 * 60, 0) - 30) / 10
                  ) * 500
        END AS parkpay
        FROM parking
    """,nativeQuery = true)
    Page<ParkInterface> alloutpage2(Pageable pageable);
}
