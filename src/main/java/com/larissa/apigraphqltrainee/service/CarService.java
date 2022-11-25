package com.larissa.apigraphqltrainee.service;

import com.amazonaws.services.s3.model.S3Object;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CarService {

    @Autowired
    CarRepository carroRepository;
    @Autowired
    ImageService storageService;

    @Transactional
    public Car createCar(CarInput car) throws IOException {
        Car newCar = new Car();
        BeanUtils.copyProperties(car, newCar);

        if(car.getId() != null){
            newCar.setCarImageName();
        }
        newCar = carroRepository.save(newCar);
        if(car.getId() == null){
            newCar.setCarImageName();
        }
        AwsService.uploadFile(car.getImageCarBase64(), newCar.getUrlImageCar());
        return newCar;
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

    public Car updateCar(CarInput carroInput) throws IOException {
        return createCar(carroInput);
    }

    public String generatePdfABase64(Long id) throws Exception {
        Car car = findCarById(id);
        try{
            CreatePDFA.createPDF(car);
            return ConvertToBase64.converterToB64(Paths.get("/app/arquivo.pdf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String fetcCarImageOnAWSS3(String nameCar) throws IOException {
        try {
           byte [] bytes = AwsService.donwloadFile(nameCar);
            return ConvertToBase64.ConverByteToB64(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }





}

