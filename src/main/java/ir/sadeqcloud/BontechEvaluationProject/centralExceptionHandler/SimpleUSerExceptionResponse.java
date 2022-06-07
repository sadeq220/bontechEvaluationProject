package ir.sadeqcloud.BontechEvaluationProject.centralExceptionHandler;

import ir.sadeqcloud.BontechEvaluationProject.model.endpoint.EndpointName;

public class SimpleUSerExceptionResponse {
    private EndpointName endpointName;
    private String message;

    public SimpleUSerExceptionResponse(EndpointName endpointName, String message) {
        this.endpointName = endpointName;
        this.message = message;
    }

    public EndpointName getEndpointName() {
        return endpointName;
    }

    public String getMessage() {
        return message;
    }
}
