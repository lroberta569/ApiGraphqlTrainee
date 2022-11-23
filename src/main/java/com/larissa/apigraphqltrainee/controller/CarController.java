package com.larissa.apigraphqltrainee.controller;

import com.larissa.apigraphqltrainee.input.CarInput;
import com.larissa.apigraphqltrainee.model.Car;
import com.larissa.apigraphqltrainee.service.CarService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public class CarController {

    @NoArgsConstructor
    @AllArgsConstructor
    @Controller
    public class CarroController  {
        @Autowired
        private CarService carService;


        @CrossOrigin("*")
        @QueryMapping
        public List<Car> findAllCar(){
            return carService.findAllCar();
        }

        @CrossOrigin("*")
        @QueryMapping
        public Car findCarById(@Arguments Long id){
            return carService.findCarById(id);
        }

        @CrossOrigin("*")
        @MutationMapping
        public Car createCar(@Arguments CarInput car) {
            return carService.createCar(car);
        }

        @CrossOrigin("*")
        @MutationMapping
        public String deleteCar(@Arguments Long id){
            return carService.deleteCar(id);

        }

        @CrossOrigin("*")
        @MutationMapping
        public Car updateCar(@Arguments CarInput car) {
            return carService.updateCar(car);
        }


        @CrossOrigin("*")
        @QueryMapping
        public String generatePdfABase64(@Arguments Long id) throws Exception {
            return carService.generatePdfABase64(id);
        }



    }
}
