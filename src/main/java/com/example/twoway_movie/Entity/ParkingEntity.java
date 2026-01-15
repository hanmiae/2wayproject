package com.example.twoway_movie.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parking")

public class ParkingEntity {
    @Id
    @Column
    String carnum;
    @Column
    String inpark;
    @Column
    String outpark;

}
