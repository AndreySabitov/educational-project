public class Main {
    public static void main(String[] args) {
        // byte
        byte minByte = -128;
        byte byteExample = 99;
        byte maxByte = 127;
        System.out.println("byte:");
        System.out.println("Минимальное значение: " + minByte);
        System.out.println("Пример значения: " + byteExample);
        System.out.println("Максимальное значение: " + maxByte);
        System.out.println("Занимает памяти: 8 бит");
        System.out.println();

        // short
        short minShort = -32768;
        short shortExample = 130;
        short maxShort = 32767;
        System.out.println("short:");
        System.out.println("Минимальное значение: " + minShort);
        System.out.println("Пример значения: " + shortExample);
        System.out.println("Максимальное значение: " + maxShort);
        System.out.println("Занимает памяти: 16 бит");
        System.out.println();

        // int
        int minInt = -2147483648;
        int intExample = 33000;
        int maxInt = 2147483647;
        System.out.println("int:");
        System.out.println("Минимальное значение: " + minInt);
        System.out.println("Пример значения: " + intExample);
        System.out.println("Максимальное значение: " + maxInt);
        System.out.println("Занимает памяти: 32 бита");
        System.out.println();

        // long
        long minLong = -9223372036854775808L;
        long longExample = 3000000000L;
        long maxLong = 9223372036854775807L;
        System.out.println("long:");
        System.out.println("Минимальное значение: " + minLong);
        System.out.println("Пример значения: " + longExample);
        System.out.println("Максимальное значение: " + maxLong);
        System.out.println("Занимает памяти: 64 бита");
        System.out.println();

        // float
        float minFloat = 1.4E-45f;
        float floatExample = 4.48f;
        float maxFloat = 3.4028235E38f;
        System.out.println("float:");
        System.out.println("Минимальное значение: " + minFloat);
        System.out.println("Пример значения: " + floatExample);
        System.out.println("Максимальное значение: " + maxFloat);
        System.out.println("Занимает памяти: 32 бита");
        System.out.println();

        // double
        double minDouble = 4.9E-324;
        double doubleExample = 5.5E6;
        double maxDouble = 1.7976931348623157E308;
        System.out.println("double:");
        System.out.println("Минимальное значение: " + minDouble);
        System.out.println("Пример значения: " + doubleExample);
        System.out.println("Максимальное значение: " + maxDouble);
        System.out.println("Занимает памяти: 64 бита");
        System.out.println();

        // char
        char minChar = 0;
        char charExample = 'A';
        char maxChar = 65535;
        System.out.println("char:");
        System.out.println("Минимальное значение: " + (int) minChar);
        System.out.println("Пример значения: " + (int) charExample);
        System.out.println("Максимальное значение: " + (int) maxChar);
        System.out.println("Занимает памяти: 16 бит");
        System.out.println();

        // boolean
        boolean exampleTrue = true;
        boolean exampleFalse = false;
        System.out.println("boolean:");
        System.out.printf("Может принимать значения %s или %s%n", exampleTrue, exampleFalse);
        System.out.println("Занимает памяти: 1 бит");
        System.out.println();

        // String
        String str = "String example";
        System.out.printf("String: %s%n", str);
    }
}
