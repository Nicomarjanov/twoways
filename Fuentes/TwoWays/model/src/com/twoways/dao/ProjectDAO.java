package com.twoways.dao;

import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.ProjectAssignmentsTO;
import com.twoways.to.ProjectsTO;

import java.util.List;
import java.util.Map;

public interface ProjectDAO {
    public ProjectsTO getProjectById(Long proId) throws Exception;
    
    public ProjectsTO getProjectByOrdId(Long ordId) throws Exception;
    
    public ProjectsTO updateProject(ProjectsTO projectsTO) throws Exception;
    
    public ProjectsTO insertProject(ProjectsTO projectsTO) throws Exception;

    public ProjectAssignmentsTO updateProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO)throws Exception;
   

    public ProjectAssignmentsTO insertProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO)throws Exception;

    
    public void insertProjectAssignamentDetails(ProAssigmentsDetailsTO proAssigmentsDetailsTO,List<EmployeesRatesTO> employeesRatesTOList) throws Exception;

    public List<ProAssigmentsDetailsTO> getProjectAssignmentsDetailsById(Long praId)throws Exception;

    public ProjectAssignmentsTO getProjectAssignmentsById(Long Long)throws Exception;

    public void deleteProjectAssignamentDetails(ProAssigmentsDetailsTO detail)throws Exception;
    
    public List <ProjectAssignmentsTO> getProjectAssignmentsByProId(Long proId)throws Exception;

    public Long findProjectAssignament(String praDate, Long emp, 
                                          String serv, String proId)throws Exception;
                                          
    public void deleteProjectAssigmentDetailsByPraId(Map params)throws Exception ;

    public void deleteProjectAssigment(Map params)throws Exception ;
    
}
