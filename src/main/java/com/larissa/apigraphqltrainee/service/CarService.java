package com.larissa.apigraphqltrainee.service;

import com.larissa.apigraphqltrainee.input.CarInput;
import com.larissa.apigraphqltrainee.model.Car;
import com.larissa.apigraphqltrainee.pdfa.CreatePDFA;
import com.larissa.apigraphqltrainee.repository.CarRepository;
import com.larissa.apigraphqltrainee.utils.ConvertToBase64;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CarService {

    @Autowired
    CarRepository carroRepository;

    public Car createCar(CarInput carroInput) {
        Car novoCarro = new Car();
        BeanUtils.copyProperties(carroInput, novoCarro);
        return carroRepository.save(novoCarro);
    }

    public List<Car> findAllCar() {
        return carroRepository.findAll();
    }

    public Car findCarById(Long id) {
        return carroRepository.findById(id).get();
    }

    public String deleteCar(Long id) {
        carroRepository.deleteById(id);
        return "Carro excluído com sucesso!";
    }

    public Car updateCar(CarInput carroInput) {
        return createCar(carroInput);
    }

    public String generatePdfABase64(Long id) throws Exception {
        Car car = findCarById(id);
        try{
            CreatePDFA.createPDF(car);
            return ConvertToBase64.converterPDFA(Paths.get("/app/arquivo.pdf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

