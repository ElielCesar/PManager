package dev.eliel.pmanager.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
