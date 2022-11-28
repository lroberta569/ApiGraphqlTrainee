package com.larissa.apigraphqltrainee.model;

import com.larissa.apigraphqltrainee.service.ImageService;
import com.larissa.apigraphqltrainee.utils.FileType;
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
    private String name;
    private String licensePlate;
    private String manufactureDate;
    @Version
    private Long version;
    private transient String imageCarBase64;
    private String urlImageCar;

    public void setCarImageName(){
        if(getId() == null){
            throw new IllegalStateException("Primeiro grave um registro");
        }

        ImageService imageService = ImageService.get(imageCarBase64);
        String entension = FileType.of(imageService.getMimeType()).getExtensao();

        this.urlImageCar = String.format("%04d-car.%s", getId(), entension);

    }

}
