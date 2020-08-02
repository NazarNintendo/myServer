package server;

import com.sun.net.httpserver.*;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import handlers.MyHttpHandler;
import handlers.NotifyHandler;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    private static HttpServer server;
    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    private static final String linuxIP = "192.168.23.228";
    private static final String windowsIP = "192.168.0.102";
    private static final String macIP = "192.168.201.186";


    private static String getCorrectIP() {
        String os = System.getProperty("os.name");
        if (os.contains("Mac"))
            return macIP;
        if (os.contains("Linux"))
            return linuxIP;
        if (os.contains("Windows"))
            return windowsIP;
        return "localhost";
    }

    public static void main(String[] args) {
        System.out.println("args = " + Arrays.toString(args));
        System.out.println("OS.name = " + System.getProperty("os.name"));

        boolean useArtifactLoggingPath = (args.length != 0) ? !args[0].equals("fromIDE") : true;

        System.out.println("flag = " + useArtifactLoggingPath);

        try {
            server = HttpServer.create(new InetSocketAddress(getCorrectIP(),8000), 0);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        //server.createContext("/", new MyHttpHandler());
        server.createContext("/notify", new NotifyHandler(useArtifactLoggingPath));
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("InetSocketAddress = " + server.getAddress());
        System.out.println("HostName = " + server.getAddress().getHostName());

        Scanner sc = new Scanner(System.in);
        String part;
        while (sc.hasNextLine()) {
            part = sc.nextLine();
            System.out.println("{\"" + part + "\"},");
        }

    }

}
