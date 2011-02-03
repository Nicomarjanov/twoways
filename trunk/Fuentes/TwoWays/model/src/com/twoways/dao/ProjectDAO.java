package com.twoways.dao;

import com.twoways.to.ProjectsTO;

public interface ProjectDAO {
    public ProjectsTO getProjectById(Long proId) throws Exception;
    
    public ProjectsTO getProjectByOrdId(Long ordId) throws Exception;
    
    public ProjectsTO updateProject(ProjectsTO projectsTO) throws Exception;
    
    public ProjectsTO insertProject(ProjectsTO projectsTO) throws Exception;
}
