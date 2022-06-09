package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceAvailabilityDto {
    private String commercialServiceName;
    private LocalDate date;
    private LocalTime startOfAvailability;
    private LocalTime endOfAvailability;

    public String getCommercialServiceName() {
        return commercialServiceName;
    }

    public void setCommercialServiceName(String commercialServiceName) {
        this.commercialServiceName = commercialServiceName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartOfAvailability() {
        return startOfAvailability;
    }

    public void setStartOfAvailability(LocalTime startOfAvailability) {
        this.startOfAvailability = startOfAvailability;
    }

    public LocalTime getEndOfAvailability() {
        return endOfAvailability;
    }

    public void setEndOfAvailability(LocalTime endOfAvailability) {
        this.endOfAvailability = endOfAvailability;
    }
}