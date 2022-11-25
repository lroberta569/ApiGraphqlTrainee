package com.larissa.apigraphqltrainee.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImageService {

    private static String mimeType;

    private static String base64;

    public static ImageService get(String data){
        String regex = "((?<=[:/;]))";
        String [] strings = data.split((","));
        base64 = strings[1];
        String [] strings1 = strings[0].split(regex);
        mimeType = strings1[1] + strings1[2].replace(";", "");
        return new ImageService();
    }

    public String getMimeType(){
        return mimeType;
    }

    public String getBase64(){
        return base64;
    }

    public static String dataMimeType(String urlImageCar){
        String [] strings = urlImageCar.split("\\.");
        String extension = strings[1];

        if("png".equals(extension)){
            return "data:image/png:base64";
        }
        return "data:image/jpeg:base64";
    }





}


