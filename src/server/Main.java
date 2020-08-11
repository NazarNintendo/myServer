package server;

import com.sun.net.httpserver.*;
import handlers.NotifyHandler;
import handlers.ShowHandler;

import utils.ColorPrintable;
import utils.ConfigClass;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main implements ColorPrintable {
    private static HttpServer server;
    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try {
            server = HttpServer.create(new InetSocketAddress(ConfigClass.getCorrectIP(),8000), 0);
        }
        catch(Exception e) {
            e.printStackTrace();
            return;
        }

        boolean useArtifactLoggingPath = (args.length != 0) ? !args[0].equals("fromIDE") : true;

        server.createContext("/notify", new NotifyHandler(useArtifactLoggingPath));
        server.createContext("/show", new ShowHandler(useArtifactLoggingPath));
        server.setExecutor(threadPoolExecutor);
        server.start();

        System.out.println(GREEN_BOLD + "Server started successfully");
        System.out.println(CYAN_BOLD + "InetSocketAddress = " + RESET + server.getAddress());
        System.out.println(CYAN_BOLD + "HostName = " + RESET + server.getAddress().getHostName() + RESET);
    }

}