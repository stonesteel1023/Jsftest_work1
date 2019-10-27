package com.iten.businessImpl;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.iten.entity.MahoEntity;
import com.iten.managedbean.MahoBean;

public class MahoBusinessImpl{

	private static final String PERSISTENCE_UNIT_NAME = "Mahocast List Book";	
	private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transactionObj = em.getTransaction();

	// Method To Fetch All School Details From The Database
	public static List<MahoBean> getAllSchoolDetails() {
		Query queryObj = em.createQuery("SELECT s FROM MahoEntity s");
		List<MahoBean> schoolList = queryObj.getResultList();
		if (schoolList != null && schoolList.size() > 0) {			
			return schoolList;
		} else {
			return null;
		}
	}

	// Method To Add Create School Details In The Database
	public static String createNewSchool(String name) {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}

		MahoEntity newSchoolObj = new MahoEntity();
		newSchoolObj.setId(getMaxSchoolId());
		newSchoolObj.setName(name);
		em.persist(newSchoolObj);
		transactionObj.commit();
		return "mahoList.xhtml?faces-redirect=true";	
	}

	// Method To Delete The Selected School Id From The Database 
	public static String deleteSchoolDetails(int mahoId) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		MahoEntity deleteSchoolObj = new MahoEntity();
		if(isSchoolIdPresent(mahoId)) {
			deleteSchoolObj.setId(mahoId);
			em.remove(em.merge(deleteSchoolObj));
		}
		transactionObj.commit();
		return "mahoList.xhtml?faces-redirect=true";
	}

	// Method To Update The School Details For A Particular School Id In The Database
	public static String updateSchoolDetails(int mahoId, String updatedSchoolName) {
		if (!transactionObj.isActive()) {
			transactionObj.begin();
		}

		if(isSchoolIdPresent(mahoId)) {
			Query queryObj = em.createQuery("UPDATE MahoEntity s SET s.name=:name WHERE s.id= :id");			
			queryObj.setParameter("id", mahoId);
			queryObj.setParameter("name", updatedSchoolName);
			int updateCount = queryObj.executeUpdate();
			if(updateCount > 0) {
				System.out.println("Record For Id: " + mahoId + " Is Updated");
			}
		}
		transactionObj.commit();
		FacesContext.getCurrentInstance().addMessage("editForm:mahoId", new FacesMessage("Record #" + mahoId + " Is Successfully Updated In Db"));
		return "EditMaho.xhtml";
	}

	// Helper Method 1 - Fetch Maximum School Id From The Database
	private static int getMaxSchoolId() {
		int maxSchoolId = 1;
		Query queryObj = em.createQuery("SELECT MAX(s.id)+1 FROM MahoEntity s");
		if(queryObj.getSingleResult() != null) {
			maxSchoolId = (Integer) queryObj.getSingleResult();
		}
		return maxSchoolId;
	}

	// Helper Method 2 - Fetch Particular School Details On The Basis Of School Id From The Database
	private static boolean isSchoolIdPresent(int mahoId) {
		boolean idResult = false;
		Query queryObj = em.createQuery("SELECT s FROM MahoEntity s WHERE s.id = :id");
		queryObj.setParameter("id", mahoId);
		MahoEntity selectedSchoolId = (MahoEntity) queryObj.getSingleResult();
		if(selectedSchoolId != null) {
			idResult = true;
		}
		return idResult;
	}
}