package ir.sadeqcloud.BontechEvaluationProject.utils;

import ir.sadeqcloud.BontechEvaluationProject.model.commercialService.CommercialServicePrivilege;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.*;
import java.util.stream.Collectors;

@Converter
public class StringToPrivilegeConverter implements AttributeConverter<Set<CommercialServicePrivilege>,String> {

    @Override
    public String convertToDatabaseColumn(Set<CommercialServicePrivilege> commercialServicePrivileges) {
        if (commercialServicePrivileges==null)
            return null;
        Optional<String> simpleUSerPrivileges = commercialServicePrivileges.stream()
                .map(commercialServicePrivilege -> commercialServicePrivilege.name())
                .reduce((privilege1, privilege2) -> privilege1 + "," + privilege2);
        return simpleUSerPrivileges.orElse(null);
    }

    @Override
    public Set<CommercialServicePrivilege> convertToEntityAttribute(String s) {
        if (s==null)
            return new HashSet<>();
        String[] privileges = s.split(",");
        Set<CommercialServicePrivilege> commercialServicePrivileges = Arrays.stream(privileges)
                .map(privilege -> CommercialServicePrivilege.valueOf(privilege))
                .collect(Collectors.toSet());
        return commercialServicePrivileges;
    }
}
