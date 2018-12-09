package com.gautamfullstack.ppmtool.controllers;

import com.gautamfullstack.ppmtool.Exceptions.ProjectNotFoundException;
import com.gautamfullstack.ppmtool.domain.Project;
import com.gautamfullstack.ppmtool.services.MapValidationErrorService;
import com.gautamfullstack.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/{id}")
    public ResponseEntity<Project> findByIdentifier(@PathVariable String id) {
        return ResponseEntity.ok(projectService.findByProjectIdentifier(id.toUpperCase()).orElseThrow(() -> new ProjectNotFoundException(id.toUpperCase(), "Project  with ID " +id.toUpperCase()+" not found" )));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(errorMap != null)
            return errorMap;
        Project project1 = projectService.saveProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<Project> findAllProjects() {
        return projectService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByIdentifier(@PathVariable String id) {
        projectService.deleteProject(id.toUpperCase());
        return new ResponseEntity<>("Project with ID:"+id.toUpperCase()+" was deleted.",HttpStatus.OK);
    }
}
