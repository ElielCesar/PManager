package dev.eliel.pmanager.Task;

import dev.eliel.pmanager.Member.MemberDTO;
import dev.eliel.pmanager.Member.MemberModel;
import dev.eliel.pmanager.Project.ProjectDTO;
import dev.eliel.pmanager.Project.ProjectModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.lang.reflect.Member;
import java.util.Optional;

@Data
public class TaskDTO {

    private final String id;

    @NotBlank(message = "Titulo é obrigatório!")
    private final String title;

    @NotBlank(message = "Descrição é obrigatório!")
    private final String description;

    @NotNull(message = "Numero de dias é obrigatório!")
    @Positive(message = "O número deve ser maior ou igual a zero.")
    private final Integer numberOfDays;

    private final TaskStatus status;

    private final String projectID;

    private final String memberID;

    public static TaskDTO convertToDTO(TaskModel task){
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getNumberOfDays(),
                task.getStatus(),
                Optional.ofNullable(task.getProject())
                        .map(ProjectModel::getId)
                        .orElse(null),
                Optional.ofNullable(task.getMember())
                        .map(MemberModel::getId)
                        .orElse(null)
        );
    }
}
