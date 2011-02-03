package com.twoways.dao;

import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.ProjectsTO;

import java.util.List;

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
}
