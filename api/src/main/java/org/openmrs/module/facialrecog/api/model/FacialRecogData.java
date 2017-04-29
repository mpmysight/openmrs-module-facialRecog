package org.openmrs.module.facialrecog.api.model;

/**
 * Created by sight on 4/3/17.
 */

/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;

import java.io.Serializable;

public class FacialRecogData extends BaseOpenmrsData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Patient patient;
    private String filePath;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Patient getPatient(){
        return patient;
    }

    public void setPatient(final Patient patient){
        this.patient = patient;
    }

    public String getFilePath(){
        return filePath;
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

}
