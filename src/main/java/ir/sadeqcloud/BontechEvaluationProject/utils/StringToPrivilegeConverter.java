package ir.sadeqcloud.BontechEvaluationProject.utils;

import ir.sadeqcloud.BontechEvaluationProject.model.endpoint.EndpointPrivilege;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.*;
import java.util.stream.Collectors;

@Converter
public class StringToPrivilegeConverter implements AttributeConverter<Set<EndpointPrivilege>,String> {

    @Override
    public String convertToDatabaseColumn(Set<EndpointPrivilege> endpointPrivileges) {
        if (endpointPrivileges==null)
            return null;
        Optional<String> simpleUSerPrivileges = endpointPrivileges.stream()
                .map(endpointPrivilege -> endpointPrivilege.name())
                .reduce((privilege1, privilege2) -> privilege1 + "," + privilege2);
        return simpleUSerPrivileges.orElse(null);
    }

    @Override
    public Set<EndpointPrivilege> convertToEntityAttribute(String s) {
        if (s==null)
            return new HashSet<>();
        String[] privileges = s.split(",");
        Set<EndpointPrivilege> endpointPrivileges = Arrays.stream(privileges)
                .map(privilege -> EndpointPrivilege.valueOf(privilege))
                .collect(Collectors.toSet());
        return endpointPrivileges;
    }
}
