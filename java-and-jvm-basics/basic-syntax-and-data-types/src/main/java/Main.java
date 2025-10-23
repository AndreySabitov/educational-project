public class Main {
    public static void main(String[] args) {
        // byte
        byte minByte = Byte.MIN_VALUE;
        byte maxByte = Byte.MAX_VALUE;
        System.out.println("byte:");
        System.out.println("Минимальное значение: " + minByte);
        System.out.println("Максимальное значение: " + maxByte);
        System.out.println("Занимает памяти: 1 байт");
        System.out.println();

        // short
        short minShort = Short.MIN_VALUE;
        short maxShort = Short.MAX_VALUE;
        System.out.println("short:");
        System.out.println("Минимальное значение: " + minShort);
        System.out.println("Максимальное значение: " + maxShort);
        System.out.println("Занимает памяти: 2 байта");
        System.out.println();

        // int
        int minInt = Integer.MIN_VALUE;
        int maxInt = Integer.MAX_VALUE;
        System.out.println("int:");
        System.out.println("Минимальное значение: " + minInt);
        System.out.println("Максимальное значение: " + maxInt);
        System.out.println("Занимает памяти: 4 байта");
        System.out.println();

        // long
        long minLong = Long.MIN_VALUE;
        long maxLong = Long.MAX_VALUE;
        System.out.println("long:");
        System.out.println("Минимальное значение: " + minLong);
        System.out.println("Максимальное значение: " + maxLong);
        System.out.println("Занимает памяти: 8 байтов");
        System.out.println();

        // float
        float minFloat = Float.MIN_VALUE;
        float maxFloat = Float.MAX_VALUE;
        System.out.println("float:");
        System.out.println("Минимальное значение: " + minFloat);
        System.out.println("Максимальное значение: " + maxFloat);
        System.out.println("Занимает памяти: 4 байта");
        System.out.println();

        // double
        double minDouble = Double.MIN_VALUE;
        double maxDouble = Double.MAX_VALUE;
        System.out.println("double:");
        System.out.println("Минимальное значение: " + minDouble);
        System.out.println("Максимальное значение: " + maxDouble);
        System.out.println("Занимает памяти: 8 байтов");
        System.out.println();

        // char
        char minChar = Character.MIN_VALUE;
        char maxChar = Character.MAX_VALUE;
        System.out.println("char:");
        System.out.println("Минимальное значение: " + (int) minChar);
        System.out.println("Максимальное значение: " + (int) maxChar);
        System.out.println("Занимает памяти: 2 байта");
        System.out.println();

        // boolean
        boolean exampleTrue = true;
        boolean exampleFalse = true;
        System.out.println("boolean:");
        System.out.printf("Может принимать значения %s или %s%n", exampleTrue, exampleFalse);
        System.out.println("Занимает памяти: 1 бит");
        System.out.println();

        // String
        String str = "String example";
        System.out.printf("String: %s%n", str);
    }
}
