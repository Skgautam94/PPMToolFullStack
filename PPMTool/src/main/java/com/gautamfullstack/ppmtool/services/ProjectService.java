package com.gautamfullstack.ppmtool.services;

import com.gautamfullstack.ppmtool.domain.Project;
import java.util.Optional;

public interface ProjectService {

    Project saveProject(Project project);

    Optional<Project> findByProjectIdentifier(String projectIdentifier);

    Iterable<Project> findAll();

    void deleteProject(String id);

    void updateProject(Project project);

}
