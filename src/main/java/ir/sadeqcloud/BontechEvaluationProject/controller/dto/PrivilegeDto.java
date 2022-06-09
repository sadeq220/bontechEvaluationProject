package ir.sadeqcloud.BontechEvaluationProject.controller.dto;

public class PrivilegeDto {
    private String username;
    private String commercialServiceName;

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommercialServiceName() {
        return commercialServiceName;
    }

    public void setCommercialServiceName(String commercialServiceName) {
        this.commercialServiceName = commercialServiceName;
    }
}
