package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigClass {

    private static final String linuxIP = "192.168.23.228";
    private static final String windowsIP = "192.168.0.102";
    private static final String macWorkIP = "192.168.1.134";
    private static final String macHomeIP = "192.168.0.105";

    public static String getCorrectIP() {
        String os = System.getProperty("os.name");
        if (os.contains("Mac"))
            return macWorkIP;
        if (os.contains("Linux"))
            return linuxIP;
        if (os.contains("Windows"))
            return windowsIP;
        return "localhost";
    }







}
