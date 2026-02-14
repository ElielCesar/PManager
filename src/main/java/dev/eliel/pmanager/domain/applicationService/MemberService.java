package dev.eliel.pmanager.domain.applicationService;

import dev.eliel.pmanager.domain.entity.MemberModel;
import dev.eliel.pmanager.domain.infraestructure.dto.MemberDTO;
import dev.eliel.pmanager.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberModel createMember(MemberDTO dto){
        MemberModel member = MemberModel
                .builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .secret(UUID.randomUUID().toString())
                .deleted(false)
                .build();

        memberRepository.save(member);
        return member;
    }


}
