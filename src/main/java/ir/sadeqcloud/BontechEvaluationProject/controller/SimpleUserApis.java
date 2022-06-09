package ir.sadeqcloud.BontechEvaluationProject.controller;

import ir.sadeqcloud.BontechEvaluationProject.service.clientAbstractionLayer.ClientAbstractionLayerOverServiceLayerContract;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceResultContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CLIENT")
public class SimpleUserApis {

    private final ClientAbstractionLayerOverServiceLayerContract clientAbstractionLayerOverServiceLayerContract;
    @Autowired
    public SimpleUserApis(ClientAbstractionLayerOverServiceLayerContract clientAbstractionLayerOverServiceLayerContract) {
        this.clientAbstractionLayerOverServiceLayerContract = clientAbstractionLayerOverServiceLayerContract;
    }
    @PostMapping("/use/service/{service}")
    public ResponseEntity useCommercialService(@PathVariable(name = "service") String commercialServiceName){
        CommercialServiceResultContract commercialServiceResultContract = clientAbstractionLayerOverServiceLayerContract.useCommercialService(commercialServiceName, null);

        return ResponseEntity.ok(commercialServiceResultContract);
    }
}
