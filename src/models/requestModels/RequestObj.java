package models.requestModels;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import utils.ColorPrintable;

import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestObj implements ColorPrintable {
    protected Headers headers;
    protected String requestBody;

    public RequestObj(HttpExchange exchange) {
        headers = exchange.getRequestHeaders();
        requestBody = new BufferedReader(new InputStreamReader(exchange.getRequestBody())).lines().collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, List<String>> header : headers.entrySet())
            result.append("[Header] ").
                    append(header.getKey()).
                    append(" -> ").
                    append(header.getValue()).
                    append("\n");

        result.append(requestBody).append("\n");

        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        RequestObj obj = (RequestObj) o;

        if (!this.headers.equals(obj.headers))
            return false;
        if (!this.requestBody.equals(obj.requestBody))
            return false;
        else
            return true;
    }

    @Override
    public  int hashCode() {
        int prime = 31;
        int result = (headers != null) ? headers.hashCode() : 0;
        result = result * prime + (requestBody != null ? requestBody.hashCode() : 0);
        return result;
    }
}
