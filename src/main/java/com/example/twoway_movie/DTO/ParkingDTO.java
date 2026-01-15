package com.example.twoway_movie.DTO;

import com.example.twoway_movie.Entity.ParkingEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ParkingDTO {
    String carnum;
    String inpark;
    String outpark;

    public ParkingEntity toentity() {
        ParkingEntity pe = new ParkingEntity();
        pe.setCarnum(carnum);
        pe.setInpark(inpark);
        pe.setOutpark(outpark);
        return pe;
    }
}
