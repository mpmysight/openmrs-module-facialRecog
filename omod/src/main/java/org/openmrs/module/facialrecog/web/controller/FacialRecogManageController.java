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
package org.openmrs.module.facialrecog.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.PersonName;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.facialrecog.api.FacialRecogService;
import org.openmrs.module.facialrecog.web.utils.WebConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The main controller.
 */
@Controller
public class  FacialRecogManageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	private String faceImageData;

	@RequestMapping(value = "/module/facialrecog/facehome.list", method = RequestMethod.GET)
	public void view(ModelMap model) {

		model.addAttribute("user", Context.getAuthenticatedUser());
	}

	@RequestMapping(value = "/module/facialrecog/identify.json", method = RequestMethod.POST)
	public void setFaceImageData(@RequestBody String encodedImage) {
		faceImageData = encodedImage;
	}

	@RequestMapping(value = "/module/facialrecog/identify.json", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> identify() {
		FacialRecogService facialRecogService = Context.getService(FacialRecogService.class);
		Patient patient = facialRecogService.identify(faceImageData);
		return WebConverter.convertPatient(patient);
	}

	@RequestMapping(value="module/facialrecog/searchpatient.json", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> searchPatient(@RequestParam(value="patientId") String identifier){
		PatientService patientService = Context.getPatientService();
		Boolean includeVoided = false;
		List<Patient> patients = patientService.getPatientsByIdentifier(identifier, includeVoided);
		if(patients.size() == 1){
			return WebConverter.convertPatient(patients.get(0));
		}
		return WebConverter.convertPatient(null);
	}

}
