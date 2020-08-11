package utils;

import models.LoggedEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
    private FileWriter fileWriter;
    private final String logsPath;

    public FileHelper(String logsPath) {
        this.logsPath = logsPath;
    }

    public void putToFile(LoggedEntity loggedEntity){
        try {
            initiateFileWriting();
            fileWriter.append(loggedEntity.toString());
            fileWriter.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void initiateFileWriting() throws IOException {
        fileWriter = new FileWriter(new File(logsPath), true);
    }

    public void clear() {
        try {
            FileOutputStream writer = new FileOutputStream(logsPath);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
