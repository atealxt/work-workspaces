package test.enumtest;

public class EnumTest {

    public static void main(final String[] args) {

        MyEnum myEnum = MyEnum.ENUM1;
        executeEnum(myEnum);


        System.out.println();

        System.out.println(MyEnum.valueOf(myEnum.toString()));
        System.out.println(MyEnum.valueOf("ENUM2"));

        executeEnum2(2);

        System.out.println();

        MyClass myClass = MyClass.ENUM1;
        executeEnum(myClass);
    }

    private static void executeEnum(final MyEnum myEnum) {

        // enum can switch
        switch (myEnum) {
            case ENUM1:
                System.out.println(myEnum);
                System.out.println(myEnum.getStatus());
                System.out.println(myEnum.name());
                System.out.println(myEnum.ordinal());
                break;
            default:
                break;
        }

    }

    private static void executeEnum2(final int status) {


    }

    private static void executeEnum(final MyClass myClass) {

        if (MyClass.ENUM1.equals(myClass)) {
            System.out.println(myClass);
        }
    }
}
