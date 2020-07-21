package models;

import com.sun.javafx.binding.StringFormatter;
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
        return ANSI_CYAN + "----------------[Time] " + formatter.format(timeStamp) + "----------------\n" + ANSI_RESET +
                request.toString();
    }


}
