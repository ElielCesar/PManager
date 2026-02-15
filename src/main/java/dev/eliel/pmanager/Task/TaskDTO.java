package dev.eliel.pmanager.Task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

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

    @NotBlank(message = "Status é obrigatório[PENDING, IN_PROGRESS, FINISHED]")
    private final TaskStatus status;

    public static TaskDTO convertToDTO(TaskModel task){
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getNumberOfDays(),
                task.getStatus()
        );
    }
}
