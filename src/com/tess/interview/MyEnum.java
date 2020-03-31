package com.tess.interview;

public enum MyEnum {
    ONE(1, "秦"), TWO(2, "齐"), THREE(3, "周"), FOUR(4, "楚"), FIVE(5, "韩"), SIX(6, "燕");
    private int code;
    private String message;

    MyEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public static MyEnum getEnum(int index) {
        MyEnum[] values = MyEnum.values();
        for (MyEnum myEnum : values) {
            if(myEnum.code==index){
                return myEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
