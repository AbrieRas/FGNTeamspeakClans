package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utility {
    public String getDateTimeNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter jsonFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(jsonFormatter).replace(" ", "T");
    }
}
