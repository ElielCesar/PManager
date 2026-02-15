package dev.eliel.pmanager.Project;

import dev.eliel.pmanager.Member.MemberModel;
import dev.eliel.pmanager.Member.MemberRepository;
import dev.eliel.pmanager.Project.ProjectModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor // cria o construtor das dependencias com final
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ProjectDTO createProject(ProjectDTO dto){
        List<MemberModel> members = new ArrayList<>();

        if(dto.getMembersIds() != null && !dto.getMembersIds().isEmpty()){
            members = memberRepository.findAllById(dto.getMembersIds());
        }

        ProjectModel projeto = ProjectModel
                .builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .initialDate(dto.getInitialDate())
                .finalDate(dto.getFinalDate())
                .status(ProjectStatus.PENDING)
                .members(members)
                .build();

        ProjectModel projetoSalvo = projectRepository.save(projeto);
        return ProjectDTO.convertToDTO(projetoSalvo);
    }

    public ProjectDTO loadProject(String id){
        ProjectModel projeto = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado!"));
        return ProjectDTO.convertToDTO(projeto);
    }

    public List<ProjectDTO> loadAllProject(){
       List<ProjectModel> projects = projectRepository.findAll();
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
        ProjectModel projeto = projectRepository.findById(id)
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

        // Atualizar members
        if(dto.getMembersIds() != null){
            List<MemberModel> members = memberRepository.findAllById(dto.getMembersIds());
            projeto.setMembers(members);
        }

        ProjectModel salvo = projectRepository.save(projeto);
        return ProjectDTO.convertToDTO(salvo);
    }

}
