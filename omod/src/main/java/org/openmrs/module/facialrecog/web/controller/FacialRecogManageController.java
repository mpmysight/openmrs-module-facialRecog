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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The main controller.
 */
@Controller
public class  FacialRecogManageController {
	
	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/facialrecog/facehome.list", method = RequestMethod.GET)
	public void view(ModelMap model) {
		log.error("Hello There");
		model.addAttribute("user", Context.getAuthenticatedUser());
	}

	/*@RequestMapping(value = "/module/facialrecog/facehome.list", method = RequestMethod.POST)
	public void save(@RequestParam("image") MultipartFile image, @RequestParam(value = "patientuuid") String patientUuid ) {
		FacialRecogService facialRecogService = Context.getService(FacialRecogService.class);
		PatientService patientService = Context.getPatientService();
		Patient patient = patientService.getPatientByUuid(patientUuid);
		if(patient != null){
			facialRecogService.save(image,patientUuid);
		} else {

		}
	}*/
	@RequestMapping(value = "/module/facialrecog/identify.json", method = RequestMethod.POST)
	public Map<String, Object> identify(@RequestParam("faceimagedata") String encodedImage) {
		FacialRecogService facialRecogService = Context.getService(FacialRecogService.class);
        Patient patient = facialRecogService.identify(encodedImage);
        return WebConverter.convertPatient(patient);
	}

}
