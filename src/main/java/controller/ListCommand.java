package controller;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ListCommand {
    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime from;
    @DateTimeFormat(pattern="yyyyMMddHH")
    private LocalDateTime today;

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setToday(LocalDateTime today) {
        this.today = today;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getToday() {
        return today;
    }
}
