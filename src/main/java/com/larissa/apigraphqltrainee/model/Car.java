package com.larissa.apigraphqltrainee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "CARROS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOME")
    private String name;
    @Column(name = "PLACA")
    private String licencePlate;
    @Column(name = "DATA_FABRICACAO")
    private String manufactureDate;
    @Version
    @Column(name = "VERSAO")
    private Long version;


}
