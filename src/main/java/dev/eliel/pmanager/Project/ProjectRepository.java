package dev.eliel.pmanager.Project;

import dev.eliel.pmanager.Project.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectModel, String> {


}
