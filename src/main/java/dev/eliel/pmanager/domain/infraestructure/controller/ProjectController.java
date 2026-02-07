package dev.eliel.pmanager.domain.infraestructure.controller;

import dev.eliel.pmanager.domain.applicationService.ProjectService;
import dev.eliel.pmanager.domain.entity.Project;
import dev.eliel.pmanager.domain.infraestructure.dto.ProjectDTO;
import dev.eliel.pmanager.domain.repository.ProjectRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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



}
