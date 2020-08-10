package models;


import com.sun.net.httpserver.Headers;
import utils.ColorPrintable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggedEntity implements ColorPrintable {
    private long timeStamp;
    private Request request;
    private String requestBody;
    public String fullBody;

    public LoggedEntity() {}

    public LoggedEntity(String fullBody) {
        this.fullBody = fullBody;
    }

    public LoggedEntity(long timeStamp, Request request) {
        this.timeStamp = timeStamp;
        this.request = request;
        requestBody = request.toString();
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return "----------------[Time] " + formatter.format(timeStamp) + "----------------\n" +
               requestBody;
    }


}
