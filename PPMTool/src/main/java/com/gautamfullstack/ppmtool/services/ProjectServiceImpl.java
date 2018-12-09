package com.gautamfullstack.ppmtool.services;

import com.gautamfullstack.ppmtool.Exceptions.ProjectIdException;
import com.gautamfullstack.ppmtool.Exceptions.ProjectNotFoundException;
import com.gautamfullstack.ppmtool.domain.Project;
import com.gautamfullstack.ppmtool.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception e) {
            throw new ProjectIdException("Project ID'" + project.getProjectIdentifier().toUpperCase()+"' already exist");
        }

    }

    @Override
    public Optional<Project> findByProjectIdentifier(String projectIdentifier){
        return projectRepository.findByProjectIdentifier(projectIdentifier);
    }

    @Override
    public Iterable<Project> findAll() {

        return  projectRepository.findAll();
    }

    @Override
    public void deleteProject(String id) {
        Optional<Project> project = projectRepository.findByProjectIdentifier(id);
        if(!project.isPresent()){
            throw new ProjectNotFoundException(id, "Project with ID:" +id+" does not exist");
        }
        projectRepository.delete(project.get());
    }

    @Override
    public void updateProject(Project project) {
        if(project.getId() != null) {
            projectRepository.save(project);
        }
    }
}

