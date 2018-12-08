package com.gautamfullstack.ppmtool.services;

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
    public Project saveOrUpdateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> getOne(Long id) {
        return null;
    }

    @Override
    public List<Project> getAll() {
        return null;
    }

    @Override
    public void deleteOne(Long id) {

    }
}
