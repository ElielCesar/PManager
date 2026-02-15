package dev.eliel.pmanager.Member;

import dev.eliel.pmanager.Project.ProjectModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
@Builder
public class MemberModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String secret;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean deleted;

    @ManyToMany(mappedBy = "members") // dentro de ProjectModel
    private List<ProjectModel> projects = new ArrayList<>();

}
