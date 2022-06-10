package ir.sadeqcloud.BontechEvaluationProject.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.CommercialServiceDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.PageDto;
import ir.sadeqcloud.BontechEvaluationProject.controller.dto.ServiceAvailabilityDto;
import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServiceAvailability;
import ir.sadeqcloud.BontechEvaluationProject.model.report.CommercialServiceUsage;
import ir.sadeqcloud.BontechEvaluationProject.service.clientAbstractionLayer.ClientAbstractionLayerOverServiceLayerContract;
import ir.sadeqcloud.BontechEvaluationProject.service.clientService.ClientReportServiceContract;
import ir.sadeqcloud.BontechEvaluationProject.service.dto.CommercialServiceResultContract;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/CLIENT")
@SecurityRequirement(name = "simpleUser")
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
    @PageableAsQueryParam
    @GetMapping("/report/available/service")
    public ResponseEntity<List<ServiceAvailabilityDto>> reportAvailableServices(@Parameter(hidden = true) @ModelAttribute PageDto pageDto){
        Page<CommercialServiceAvailability> availableServicesAtTheMoment = clientReportServiceContract.getAvailableServicesAtTheMoment(pageDto);
        List<ServiceAvailabilityDto> serviceAvailabilityDtos = availableServicesAtTheMoment.map(commercialServiceAvailability -> ServiceAvailabilityDto.factory(commercialServiceAvailability)).toList();
        return ResponseEntity.ok(serviceAvailabilityDtos);
    }
    @PageableAsQueryParam
    @GetMapping("/report/use/service")
    public ResponseEntity<List<CommercialServiceUsage>> reportCommercialServiceUsage(@RequestParam(defaultValue = "true")boolean success,@Parameter(hidden = true) @ModelAttribute PageDto pageDto){
        Page<CommercialServiceUsage> commercialServiceUsages = clientReportServiceContract.reportServiceUsage(success, pageDto);
        return ResponseEntity.ok(commercialServiceUsages.toList());
    }
    @GetMapping("/report/allowable/service")
    public ResponseEntity<Set<CommercialServiceDto>> reportAllowableCommercialServices(){
        Set<CommercialServiceDto> allowableServices = clientReportServiceContract.getAllowableServices();
        return ResponseEntity.ok(allowableServices);
    }
}
