package models;


import models.requestModels.RequestObj;
import utils.ColorPrintable;

import java.text.SimpleDateFormat;

public class LoggedEntity implements ColorPrintable {
    private final long timeStamp;
    private final RequestObj request;

    public LoggedEntity(long timeStamp, RequestObj request) {
        this.timeStamp = timeStamp;
        this.request = request;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        return "###########################################################################\n" +
                "----------------[Time] " + formatter.format(timeStamp) + " ----------------\n" +
               request.toString();
    }
}
