package com.example.demo.currency.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long currencyId;

    @Column
    private String currencyCode;

    @Column
    private String chineseName;
}
