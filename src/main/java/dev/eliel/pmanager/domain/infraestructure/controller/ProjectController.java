package dev.eliel.pmanager.domain.infraestructure.controller;

import dev.eliel.pmanager.domain.applicationService.ProjectService;
import dev.eliel.pmanager.domain.entity.Project;
import dev.eliel.pmanager.domain.infraestructure.dto.ProjectDTO;
import dev.eliel.pmanager.domain.repository.ProjectRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor // faz a injeção de dependência de atributos final
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO createProject(@Valid @RequestBody ProjectDTO dto){
        return projectService.createProject(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO listProject(@PathVariable String id){
        return  projectService.loadProject(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDTO> listProject(){
        return  projectService.loadAllProject();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id){
        projectService.excludeProject(id);
    }

    @PatchMapping("/{id}")
    public ProjectDTO updateProject(@PathVariable String id, @Valid @RequestBody ProjectDTO dto){
        return projectService.updateProject(id, dto);
    }

}
