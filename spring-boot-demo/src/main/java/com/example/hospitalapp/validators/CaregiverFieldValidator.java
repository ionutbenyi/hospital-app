package com.example.hospitalapp.validators;

import com.example.hospitalapp.dto.CaregiverDTO;
import com.example.hospitalapp.errorhandler.IncorrectParameterException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class CaregiverFieldValidator {

    private static final Log LOGGER = LogFactory.getLog(PersonFieldValidator.class);
    private static final EmailFieldValidator EMAIL_VALIDATOR = new EmailFieldValidator() ;

    public static void validateInsertOrUpdate(CaregiverDTO caregiverDTO) {

        List<String> errors = new ArrayList<>();
        if (caregiverDTO == null) {
            errors.add("caregiverDTO is null");
            throw new IncorrectParameterException(CaregiverDTO.class.getSimpleName(), errors);
        }
        if (caregiverDTO.getEmail() == null || !EMAIL_VALIDATOR.validate(caregiverDTO.getEmail())) {
            errors.add("Caregiver email has invalid format");
        }

        if (!errors.isEmpty()) {
            LOGGER.error(errors);
            throw new IncorrectParameterException(PersonFieldValidator.class.getSimpleName(), errors);
        }
    }
}
