package com.gautamfullstack.ppmtool.services;

import com.gautamfullstack.ppmtool.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project saveOrUpdateProject(Project project);

    Optional<Project> getOne(Long id);

    List<Project> getAll();

    void deleteOne(Long id);

}
