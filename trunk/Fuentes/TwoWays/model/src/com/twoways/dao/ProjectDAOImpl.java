package com.twoways.dao;

import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.LanguaguesAcronymsTO;
import com.twoways.to.LanguaguesTO;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.ProjectAssignmentsTO;
import com.twoways.to.ProjectsTO;
import com.twoways.to.RatesTO;
import com.twoways.to.TranslatorsLanguaguesTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;


public class ProjectDAOImpl extends AbstractDAO implements ProjectDAO {
    public ProjectDAOImpl() {
    }

    public ProjectsTO getProjectById(Long proId) throws Exception {
        ProjectsTO projectsTO = 
            (ProjectsTO)getSqlMapClientTemplate().queryForObject("getProjectById", 
                                                                 proId);
        return projectsTO;
    }

    public ProjectsTO getProjectByOrdId(Long ordId) throws Exception {
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

    public ProjectAssignmentsTO insertProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO) throws Exception {
        Long praId = 
            (Long)getSqlMapClientTemplate().queryForObject("projectAss.seq", 
                                                           "");
        projectAssignmentsTO.setPraId(praId);
        getSqlMapClientTemplate().insert("insertProjectAssigment", 
                                         projectAssignmentsTO);
        return getProjectAssignmentsById(praId);
    }

    public ProjectAssignmentsTO updateProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO) throws Exception {
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


    public List<ProjectAssignmentsTO> getProjectAssignmentsByProId(Long proId) throws Exception {

        List<ProjectAssignmentsTO> projectAssignmentsTO = 
            getSqlMapClientTemplate().queryForList("getProjectAssignamentByProId", 
                                                   proId);
        return projectAssignmentsTO;
    }

    public void insertProjectAssignamentDetails(ProAssigmentsDetailsTO proAssigmentsDetailsTO, 
                                                List<EmployeesRatesTO> employeesRatesTOList) throws Exception {

        for (EmployeesRatesTO employeesRatesTO: employeesRatesTOList) {

            Long praaId = 
                (Long)getSqlMapClientTemplate().queryForObject("projectAssDet.seq", 
                                                               "");

            proAssigmentsDetailsTO.setPadId(praaId);
            proAssigmentsDetailsTO.setEmployeesRatesTO(employeesRatesTO);
            ProAssigmentsDetailsTO proAssigmentsDetailsTOOld = 
                getProjectAssignmentsDetailsById(proAssigmentsDetailsTO);
            if (proAssigmentsDetailsTOOld == null) {
                getSqlMapClientTemplate().insert("insertProjectAssigmentDetails", 
                                                 proAssigmentsDetailsTO);
            } else {
                proAssigmentsDetailsTOOld.setPranslatorsLanguaguesTO(proAssigmentsDetailsTO.getPranslatorsLanguaguesTO());
                getSqlMapClientTemplate().update("updateProjectAssigmentDetailsLanguage", 
                                                 proAssigmentsDetailsTOOld);
            }

            //   
        }

    }
    /*
    public List<ProAssigmentsDetailsTO> getProjectAssignmentsDetailsById(Long praId) throws Exception {

         List<ProAssigmentsDetailsTO> proAssigmentsDetailsTO =
            ( List<ProAssigmentsDetailsTO>)getSqlMapClientTemplate().queryForList("getProjectAssigmentDetailsByPraId",
                                                                 praId);
        return proAssigmentsDetailsTO;
    }

   */

    public List<ProAssigmentsDetailsTO> getProjectAssignmentsDetailsById(Long praId) throws Exception {

        
        
        List<ProAssigmentsDetailsTO> result = 
            new ArrayList<ProAssigmentsDetailsTO>();
        List<Map> proAssigmentsDetailsTOListMap = 
            (List<Map>)getSqlMapClientTemplate().queryForList("getProjectAssigmentDetailsByPadMap", 
                                                              praId);

        Iterator iterator = proAssigmentsDetailsTOListMap.iterator();
        while (iterator.hasNext()) {
            Map mapResult = (Map)iterator.next();

            ProAssigmentsDetailsTO proAssigmentsDetailsTO = 
                new ProAssigmentsDetailsTO();
            proAssigmentsDetailsTO.setPadId(Long.parseLong(mapResult.get("PAD_ID").toString()));
           if (mapResult.get("TRANSLATORS_LANGUAGUES_TLA_ID") != null && !mapResult.get("TRANSLATORS_LANGUAGUES_TLA_ID").toString().equals("0")) {
                proAssigmentsDetailsTO.setPranslatorsLanguaguesTO(new TranslatorsLanguaguesTO());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().setTlaId(Long.parseLong(mapResult.get("TRANSLATORS_LANGUAGUES_TLA_ID").toString()));
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().setLangAcronymsTO(new LanguaguesAcronymsTO());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO().setLanguaguesTO(new LanguaguesTO());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO().getLanguaguesTO().setLanName(mapResult.get("LEN1").toString());
                if (mapResult.get("ACRO1") != null) {
                    proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO().setLaaAcronym(mapResult.get("ACRO1").toString());
                }
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().setLangAcronymsTO1(new LanguaguesAcronymsTO());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().setLanguaguesTO(new LanguaguesTO());
                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().getLanguaguesTO().setLanName(mapResult.get("LEN2").toString());
                if (mapResult.get("ACRO2") != null) {
                   proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().setLaaAcronym(mapResult.get("ACRO2").toString());
                }
            }
            proAssigmentsDetailsTO.setProjectAssignmentsTO(new ProjectAssignmentsTO());
            proAssigmentsDetailsTO.getProjectAssignmentsTO().setPraId(Long.parseLong(mapResult.get("PROJECT_ASSIGNMENTS_PRA_ID").toString()));
            proAssigmentsDetailsTO.setOrdersDocsTO(new OrdersDocsTO());
            proAssigmentsDetailsTO.getOrdersDocsTO().setOdoId(Long.parseLong(mapResult.get("ORDERS_DOCS_ODO_ID").toString()));
            proAssigmentsDetailsTO.getOrdersDocsTO().setOrdersOrdId(Long.parseLong(mapResult.get("ORDERS_DOCS_ORDERS_ORD_ID").toString()));
            proAssigmentsDetailsTO.getOrdersDocsTO().setOdoName(mapResult.get("DOCNAME").toString());
            if (mapResult.get("PAD_WCOUNT") != null && 
                mapResult.get("PAD_WCOUNT").toString().length() > 0) {
                proAssigmentsDetailsTO.setPadWCount(Double.parseDouble(mapResult.get("PAD_WCOUNT").toString()));
            }
            proAssigmentsDetailsTO.setEmployeesRatesTO(new EmployeesRatesTO());
            proAssigmentsDetailsTO.getEmployeesRatesTO().setEmployeesTO(new EmployeesTO());
            proAssigmentsDetailsTO.getEmployeesRatesTO().getEmployeesTO().setEmpId(Long.parseLong(mapResult.get("EMPLOYEES_RATES_EMPLOYEES_EMP_").toString()));
            proAssigmentsDetailsTO.getEmployeesRatesTO().setRatesTO(new RatesTO());
            proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().setRatId(Long.parseLong(mapResult.get("EMPLOYEES_RATES_RATES_RAT_ID").toString()));
            proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().setRatName(mapResult.get("RAT_NAME").toString());
            
            if (mapResult.get("CUR_ID") != null &&  mapResult.get("CUR_ID").toString().length() > 0) {
                proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().setCurrencyTO(new CurrencyTO());
                proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().getCurrencyTO().setCurId(Long.parseLong(mapResult.get("CUR_ID").toString()));
                proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().getCurrencyTO().setCurName(mapResult.get("CUR_NAME").toString());
                proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().getCurrencyTO().setCurSymbol(mapResult.get("CUR_SYMBOL").toString());
            }    
            if (mapResult.get("PAD_RATE") != null && 
                mapResult.get("PAD_RATE").toString().length() > 0) {
                proAssigmentsDetailsTO.setPadRate(Double.parseDouble(mapResult.get("PAD_RATE").toString()));
            }
            result.add(proAssigmentsDetailsTO);


        }

        return result;
    }


    public void deleteProjectAssignamentDetails(ProAssigmentsDetailsTO detail) {

        getSqlMapClientTemplate().delete("deleteProjectAssigmentDetails", 
                                         detail);

    }

    public ProAssigmentsDetailsTO getProjectAssignmentsDetailsById(ProAssigmentsDetailsTO detail) {

        return (ProAssigmentsDetailsTO)getSqlMapClientTemplate().queryForObject("getProjectAssigmentDetailsByPad", 
                                                                                detail);

    }


    public Long findProjectAssignament(String praDate, Long emp, String serv, 
                                       String proId) {

        Map params = new HashMap();
        params.put("praDate", 
                   (praDate.length() < 10) ? praDate + " 00:00 " : praDate);
        params.put("emp", emp);
        params.put("serv", serv);
        params.put("proId", proId);

        return (Long)getSqlMapClientTemplate().queryForObject("findProjectAssignament", 
                                                              params);

    }


    public void deleteProjectAssigmentDetailsByPraId(Map params) throws Exception {
        getSqlMapClientTemplate().delete("deleteProjectAssigmentDetailsByPraId", 
                                         params);
    }

    public void deleteProjectAssigment(Map params) {
        
        getSqlMapClientTemplate().delete("deleteProjectAssigmentDetailsByAssingPraId", 
                                         params.get("praId").toString());
        
        getSqlMapClientTemplate().delete("deleteProjectAssigmentByPraId", 
                                         params);
    }

    public Long findProjectAssignament(String praDate, Long emp) {
        Map params = new HashMap();
        params.put("praDate", 
                   (praDate.length() < 10) ? praDate + " 00:00 " : praDate);
        params.put("empId", emp);
       

        return (Long)getSqlMapClientTemplate().queryForObject("findProjectAssignamentAvailability", 
                                                              params);

    }
    
    
    
    public void  updateProjectAssigmentFromDetails(ProjectAssignmentsTO projectAssignmentsTO){
        getSqlMapClientTemplate().update("updateProjectAssigmentFromDetails",projectAssignmentsTO);   
    }
    
    
    public void updateProjectAssigmentDetailsByPadId(ProAssigmentsDetailsTO proAssigmentsDetailsTO){
        getSqlMapClientTemplate().update("updateProjectAssigmentDetailsByPadId",proAssigmentsDetailsTO); 
    }
    
    public List getProjectAssignmentsByEmpId(Long empId, String mesId, String anioId) throws Exception {
        
        Map params = new HashMap();
        params.put("mesId",mesId); 
        params.put("empId", empId);        
        params.put("anioId", anioId);
        
        List ret= null;
        try {
            ret = getSqlMapClientTemplate().queryForList("getProjectAssignamentByEmpId", 
                                                                           params);
        
        }catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
}

