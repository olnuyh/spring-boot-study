package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String price;

    public void patch(Coffee coffee){
        if(coffee.name != null)
            this.name = coffee.name;
        if(coffee.price != null)
            this.price = coffee.price;
    }
}
