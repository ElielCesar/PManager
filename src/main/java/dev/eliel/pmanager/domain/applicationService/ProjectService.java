package dev.eliel.pmanager.domain.applicationService;

import dev.eliel.pmanager.domain.entity.Project;
import dev.eliel.pmanager.domain.infraestructure.dto.ProjectDTO;
import dev.eliel.pmanager.domain.model.ProjectStatus;
import dev.eliel.pmanager.domain.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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



}
