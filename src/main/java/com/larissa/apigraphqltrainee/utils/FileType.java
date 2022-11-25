package com.larissa.apigraphqltrainee.utils;

public enum FileType {
    PNG("image/png", "png"),
    JPG("image/jpeg", "jpeg");

    final String mimeType;
    final String extensao;

    FileType(String mineType, String extensao){
        this.mimeType = mineType;
        this.extensao = extensao;
    }

    public String getMineType() {
        return mimeType;
    }

    public String getExtensao() {
        return extensao;
    }

    public boolean sameOf(String mimeType) {
        return this.mimeType.equalsIgnoreCase(mimeType);
    }

    public static FileType of(String mimeType) {
        for(FileType fileType : values()) {
            if(fileType.sameOf(mimeType)) {
                return fileType;
            }
        }
        return null;
    }
}
