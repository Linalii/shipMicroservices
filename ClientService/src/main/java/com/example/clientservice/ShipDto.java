package com.example.clientservice;


import lombok.Data;


@Data
public class ShipDto {

    private Long id;
    private String name;
    private Long number;
    private String owner;

}
