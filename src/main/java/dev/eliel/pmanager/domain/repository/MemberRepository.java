package dev.eliel.pmanager.domain.repository;

import dev.eliel.pmanager.domain.entity.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberModel, String> {

    Optional<MemberModel> findByIdAndDeleted(String id, boolean deleted);

    Optional<MemberModel> findByEmailAndDeleted(String email, boolean deleted);

}
