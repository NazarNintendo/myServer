package utils;

import models.LoggedEntity;

import java.util.ArrayList;
import java.util.List;

public interface Logger {
    List<LoggedEntity> logs = new ArrayList<>();
    void deposit(Object obj);
    Object retrieve(int ind);
}
