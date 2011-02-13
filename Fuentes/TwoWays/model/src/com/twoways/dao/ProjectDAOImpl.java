package com.twoways.dao;

import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.EmployeesRatesProjTO;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.LanguaguesAcronymsTO;
import com.twoways.to.LanguaguesTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.ProjectAssignmentsTO;
import com.twoways.to.ProjectsTO;

import com.twoways.to.TranslatorsLanguaguesTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import java.util.Iterator;

public class ProjectDAOImpl extends AbstractDAO implements ProjectDAO {
    public ProjectDAOImpl() {
    }

    public ProjectsTO getProjectById(Long proId) throws Exception {
        ProjectsTO projectsTO = 
            (ProjectsTO)getSqlMapClientTemplate().queryForObject("getProjectById", 
                                                                 proId);
        return projectsTO;
    }
    
    public ProjectsTO getProjectByOrdId(Long ordId) throws Exception{
        ProjectsTO projectsTO = 
            (ProjectsTO)getSqlMapClientTemplate().queryForObject("getProjectByOrdId", 
                                                                 ordId);
        return projectsTO;
    }

    public ProjectsTO updateProject(ProjectsTO projectsTO) {

        return (ProjectsTO)getSqlMapClientTemplate().insert("updateProject", 
                                                            projectsTO);
    }

    public ProjectsTO insertProject(ProjectsTO projectsTO) throws Exception {

        Long proId = 
            (Long)getSqlMapClientTemplate().queryForObject("project.seq", "");
        projectsTO.setProId(proId);
        getSqlMapClientTemplate().insert("insertProject", projectsTO);
        return getProjectById(proId);
    }

    public ProjectAssignmentsTO insertProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO)throws Exception{
        Long praId = 
            (Long)getSqlMapClientTemplate().queryForObject("projectAss.seq", "");
        projectAssignmentsTO.setPraId(praId);
        getSqlMapClientTemplate().insert("insertProjectAssigment", projectAssignmentsTO);
        return getProjectAssignmentsById(praId);
    }

    public ProjectAssignmentsTO updateProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO) throws Exception{
        getSqlMapClientTemplate().insert("updateProjectAssigment", 
                                                            projectAssignmentsTO);
        return getProjectAssignmentsById(projectAssignmentsTO.getPraId());
    }

    public ProjectAssignmentsTO getProjectAssignmentsById(Long praId) throws Exception {
    
        ProjectAssignmentsTO projectAssignmentsTO = 
            (ProjectAssignmentsTO)getSqlMapClientTemplate().queryForObject("getProjectAssignamentByPraId", 
                                                                 praId);
        return projectAssignmentsTO;
    }
    
    
    public List <ProjectAssignmentsTO> getProjectAssignmentsByProId(Long proId) throws Exception {
    
        List <ProjectAssignmentsTO> projectAssignmentsTO = getSqlMapClientTemplate().queryForList("getProjectAssignamentByProId", 
                                                                 proId);
        return projectAssignmentsTO;
    }

    public void insertProjectAssignamentDetails(ProAssigmentsDetailsTO proAssigmentsDetailsTO,List<EmployeesRatesTO> employeesRatesTOList) throws Exception {
        
        for(EmployeesRatesTO employeesRatesTO : employeesRatesTOList){     
      
            Long praaId = 
                (Long)getSqlMapClientTemplate().queryForObject("projectAssDet.seq", "");
            
            proAssigmentsDetailsTO.setPadId(praaId);
            proAssigmentsDetailsTO.setEmployeesRatesTO(employeesRatesTO);
            ProAssigmentsDetailsTO proAssigmentsDetailsTOOld =getProjectAssignmentsDetailsById(proAssigmentsDetailsTO);
            if(proAssigmentsDetailsTOOld==null ){
                getSqlMapClientTemplate().insert("insertProjectAssigmentDetails", proAssigmentsDetailsTO);
            }else{
                proAssigmentsDetailsTOOld.setPranslatorsLanguaguesTO(proAssigmentsDetailsTO.getPranslatorsLanguaguesTO());
                getSqlMapClientTemplate().update("updateProjectAssigmentDetailsLanguage", proAssigmentsDetailsTOOld);   
            }
            
         //   
        }
        
    }

    /*public List<ProAssigmentsDetailsTO> getProjectAssignmentsDetailsById(Long praId) throws Exception {
    
         List<ProAssigmentsDetailsTO> proAssigmentsDetailsTO = 
            ( List<ProAssigmentsDetailsTO>)getSqlMapClientTemplate().queryForList("getProjectAssigmentDetailsByPraId", 
                                                                 praId);
        return proAssigmentsDetailsTO;
    }
    */
    
    
    public List<ProAssigmentsDetailsTO> getProjectAssignmentsDetailsById(Long praId) throws Exception {
    
         
         List<Map> proAssigmentsDetailsTOList = 
            ( List<Map>)getSqlMapClientTemplate().queryForList("getProjectAssigmentDetailsByPadMap", 
                                                                 praId);
                                                                 
        Iterator iterator = proAssigmentsDetailsTOList.iterator();
        while	(iterator.hasNext()) {
            Map mapResult = (Map) iterator.next();
            
                ProAssigmentsDetailsTO proAssigmentsDetailsTO = new  ProAssigmentsDetailsTO();
                proAssigmentsDetailsTO.setPadId(Long.parseLong(mapResult.get("PAD_ID").toString()));
                proAssigmentsDetailsTO.setPranslatorsLanguaguesTO(new TranslatorsLanguaguesTO());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().setTlaId(Long.parseLong(mapResult.get("TRANSLATORS_LANGUAGUES_TLA_ID").toString()));
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().setLangAcronymsTO(new LanguaguesAcronymsTO ());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO().setLanguaguesTO(new LanguaguesTO());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO().getLanguaguesTO().setLanName("LEN1");
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().setLangAcronymsTO1(new LanguaguesAcronymsTO ());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().setLanguaguesTO(new LanguaguesTO());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().getLanguaguesTO().setLanName("LEN2");
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().setLaaAcronym(mapResult.get("PAD_ID").toString());
                proAssigmentsDetailsTO.setPadId(Long.parseLong(mapResult.get("PAD_ID").toString()));
                proAssigmentsDetailsTO.setPadId(Long.parseLong(mapResult.get("PAD_ID").toString()));
                proAssigmentsDetailsTO.setPadId(Long.parseLong(mapResult.get("PAD_ID").toString()));
                proAssigmentsDetailsTO.setPadId(Long.parseLong(mapResult.get("PAD_ID").toString()));
                proAssigmentsDetailsTO.setPadId(Long.parseLong(mapResult.get("PAD_ID").toString()));
                proAssigmentsDetailsTO.setPadId(Long.parseLong(mapResult.get("PAD_ID").toString()));
                proAssigmentsDetailsTO.setPadId(Long.parseLong(mapResult.get("PAD_ID").toString()));
              
                
              <result property="pranslatorsLanguaguesTO.langAcronymsTO.languaguesTO.lanName" column="LEN1" />  
              <result property="pranslatorsLanguaguesTO.langAcronymsTO1.languaguesTO.lanName" column="LEN12" />  
              <result property="pranslatorsLanguaguesTO.langAcronymsTO1.laaAcronym" column="ACRO2" />  
              <result property="pranslatorsLanguaguesTO.langAcronymsTO.laaAcronym" column="1" />  
              <result property="projectAssignmentsTO.praId"   column="PROJECT_ASSIGNMENTS_PRA_ID" />
              <result property="ordersDocsTO.odoId" column="ORDERS_DOCS_ODO_ID" />     
              <result property="padWCount" column="PAD_WCOUNT" />     
              <result property="ordersDocsTO.ordersOrdId" column="ORDERS_DOCS_ORDERS_ORD_ID" />  
              <result property="ordersDocsTO.odoName" column="DOCNAME"  />  
              <result property="employeesRatesTO.ratesTO.ratId" column="EMPLOYEES_RATES_RATES_RAT_ID" />
              <result property="employeesRatesTO.ratesTO.ratName" column="RAT_NAME" />
              <result property="employeesRatesTO.employeesTO.empId" column="EMPLOYEES_RATES_EMPLOYEES_EMP_" />     
              <result property="padRate" column="PAD_RATE" /> 
          </resultMap>
          
               
               System.out.println(object); 
           
            
            
            
        }
        
        return new ArrayList();*/
    }
    


    public void deleteProjectAssignamentDetails(ProAssigmentsDetailsTO detail) {
    
       getSqlMapClientTemplate().delete("deleteProjectAssigmentDetails",detail); 
        
    }
    
    public  ProAssigmentsDetailsTO getProjectAssignmentsDetailsById(ProAssigmentsDetailsTO detail) {
    
       return (ProAssigmentsDetailsTO) getSqlMapClientTemplate().queryForObject("getProjectAssigmentDetailsByPad",detail); 
        
    }


    public Long findProjectAssignament(String praDate, Long emp, 
                                          String serv, String proId) {
                                    
        Map params= new HashMap();
        params.put("praDate",(praDate.length() <10)? praDate +" 00:00 ":praDate);
        params.put("praDate",(praDate.length() <10)? praDate +" 00:00 ":praDate);
        params.put("emp",emp);
        params.put("serv",serv);
        params.put("proId",proId);

        return(Long) getSqlMapClientTemplate().queryForObject("findProjectAssignament",params); 
        
    }
    
    
    public void deleteProjectAssigmentDetailsByPraId(Map params)throws Exception{
        getSqlMapClientTemplate().delete("deleteProjectAssigmentDetailsByPraId",params);
    }
}

