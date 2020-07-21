package models;


import com.sun.net.httpserver.Headers;
import utils.ColorPrintable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggedEntity implements ColorPrintable {
    private long timeStamp;
    private Request request;

    public LoggedEntity(long timeStamp, Request request) {
        this.timeStamp = timeStamp;
        this.request = request;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return "----------------[Time] " + formatter.format(timeStamp) + "----------------\n" +
                request.toString();
    }


}
