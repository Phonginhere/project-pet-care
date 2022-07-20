package com.example.petcareapp.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
//@Getter
//@Setter
public class Product implements Serializable {
    private String name;
    private int year;
    //getter, setter cóntructoctor
    //Lombox can be used only in Java Spring !


    public Product(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
