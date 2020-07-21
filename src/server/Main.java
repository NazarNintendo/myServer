package server;

import com.sun.net.httpserver.*;
import handlers.MyHttpHandler;
import handlers.NotifyHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    private static HttpServer server;
    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    private static final String nazariyIP = "192.168.23.228";
    private static final String windaIP = "192.168.0.102";

    public static void main(String[] args) {
        try {
            server = HttpServer.create(new InetSocketAddress(nazariyIP,8000), 0);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        //server.createContext("/", new MyHttpHandler());
        server.createContext("/notify", new NotifyHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("InetSocketAddress = " + server.getAddress());
        System.out.println("HostName = " + server.getAddress().getHostName());
    }

}
