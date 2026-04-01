package com.onik.eduspring.enums;

public enum ActivityTypeEnum {

    HOMEWORK(1, "作业"),       // 作业活动，需要上传文件或提交内容
    EXAM(2, "考试"),           // 在线测试，可自动评分
    DISCUSSION(3, "讨论"),     // 评论、讨论区
    SIGN(4, "签到");            // 签到打卡

    private final int code;    // 数据库存储值
    private final String desc; // 前端显示文字

    ActivityTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public int getCode() { return code; }
    public String getDesc() { return desc; }

    /**
     * 根据code获取枚举
     */
    public static ActivityTypeEnum of(int code) {
        for (ActivityTypeEnum type : values()) {
            if (type.code == code) return type;
        }
        throw new IllegalArgumentException("无效活动类型: " + code);
    }
}
