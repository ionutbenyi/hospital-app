package com.example.hospitalapp.validators;

import com.example.hospitalapp.dto.DoctorDTO;
import com.example.hospitalapp.dto.PersonDTO;
import com.example.hospitalapp.errorhandler.IncorrectParameterException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class DoctorFieldValidator {

    private static final Log LOGGER = LogFactory.getLog(PersonFieldValidator.class);
    private static final EmailFieldValidator EMAIL_VALIDATOR = new EmailFieldValidator() ;

    public static void validateInsertOrUpdate(DoctorDTO docDTO) {

        List<String> errors = new ArrayList<>();

        if (docDTO == null) {
            errors.add("docDTO is null");
            throw new IncorrectParameterException(PersonDTO.class.getSimpleName(), errors);
        }
        if (docDTO.getEmail() == null || !EMAIL_VALIDATOR.validate(docDTO.getEmail())) {
            errors.add("Doctor email has invalid format");
        }

        if (!errors.isEmpty()) {
            LOGGER.error(errors);
            throw new IncorrectParameterException(DoctorFieldValidator.class.getSimpleName(), errors);
        }
    }
}
