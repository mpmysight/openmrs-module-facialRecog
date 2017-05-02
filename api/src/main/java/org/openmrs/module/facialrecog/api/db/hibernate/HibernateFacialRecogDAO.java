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
package org.openmrs.module.facialrecog.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.facialrecog.api.db.FacialRecogDAO;
import org.openmrs.module.facialrecog.api.model.FacialRecogData;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * It is a default implementation of  {@link FacialRecogDAO}.
 */
public class HibernateFacialRecogDAO implements FacialRecogDAO {
	@Autowired
//	protected final Log log = LogFactory.getLog(this.getClass());
//	private SessionFactory sessionFactory;
	protected SessionFactory sessionFactory;
	protected Class mappedClass =FacialRecogData.class;

	public HibernateFacialRecogDAO(){
		super();
	}
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }

	public FacialRecogData getById(Integer id){
		return (FacialRecogData)sessionFactory.getCurrentSession().get(mappedClass,id);
	}

	public FacialRecogData getByUuid(String uuid){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mappedClass);
		criteria.add(Restrictions.eq("uuid", uuid));
		criteria.add(Restrictions.eq("voided", Boolean.FALSE));
		return (FacialRecogData)criteria.uniqueResult();
	}

	public FacialRecogData getByFilePath(String filePath){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mappedClass);
		criteria.add(Restrictions.eq("filePath", filePath));
		criteria.add(Restrictions.eq("voided", Boolean.FALSE));
		return (FacialRecogData)criteria.uniqueResult();
	}

	public List<FacialRecogData> getAll(){;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mappedClass);
		return (List<FacialRecogData>) criteria.list();
	}

	public FacialRecogData saveOrUpdate(FacialRecogData object){
		sessionFactory.getCurrentSession().saveOrUpdate(object);
		return object;
	}

	public FacialRecogData update(FacialRecogData object){
		sessionFactory.getCurrentSession().update(object);
		return object;
	}

	public void delete(FacialRecogData object){
		sessionFactory.getCurrentSession().delete(object);
	}

	public Number count(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(mappedClass);
		criteria.add(Restrictions.eq("voided", Boolean.FALSE));
		criteria.setProjection(Projections.rowCount());
		return (Number) criteria.uniqueResult();
	}
}