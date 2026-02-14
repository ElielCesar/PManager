package dev.eliel.pmanager.domain.infraestructure.dto;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    @NotBlank(message = "Name é um campo obrigatório!")
    @Size(min = 3, max = 80)
    private String name;

    @NotBlank(message = "Email é um campo obrigatório!")
    @Email(message = "Email inválido!")
    private String email;


}
