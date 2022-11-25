package com.larissa.apigraphqltrainee.utils;

import ch.qos.logback.core.util.FileUtil;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.larissa.apigraphqltrainee.model.Base64URL;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ConvertToBase64 {

    public static String converterToB64(Path path) throws IOException {
        byte [] bytes  = Files.readAllBytes(path);

        String b64 = Base64.getEncoder().encodeToString(bytes);
        return b64;
    }

    public static File ConvertFileToFile(String data, String name) throws IOException {
        byte [] bytes = Base64.getDecoder().decode(data);
        File file = new File(name);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        return file;

    }

    public static String ConverByteToB64(byte [] bytes) {
        String b64 = Base64.getEncoder().encodeToString(bytes);
        return b64;


    }


}