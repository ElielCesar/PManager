package dev.eliel.pmanager.Project;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
