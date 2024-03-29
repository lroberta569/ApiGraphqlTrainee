package com.larissa.apigraphqltrainee.controller;

import com.larissa.apigraphqltrainee.input.CarInput;
import com.larissa.apigraphqltrainee.model.Base64URL;
import com.larissa.apigraphqltrainee.model.Car;
import com.larissa.apigraphqltrainee.service.AwsService;
import com.larissa.apigraphqltrainee.service.CarService;
import com.larissa.apigraphqltrainee.service.ImageService;
import com.larissa.apigraphqltrainee.utils.ConvertToBase64;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Controller
@CrossOrigin("*")
public class CarController {

        @Autowired
        private CarService carService;
        @Autowired
        private AwsService awsService;

        @QueryMapping
        public List<Car> findAllCar(){
            return carService.findAllCar();
        }

        @QueryMapping
        public Car findCarById(@Argument Long id) throws IOException {
            Car car  = carService.findCarById(id);
            String dataMimeType = ImageService.dataMimeType(car.getUrlImageCar());
            car.setImageCarBase64(dataMimeType + "," + carService.fetcCarImageOnAWSS3(car.getUrlImageCar()));
            return car;
        }

        @MutationMapping
        public Car createCar(@Argument CarInput car) throws IOException {
            return carService.createCar(car);
        }


        @MutationMapping
        public String deleteCar(@Argument Long id){
            return carService.deleteCar(id);

        }

        @MutationMapping
        public Car updateCar(@Argument CarInput car) throws IOException {
            return carService.updateCar(car);
        }


        @QueryMapping
        public String generatePdfABase64(@Argument Long id) throws Exception {
            return carService.generatePdfABase64(id);
        }

    }

