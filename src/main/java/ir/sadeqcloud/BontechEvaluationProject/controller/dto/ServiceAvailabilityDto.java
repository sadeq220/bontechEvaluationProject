package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceAvailabilityDto {
    private String commercialServiceName;
    private LocalDate date;
    @Schema(implementation = String.class,example = "HH:mm")
    private LocalTime startOfAvailability;
    @Schema(implementation = String.class,example = "HH:mm")
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
    public static ServiceAvailabilityDto factory(CommercialServiceAvailability commercialServiceAvailability){
        ServiceAvailabilityDto serviceAvailabilityDto = new ServiceAvailabilityDto();
        serviceAvailabilityDto.date=commercialServiceAvailability.getCommercialServiceAvailabilityKey().getDate();
        serviceAvailabilityDto.commercialServiceName=commercialServiceAvailability.getCommercialServiceAvailabilityKey().getCommercialServiceName();
        serviceAvailabilityDto.startOfAvailability=commercialServiceAvailability.getStartOfAvailability();
        serviceAvailabilityDto.endOfAvailability=commercialServiceAvailability.getEndOfAvailability();
        return serviceAvailabilityDto;
    }
}
