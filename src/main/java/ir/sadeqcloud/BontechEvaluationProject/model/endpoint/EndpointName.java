package ir.sadeqcloud.BontechEvaluationProject.model.endpoint;

public enum EndpointName {

    SEND_SMS(EndpointPrivilege.SEND_SMS);

    private EndpointPrivilege privilege;

    EndpointName(EndpointPrivilege privilege){
        this.privilege= privilege;
    }

    public EndpointPrivilege getCorrelatePrivilege(){
        return privilege;
    }
}
