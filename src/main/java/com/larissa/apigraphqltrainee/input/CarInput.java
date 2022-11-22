package com.larissa.apigraphqltrainee.input;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class CarInput {

    private Long id;
    private String name;
    private String licencePlate;
    private String manufactureDate;
    private Long version;
}