package dev.eliel.pmanager.Member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
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

    public MemberDTO loadMemberById(String memberID){
        MemberModel memberModel =  memberRepository.findByIdAndDeleted(memberID, false)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado!"));

        return MemberDTO.convertToDTO(memberModel);

    }

    @Transactional
    public void deleteMemberByID(String memberID){
        MemberModel memberModel = memberRepository.findByIdAndDeleted(memberID, false)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado!"));
        memberModel.setDeleted(true);
    }

    public List<MemberDTO> loadAllMembers(){
        return memberRepository.findByDeleted(false)
                .stream()
                .map(MemberDTO::convertToDTO)
                .toList();
    }

    @Transactional
    public MemberDTO updateMember(String memberID, MemberDTO dto){
        MemberModel memberModel = memberRepository.findByIdAndDeleted(memberID, false)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado!"));

        memberModel.setName(dto.getName());
        memberModel.setEmail(dto.getEmail());
        memberRepository.save(memberModel);
        return MemberDTO.convertToDTO(memberModel);

    }

    public List<MemberDTO> findAllMembers(String email){

        if(email == null){
            return memberRepository.findByDeleted(false)
                    .stream()
                    .map(MemberDTO::convertToDTO)
                    .toList();
        } else {
            return memberRepository.findByEmailAndDeleted(email, false)
                    .stream()
                    .map(MemberDTO::convertToDTO)
                    .toList();
        }
    }

}
