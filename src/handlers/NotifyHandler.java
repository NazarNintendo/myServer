package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import models.LoggedEntity;
import models.Request;
import models.requestModels.NotificationRequest;

import utils.ColorPrintable;
import utils.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotifyHandler extends AbstractHandler implements ColorPrintable, Logger {

    private FileWriter fileWriter;

    public NotifyHandler(boolean useArtifactLogging) {
        super(useArtifactLogging);
    }


    private void fileStringifier(LoggedEntity loggedEntity) throws IOException{
        fileWriter.append(loggedEntity.toString());
        fileWriter.close();
    }



    private void initiateFileWriting() throws IOException {
        fileWriter = new FileWriter(new File(logsPath), true);
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

    public String handlePost(HttpExchange httpExchange) {
        Request postRequest = new NotificationRequest(httpExchange.getRequestHeaders(),
                                                        httpExchange.getRequestBody());

        LoggedEntity entity = new LoggedEntity(new Date().getTime(), postRequest);

        deposit(entity);
        
        return entity.toString();
    }

    public String handleGet(HttpExchange httpExchange) {
        try {
            initiateFileWriting();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        Request postRequest = new NotificationRequest(httpExchange.getRequestHeaders(),
                httpExchange.getRequestBody());

        LoggedEntity entity = new LoggedEntity(new Date().getTime(), postRequest);


        try {
            fileStringifier(entity);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(entity.toString());

        return "SUCCESS";
    }

    public String handleDelete(HttpExchange httpExchange) {
        return "Not yet implemented";
    }

    public String handleUpdate(HttpExchange httpExchange) {
        return "Not yet implemented";
    }

    @Override
    public void deposit(Object obj) {
        LoggedEntity log = (LoggedEntity) obj;
        logs.add(log);
    }

    @Override
    public Object retrieve(int ind) {
        return logs.get(ind);
    }

    public void sendResponse(HttpExchange httpExchange, Object parameters) throws IOException{
        OutputStream response = httpExchange.getResponseBody();
        StringBuilder responseBody = new StringBuilder();
        responseBody.append("<h1>").append("NotifyHandler").append("</h1>").
                append("<p>").append(parameters.toString()).append("</p>");

        httpExchange.sendResponseHeaders(200, responseBody.toString().length());
        response.write(responseBody.toString().getBytes());
        response.flush();
        response.close();
    }

}
