package models.requestModels;

import com.sun.net.httpserver.Headers;
import models.Request;
import utils.ColorPrintable;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

public class NotificationRequest extends Request{
    public NotificationRequest(Headers headers, InputStream requestBody) {
        this.headers = headers;
        this.requestBody = requestBody;
    }
}
