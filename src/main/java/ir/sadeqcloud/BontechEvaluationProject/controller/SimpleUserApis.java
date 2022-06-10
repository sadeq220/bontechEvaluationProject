package ir.sadeqcloud.BontechEvaluationProject.controller;

import ir.sadeqcloud.BontechEvaluationProject.controller.dto.PageDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.ServiceAvailabilityDto;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;
import ir.sadeqcloud.BontechEvaluationProject.service.clientAbstractionLayer.ClientAbstractionLayerOverServiceLayerContract;
import ir.sadeqcloud.BontechEvaluationProject.service.clientService.ClientReportServiceContract;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceResultContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CLIENT")
public class SimpleUserApis {

    private final ClientAbstractionLayerOverServiceLayerContract clientAbstractionLayerOverServiceLayerContract;
    private final ClientReportServiceContract clientReportServiceContract;
    @Autowired
    public SimpleUserApis(ClientAbstractionLayerOverServiceLayerContract clientAbstractionLayerOverServiceLayerContract
                         ,ClientReportServiceContract clientReportServiceContract) {
        this.clientAbstractionLayerOverServiceLayerContract = clientAbstractionLayerOverServiceLayerContract;
        this.clientReportServiceContract=clientReportServiceContract;
    }
    @PostMapping("/use/service/{service}")
    public ResponseEntity useCommercialService(@PathVariable(name = "service") String commercialServiceName){
        CommercialServiceResultContract commercialServiceResultContract = clientAbstractionLayerOverServiceLayerContract.useCommercialService(commercialServiceName, null);

        return ResponseEntity.ok(commercialServiceResultContract);
    }
    @GetMapping("/report/available/service")
    public ResponseEntity<List<ServiceAvailabilityDto>> reportAvailableServices(@ModelAttribute PageDto pageDto){
        Page<CommercialServiceAvailability> availableServicesAtTheMoment = clientReportServiceContract.getAvailableServicesAtTheMoment(pageDto);
        List<ServiceAvailabilityDto> serviceAvailabilityDtos = availableServicesAtTheMoment.map(commercialServiceAvailability -> ServiceAvailabilityDto.factory(commercialServiceAvailability)).toList();
        return ResponseEntity.ok(serviceAvailabilityDtos);
    }
}
