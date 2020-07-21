package models;


import com.sun.net.httpserver.Headers;
import utils.ColorPrintable;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public abstract class Request implements ColorPrintable {
    protected Headers headers;
    protected InputStream requestBody;


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry header : headers.entrySet())
            result.append(ANSI_RESET + "[Header] ").
                    append(ANSI_GREEN + header.getKey()).
                    append(" -> ").
                    append(ANSI_PURPLE + header.getValue()).
                    append("\n");

        Scanner scanner = new Scanner(requestBody).useDelimiter("\n");
        while (scanner.hasNext())
            result.append(scanner.hasNext());

        return result.toString();
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
