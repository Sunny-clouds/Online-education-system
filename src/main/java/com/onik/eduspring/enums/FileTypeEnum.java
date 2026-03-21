package com.onik.eduspring.enums;


/**
 * 文件类型枚举
 */
public enum FileTypeEnum {

    VIDEO("video", "videoFileStrategy"),
    IMAGE("image", "imageFileStrategy"),
    DOC("doc", "docFileStrategy");

    private final String type;
    private final String beanName;

    FileTypeEnum(String type, String beanName) {
        this.type = type;
        this.beanName = beanName;
    }

    public String getType() {
        return type;
    }

    public String getBeanName() {
        return beanName;
    }

    public static FileTypeEnum getByType(String type) {
        for (FileTypeEnum value : values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }
        return null;
    }
}