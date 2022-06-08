package ir.sadeqcloud.BontechEvaluationProject.model.commercialService;

public enum CommercialServiceName {

    SEND_SMS(CommercialServicePrivilege.SEND_SMS);

    private CommercialServicePrivilege privilege;

    CommercialServiceName(CommercialServicePrivilege privilege){
        this.privilege= privilege;
    }

    public CommercialServicePrivilege getCorrelatePrivilege(){
        return privilege;
    }
}
