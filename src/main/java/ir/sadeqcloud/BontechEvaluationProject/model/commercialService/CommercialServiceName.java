package ir.sadeqcloud.BontechEvaluationProject.model.commercialService;

public enum CommercialServiceName {

    SEND_SMS(CommercialServicePrivilege.SEND_SMS),
    SEND_EMAIL(CommercialServicePrivilege.SEND_EMAIL);

    private CommercialServicePrivilege privilege;

    CommercialServiceName(CommercialServicePrivilege privilege){
        this.privilege= privilege;
    }

    public CommercialServicePrivilege getCorrelatePrivilege(){
        return privilege;
    }
}
