package dev.eliel.pmanager.Task;

import dev.eliel.pmanager.Member.MemberModel;
import dev.eliel.pmanager.Member.MemberRepository;
import dev.eliel.pmanager.Project.ProjectModel;
import dev.eliel.pmanager.Project.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TaskDTO createTask(TaskDTO task){
        // Toda tarefa deve ter um projeto associado.
        ProjectModel project = null;
        if(task.getProjectID() != null){
            project = projectRepository.findById(task.getProjectID())
                    .orElseThrow(()-> new RuntimeException("Projeto não encontrado, informe um projeto válido!"));
        }

        // Toda tarefa deve ter pelo menos 1 membro associado.
        MemberModel member = null;
        if(task.getMemberID() != null){
            member = memberRepository.findById(task.getMemberID())
                    .orElseThrow(()-> new RuntimeException("Membro não encontrado, informe um membro válido!"));
        }

        TaskModel taskBuilder = TaskModel
                .builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .numberOfDays(task.getNumberOfDays())
                .status(TaskStatus.PENDING)
                .project(project)
                .member(member)
                .build();

        TaskModel taskSalva = taskRepository.save(taskBuilder);
        return TaskDTO.convertToDTO(taskSalva);
    }

    public TaskDTO loadTask(String id){
        TaskModel task = taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task não encontrada!"));
        return TaskDTO.convertToDTO(task);
    }

    public Page<TaskDTO> loadAllTasks(Pageable pageable){
        return taskRepository.findAll(pageable)
                .map(TaskDTO::convertToDTO);
    }

    @Transactional
    public void deleteTask(String id){
        taskRepository.deleteById(id);
    }

    @Transactional
    public TaskDTO updateTask(String id, TaskDTO task){
        TaskModel taskAntiga = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));

        taskAntiga.setTitle(task.getTitle());
        taskAntiga.setDescription(task.getDescription());
        taskAntiga.setNumberOfDays(task.getNumberOfDays());

        if(task.getStatus() != null){
            try {
                taskAntiga.setStatus(TaskStatus.valueOf(task.getStatus().name().toUpperCase()));
            } catch (IllegalArgumentException e){
                throw new RuntimeException("Status inválido! Use: PENDING, IN_PROGRESS ou FINISHED");
            }
        }
        // atualizar project se enviado
        if(task.getProjectID() != null){
            ProjectModel project = projectRepository.findById(task.getProjectID())
                    .orElseThrow(()-> new RuntimeException("Projeto não encontrado, informe um projeto válido!"));
            taskAntiga.setProject(project);
        }

        // atualizar member se enviado
        if(task.getMemberID() != null){
            MemberModel member = memberRepository.findById(task.getMemberID())
                    .orElseThrow(()-> new RuntimeException("Membro não encontrado, informe um membro válido!"));
            taskAntiga.setMember(member);
        }

        TaskModel taskAtualizada = taskRepository.save(taskAntiga);
        return TaskDTO.convertToDTO(taskAtualizada);

    }

    public List<TaskDTO> taskFilter(TaskStatus status, String title, String description){
        List<TaskModel> tasks = new ArrayList<>();

        if(status != null){
            tasks = taskRepository.findByStatus(status);
        }

        if(title != null){
            tasks = taskRepository.findByTitleContainingIgnoreCase(title);
        }

        if (description != null){
            tasks = taskRepository.findByDescriptionContainingIgnoreCase(description);
        }

        return tasks
                .stream()
                .map(TaskDTO::convertToDTO)
                .toList();

    }

}
