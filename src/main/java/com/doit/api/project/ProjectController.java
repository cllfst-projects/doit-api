package com.doit.api.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity<String> postProject(@RequestBody ProjectRequest projectRequest, @PathVariable long id) {
        String response = projectService.addProject(projectRequest,id);
        if (response == "ADDED")
            return new ResponseEntity<>("ADDED", HttpStatus.OK);
        else if (response == "CONFLICT")
            return new ResponseEntity<>("Project already exists", HttpStatus.CONFLICT);
        else if (response == "NO USER")
            return new ResponseEntity<>("User does not exist", HttpStatus.CONFLICT);
        else
            return new ResponseEntity<>("Project content too large", HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Project>> getProjects() {
        return new ResponseEntity<>(projectService.getProjects(), HttpStatus.OK);
    }

    @GetMapping(path = "/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable long projectId) {
        Project project = projectService.getProject(projectId);
        if (project != null) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        }
        return new ResponseEntity<>(project, HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Project> updateProjectName(@RequestBody Project project) {
        Project newProject = projectService.updateProjectName(project);
        return new ResponseEntity<>(newProject, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable long id) {
        if (projectService.deleteProject(id)) {
            return new ResponseEntity<>("project deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("project not found", HttpStatus.NOT_FOUND);
    }

}
