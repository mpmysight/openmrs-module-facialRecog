package org.openmrs.module.facialrecog.web.utils;

import org.openmrs.Patient;

import java.util.HashMap;
import java.util.Map;

public class WebConverter {
    public static Map<String, Object> convertPatient(final Patient patient){
        Map<String, Object> map = new HashMap<String, Object>();
        if(patient != null) {
            map.put("id", patient.getId());
            map.put("familyname", patient.getFamilyName());
        }
        return map;
    }
}
