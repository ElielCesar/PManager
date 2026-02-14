package dev.eliel.pmanager.domain.applicationService;

import dev.eliel.pmanager.domain.entity.Project;
import dev.eliel.pmanager.domain.infraestructure.dto.ProjectDTO;
import dev.eliel.pmanager.domain.model.ProjectStatus;
import dev.eliel.pmanager.domain.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // cria o construtor das dependencias com final
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectDTO createProject(ProjectDTO dto){

        Project projeto = Project
                .builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .initialDate(dto.getInitialDate())
                .finalDate(dto.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        Project projetoSalvo = projectRepository.save(projeto);
        return ProjectDTO.convertToDTO(projetoSalvo);
    }

    public ProjectDTO loadProject(String id){
        Project projeto = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));
        return ProjectDTO.convertToDTO(projeto);
    }

    public List<ProjectDTO> loadAllProject(){
       List<Project> projects = projectRepository.findAll();
       return projects.stream()
               .map(ProjectDTO::convertToDTO)
               .toList();
    }

    @Transactional
    public void excludeProject(String id){
        projectRepository.deleteById(id);
    }

    @Transactional
    public ProjectDTO updateProject(String id, ProjectDTO dto){
        Project projeto = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        projeto.setName(dto.getName());
        projeto.setDescription(dto.getDescription());
        projeto.setInitialDate(dto.getInitialDate());
        projeto.setFinalDate(dto.getFinalDate());

        try{
            projeto.setStatus(ProjectStatus.valueOf(dto.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e){
            throw new RuntimeException("Status inválido! Use: PENDING, IN_PROGRESS ou FINISHED");
        }

        Project salvo = projectRepository.save(projeto);
        return ProjectDTO.convertToDTO(salvo);
    }

}
