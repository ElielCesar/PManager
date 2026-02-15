package dev.eliel.pmanager.Member;

import dev.eliel.pmanager.Project.ProjectModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String id;
    private String secret;

    @NotBlank(message = "Name é um campo obrigatório!")
    @Size(min = 3, max = 80)
    private String name;

    @NotBlank(message = "Email é um campo obrigatório!")
    @Email(message = "Email inválido!")
    private String email;

    private Boolean deleted;

    private Set<String> projectIds;

    public static MemberDTO convertToDTO(MemberModel member){
        return new MemberDTO(
                member.getId(),
                member.getSecret(),
                member.getName(),
                member.getEmail(),
                member.getDeleted(),
                Optional
                        .ofNullable(member.getProjects())
                        .orElse(List.of())
                        .stream()
                        .map(ProjectModel::getId)
                        .collect(Collectors.toSet())
        );
    }
}
