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
package org.openmrs.module.facialrecog.api.impl;

import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.facialrecog.api.FacialRecogService;
import org.openmrs.module.facialrecog.api.db.FacialRecogDAO;

import org.openmrs.api.context.Context;

import com.sight.facialrecog.api.service.FacialRecogLibService;
import org.openmrs.module.facialrecog.api.model.FacialRecogData;

/**
 * It is a default implementation of {@link FacialRecogService}.
 */
public class FacialRecogServiceImpl extends BaseOpenmrsService implements FacialRecogService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private FacialRecogDAO dao;
	private FacialRecogLibService facialRecogLibService;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(FacialRecogDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public FacialRecogDAO getDao() {
	    return dao;
    }

    /**
     *
     */
    public void save(String encodedImage, String patientUuid){
        String filePath = facialRecogLibService.save(encodedImage);
        if(filePath != null){
            PatientService patientService = Context.getPatientService();
            Patient patient = patientService.getPatientByUuid(patientUuid);
            if(patient != null){
                FacialRecogData facialRecogData = new FacialRecogData();
                facialRecogData.setPatient(patient);
                facialRecogData.setFilePath(filePath);
                dao.saveOrUpdate(facialRecogData);
            } else {
                //throw patient not found exception
            }
        } else {
            //throw Image Save Exception
        }
    }

    public Patient identify(String encodedImage){
        String filePath =  facialRecogLibService.identify(encodedImage);
        if(filePath != null){
            FacialRecogData facialRecogData = dao.getByFilePath(filePath);
            if(facialRecogData!= null){
                return facialRecogData.getPatient();
            }
        }
        return null;
    }
}