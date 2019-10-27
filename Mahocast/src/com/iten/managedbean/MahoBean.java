package com.iten.managedbean;
import java.util.List;
//import org.springframework.beans.BeanUtils;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import com.iten.businessImpl.MahoBusinessImpl;

@ManagedBean
public class MahoBean {
 
    private int id;
    private String name;    
    private String editSchoolId;
    
    // Method To Fetch The Existing School List From The Database
    public List<MahoBean> selectMahoList() {
        return MahoBusinessImpl.getAllSchoolDetails();        
    }
 
    // Method To Add New School To The Database
    public String addNewMaho(MahoBean mahoBean) {
        return MahoBusinessImpl.createNewSchool(mahoBean.getName());        
    }
 
    // Method To Delete The School Details From The Database
    public String deleteSchoolById(int mahoId) {      
        return MahoBusinessImpl.deleteSchoolDetails(mahoId);        
    }
 
    // Method To Navigate User To The Edit Details Page And Passing Selecting School Id Variable As A Hidden Value
    public String editSchoolDetailsById() {
        editSchoolId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedSchoolId");     
        return "EditMaho.xhtml";
    }
 
    // Method To Update The School Details In The Database
    public String updateSchoolDetails(MahoBean mahoBean) {
        return MahoBusinessImpl.updateSchoolDetails(Integer.parseInt(mahoBean.getEditSchoolId()), mahoBean.getName());        
    }
    
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEditSchoolId() {
        return editSchoolId;
    }
 
    public void setEditSchoolId(String editSchoolId) {
        this.editSchoolId = editSchoolId;
    }
 
   
}