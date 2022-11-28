package com.larissa.apigraphqltrainee.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.larissa.apigraphqltrainee.utils.ConvertToBase64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class AwsService {

    @Value("${app.awsServices.bucketName}")
    private String bucketName;
    @Autowired
    private AmazonS3 s3Cliente;

    public  String uploadFile(String data, String name) throws IOException {
        ImageService imageService = ImageService.get(data);
        File fileObj = ConvertToBase64.ConvertFileToFile(imageService.getBase64(), name);
        s3Cliente.putObject(new PutObjectRequest(bucketName, name, fileObj));
        fileObj.delete();
        return "Arquivo " + name + " enviado: ";
    }

    public  byte[] donwloadFile(String name) throws IOException {
        S3Object s3Object = s3Cliente.getObject(new GetObjectRequest(bucketName, name));
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        byte[] bytes = IOUtils.toByteArray(s3ObjectInputStream);
        return bytes;
    }


}
