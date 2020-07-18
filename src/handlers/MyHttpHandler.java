package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utils.ColorPrintable;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyHttpHandler implements HttpHandler, ColorPrintable {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;

        if ("GET".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handleGetRequest(httpExchange);
        }
        else if ("POST".equals(httpExchange.getRequestMethod())) {
            requestParamValue = handlePostRequest(httpExchange);
        }

        sendResponse(httpExchange, requestParamValue);
    }

    private String handleGetRequest(HttpExchange httpExchange) {
        SimpleDateFormat formatter = new SimpleDateFormat("[dd-MM-yyyy HH:mm:ss]");
        System.out.println(ANSI_CYAN + "[Time] " + formatter.format(new Date().getTime()));

        for (Map.Entry headers : httpExchange.getRequestHeaders().entrySet()) {
//            if (headers.getKey().toString().equals("Host"))
                System.out.println(ANSI_GREEN + "[Header] " + ANSI_RESET + headers.getKey() + " -> " + headers.getValue());
        }

        Scanner scanner = new Scanner(httpExchange.getRequestBody()).useDelimiter("} ");
        String requestBody = scanner.hasNext() ? scanner.next() : "";
        System.out.println(ANSI_PURPLE + "[Request Body]\n"  + requestBody + ANSI_RESET);

        return requestBody;
    }

    private String handlePostRequest(HttpExchange httpExchange) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(ANSI_CYAN + "[Time] " + formatter.format(new Date().getTime()));

        for (Map.Entry headers : httpExchange.getRequestHeaders().entrySet()) {
//            if (headers.getKey().toString().equals("Host"))
                System.out.println(ANSI_GREEN + "[Header] " + ANSI_RESET + headers.getKey() + " -> " + headers.getValue());
        }


        Scanner scanner = new Scanner(httpExchange.getRequestBody()).useDelimiter("} ");
        String requestBody = scanner.hasNext() ? scanner.next() : "";
        System.out.println(ANSI_PURPLE + "[Request Body]\n"  + requestBody + ANSI_RESET);

        return requestBody;
    }

    private void sendResponse(HttpExchange httpExchange, String requestParamValue) throws IOException{
        OutputStream outputStream = httpExchange.getResponseBody();
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.
                append("<h1>").
                append("I ya love Nazar :3").
                append("</h1>");
//                append("<iframe><p>").
//                append(requestParamValue).
//                append("</p></iframe>");

        httpExchange.sendResponseHeaders(200, htmlBuilder.toString().length());

        outputStream.write(htmlBuilder.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }


}
