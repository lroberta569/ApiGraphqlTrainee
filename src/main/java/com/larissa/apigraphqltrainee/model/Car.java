package com.larissa.apigraphqltrainee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "licencePlate")
    private String licencePlate;
    @Column(name = "manufactureDate")
    private String manufactureDate;
    @Version
    @Column(name = "version")
    private Long version;


}
