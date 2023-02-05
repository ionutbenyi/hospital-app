package com.example.hospitalapp.validators;

import com.example.hospitalapp.dto.PatientDTO;
import com.example.hospitalapp.errorhandler.IncorrectParameterException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class PatientFieldValidator {

    private static final Log LOGGER = LogFactory.getLog(PersonFieldValidator.class);
    private static final EmailFieldValidator EMAIL_VALIDATOR = new EmailFieldValidator() ;

    public static void validateInsertOrUpdate(PatientDTO patientDTO) {

        List<String> errors = new ArrayList<>();
        if (patientDTO == null) {
            errors.add("patientDTO is null");
            throw new IncorrectParameterException(PatientDTO.class.getSimpleName(), errors);
        }
        if (patientDTO.getEmail() == null || !EMAIL_VALIDATOR.validate(patientDTO.getEmail())) {
            errors.add("Patient email has invalid format");
        }

        if (!errors.isEmpty()) {
            LOGGER.error(errors);
            throw new IncorrectParameterException(PatientFieldValidator.class.getSimpleName(), errors);
        }
    }
}
