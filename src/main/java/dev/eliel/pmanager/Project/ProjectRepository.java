package dev.eliel.pmanager.Project;

import dev.eliel.pmanager.Project.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, String> {

    // Filtrar por nome do projeto (parcial, case insensitive)
    List<ProjectModel> findByNameContainingIgnoreCase(String name);

    // Filtrar por memberID do projeto
    //@Query("SELECT p FROM ProjectModel p JOIN p.members m WHERE m.id = :memberId")
    List<ProjectModel> findByMembersId(String memberId);

}
