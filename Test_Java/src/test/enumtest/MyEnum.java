package test.enumtest;

public enum MyEnum {

    ENUM1(1), ENUM2(2);

    private MyEnum(final int status) {
        this.status = status;
    }

    private final int status;

    /** 自定义的枚举参数 */
    public final int getStatus() {
        return status;
    }

    public final static MyEnum getMyEnum(final int status) {

        if(ENUM1.status == status){
            return ENUM1;
        }
        if(ENUM2.status == status){
            return ENUM1;
        }


//        if(ENUM1.getStatus() == )

        return ENUM1;
    }
}
