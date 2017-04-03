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
package org.openmrs.module.facialrecog.api.db;

import org.openmrs.module.facialrecog.api.FacialRecogService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.openmrs.module.facialrecog.api.model.FacialRecogData;

/**
 *  Database methods for {@link FacialRecogService}.
 */
public interface FacialRecogDAO {
	
	/*
	 * Add DAO methods here
	 */

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    FacialRecogData getById(Integer id);

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    FacialRecogData getByUuid(String uuid);

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    List<FacialRecogData> getAll();

    /*@SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    List<FacialRecogData> getByScheduled(Boolean scheduled);*/

    @Transactional
    FacialRecogData saveOrUpdate(FacialRecogData object);

    @Transactional
    FacialRecogData update(FacialRecogData object);

    @Transactional
    void delete(FacialRecogData object);

    Number count();
}