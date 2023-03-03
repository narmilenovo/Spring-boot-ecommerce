package com.example.springBootEcommerce.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

}












