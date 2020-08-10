package models;

import com.sun.net.httpserver.Headers;
import utils.ColorPrintable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class Request implements ColorPrintable {
    protected Headers headers;
    protected InputStream requestBody;


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry header : headers.entrySet())
            result.append("[Header] ").
                    append(header.getKey()).
                    append(" -> ").
                    append(header.getValue()).
                    append("\n");


        String requestString = new BufferedReader(new InputStreamReader(requestBody)).lines()
                .parallel().collect(Collectors.joining("\n"));

        return result.append(requestString).toString() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != getClass())
            return false;

        Request obj = (Request) o;

        if (!this.headers.equals(obj.headers))
            return false;
        if (!this.requestBody.equals(obj.requestBody))
            return false;
        else
            return true;
    }

    @Override
    public  int hashCode() {
        return 0;
    }
}
