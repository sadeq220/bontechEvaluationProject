package ir.sadeqcloud.BontechEvaluationProject.custException;

import ir.sadeqcloud.BontechEvaluationProject.model.endpoint.EndpointName;

public class ServiceNotAvailableAtTheMoment extends RuntimeException {
    private EndpointName endpointName;
    public ServiceNotAvailableAtTheMoment(EndpointName endpointName){
        this.endpointName=endpointName;
    }

    public EndpointName getEndpointName() {
        return endpointName;
    }
}
