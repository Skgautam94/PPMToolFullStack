package com.gautamfullstack.ppmtool.controllers;

import com.gautamfullstack.ppmtool.Exceptions.ProjectNotFoundException;
import com.gautamfullstack.ppmtool.domain.Project;
import com.gautamfullstack.ppmtool.services.MapValidationErrorService;
import com.gautamfullstack.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/{id}")
    public Project findById(@PathVariable Long id) throws ProjectNotFoundException {
        Optional<Project> project = projectService.getOne(id);
        if(!project.isPresent()) {
            throw new ProjectNotFoundException("Project with id: "+ id+" Not found");
        }
        return project.get();
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null)
            return errorMap;
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
