package com.doit.api.project;

import com.doit.api.item.ItemService;
import com.doit.api.user.User;
import com.doit.api.user.UserRepository;
import com.doit.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ItemService itemService;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, @Lazy ItemService itemService, UserRepository userRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.itemService = itemService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public String addProject(ProjectRequest request, long id) {
        if (request == null) {
            return "Please insert Project";
        }
        boolean userExists = userRepository.findById(id).isPresent();
        if (!userExists) {
            return "NO USER";
        }
        User user = userService.getUser(id);
        Project project = Project.builder()
                .name(request.getName())
                .user(user)
                .build();
        boolean projectExists = userService.projectExists(user, project.getName());
        if (projectExists) {
            return ("CONFLICT");
        }
        user.addProject(project);
        if (project.getName().length() > 25) {
            return "TOO LARGE";
        } else {
            projectRepository.save(project);
            userRepository.save(user);
            return "ADDED";
        }
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(long id) {
        Project project = getProjects().stream()
                .filter(t -> id == t.getId())
                .findFirst()
                .orElse(null);
        return project;
    }

    public Project updateProjectName(Project project) {
        Long id = project.getId();
        Project myProject = getProject(id);
        myProject.setName(project.getName());
        projectRepository.save(myProject);
        return (myProject);
    }

    public boolean deleteProject(long id) {
        projectRepository.deleteById(id);
        return true;
    }

    public boolean itemExists(Project project, String name) {
        return project.getItems().stream().anyMatch(p -> p.getName().equals(name));
    }
}
