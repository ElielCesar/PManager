package dev.eliel.pmanager.Project;

import dev.eliel.pmanager.Member.MemberModel;
import dev.eliel.pmanager.Project.ProjectModel;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class ProjectDTO {

    private final String id;

    @NotBlank(message = "Name é obrigatório!")
    private final String name;

    @NotBlank(message = "Description é obrigatório!")
    private final String description;

    @NotNull
    private final LocalDate initialDate;

    @NotNull
    private final LocalDate finalDate;

    private final String status;

    private final Set<String> membersIds;

    @AssertTrue(message = "A data final não pode ser anterior a data de inicio.")
    private boolean isInitialDateMenorFinalDate(){
        return initialDate.isBefore(finalDate);
    }


    public static ProjectDTO convertToDTO(ProjectModel project){
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getInitialDate(),
                project.getFinalDate(),
                project.getStatus().name(),
                Optional
                        .ofNullable(project.getMembers())
                        .orElse(List.of())
                        .stream()
                        .map(MemberModel::getId)
                        .collect(Collectors.toSet())

        );
    }
}
