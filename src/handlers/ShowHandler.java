package handlers;

import com.sun.net.httpserver.HttpExchange;
import utils.Logger;

import java.io.*;

public class ShowHandler extends AbstractHandler implements Logger{

    public ShowHandler(boolean useArtifactLogging) {
        super(useArtifactLogging);
    }



    @Override
    public String handleGet(HttpExchange exchange) {
        return "Not yet implemented";
    }

    @Override
    public String handleDelete(HttpExchange exchange) {
        return "Not yet implemented";
    }

    @Override
    public String handleUpdate(HttpExchange exchange) {
        return "Not yet implemented";
    }

    @Override
    public String handlePost(HttpExchange exchange) {
        return "Not yet implemented";
    }

    @Override
    public void sendResponse(HttpExchange exchange, Object param) throws IOException {
        OutputStream response = exchange.getResponseBody();
        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<h1>").append("ShowHandler").append("</h1>").
                append("<p>").append(param.toString()).append("</p>");

        exchange.sendResponseHeaders(200, responseBody.toString().length());
        response.write(responseBody.toString().getBytes());
        response.flush();
        response.close();
    }

    @Override
    public void deposit(Object obj) {

    }

    @Override
    public Object retrieve(int ind) {
        return null;
    }
}
