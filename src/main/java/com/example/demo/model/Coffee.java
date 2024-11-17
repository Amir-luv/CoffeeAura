package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "menu")
@Data

public class Coffee {
    @Id
    private int id;
    private String name;
    private int price;

}