package com.larissa.apigraphqltrainee.input;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarInput {

    private Long id;
    private String name;
    private String licencePlate;
    private String manufactureDate;
    private Long version;
}