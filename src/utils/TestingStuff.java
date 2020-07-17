package utils;

import java.util.Arrays;

public class TestingStuff {
    public static void main(String[] args) {
        String string = "av3122";

        try {
            byte[] array1 = string.getBytes("UTF-8");
            byte[] array2 = string.getBytes("UTF-16BE");
            byte[] array3 = string.getBytes("UTF-32BE");
            byte[] array4 = string.getBytes("UTF-16LE");

            System.out.print("UTF-8:");
            for(byte b : array1) {
                System.out.print(b);
            }
            System.out.println();

            System.out.print("UTF-16BE:");
            for(byte b : array2) {
                System.out.print(b);
            }
            System.out.println();

            System.out.print("UTF-32BE:");
            for(byte b : array3) {
                System.out.print(b);
            }
            System.out.println();

            System.out.print("UTF-16LE:");
            for(byte b : array4) {
                System.out.print(b);
            }
            System.out.println();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
