package com.larissa.apigraphqltrainee.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ConvertToBase64 {

    public static String converterPDFA(Path path) throws IOException {
        byte [] bytes  = Files.readAllBytes(path);

        String b64 = Base64.getEncoder().encodeToString(bytes);
        return b64;
    }
}