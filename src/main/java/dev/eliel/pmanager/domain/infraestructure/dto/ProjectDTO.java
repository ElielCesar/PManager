package dev.eliel.pmanager.domain.infraestructure.dto;

import dev.eliel.pmanager.domain.entity.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ProjectDTO {

    @NotBlank(message = "Name é obrigatório!")
    private final String name;

    @NotBlank(message = "Description é obrigatório!")
    private final String description;

    @NotNull
    private final LocalDate initialDate;

    @NotNull
    private final LocalDate finalDate;

    private final String status;


    public static ProjectDTO convertToDTO(Project project){
        return new ProjectDTO(
                project.getName(),
                project.getDescription(),
                project.getInitialDate(),
                project.getFinalDate(),
                project.getStatus().name()
        );
    }
}
