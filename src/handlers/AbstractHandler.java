package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public abstract class AbstractHandler implements HttpHandler {
    protected String logsPath;

    public AbstractHandler() {};

    public AbstractHandler(boolean useArtifactLogging) {
        this.logsPath = useArtifactLogging ? "resources/logs" : "../../../resources/logs";
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Object parameter = "default response";


        switch(exchange.getRequestMethod()) {
            case "POST": {
                parameter = handlePost(exchange);
                break;
            }
            case "GET": {
                parameter = handleGet(exchange);
                break;
            }
            case "DELETE": {
                parameter = handleDelete(exchange);
                break;
            }
            case "UPDATE": {
                parameter = handleUpdate(exchange);
                break;
            }
        }
        sendResponse(exchange, parameter);
    }

    public abstract String handlePost(HttpExchange exchange);

    public abstract String handleGet(HttpExchange exchange);

    public abstract String handleDelete(HttpExchange exchange);

    public abstract String handleUpdate(HttpExchange exchange);

    public abstract void sendResponse(HttpExchange exchange, Object param) throws IOException;

}
