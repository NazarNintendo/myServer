package handlers;

import com.sun.net.httpserver.HttpExchange;

import models.LoggedEntity;
import models.requestModels.RequestObj;

import utils.ColorPrintable;
import utils.FileHelper;

import java.io.*;
import java.util.Date;

public class NotifyHandler extends AbstractHandler implements ColorPrintable {

    private final FileHelper fileHelper = new FileHelper(logsPath);

    public NotifyHandler(boolean useArtifactLogging) {
        super(useArtifactLogging);
        fileHelper.clear();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        Object response;

        switch(httpExchange.getRequestMethod()) {
            case "GET": {
                response = handleGet(httpExchange);
                break;
            }
            case "POST": {
                response = handlePost(httpExchange);
                break;
            }
            case "DELETE": {
                response = handleDelete(httpExchange);
                break;
            }
            case "UPDATE": {
                response = handleUpdate(httpExchange);
                break;
            }
            default:
                response = "Not yet implemented";
        }

        sendResponse(httpExchange, response);
    }

    public String handlePost(HttpExchange exchange) {
        RequestObj postRequest = new RequestObj(exchange);
        long timeStamp = new Date().getTime();

        LoggedEntity entity = new LoggedEntity(timeStamp, postRequest);

        fileHelper.putToFile(entity);

        return entity.toString();
    }

    public String handleGet(HttpExchange exchange) {
        return "Not yet implemented";
    }

    public String handleDelete(HttpExchange httpExchange) {
        return "Not yet implemented";
    }

    public String handleUpdate(HttpExchange httpExchange) {
        return "Not yet implemented";
    }

    public void sendResponse(HttpExchange httpExchange, Object obj) throws IOException{
        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<h1>").append("NotifyHandler").append("</h1>").
                append("<p>").append(obj).append("</p>");

        httpExchange.sendResponseHeaders(200, responseBody.toString().length());

        OutputStream response = httpExchange.getResponseBody();
        response.write(responseBody.toString().getBytes());
        response.flush();
        response.close();


    }

}
