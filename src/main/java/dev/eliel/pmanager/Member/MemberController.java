package dev.eliel.pmanager.Member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor // faz a injeção de dependência de atributos final
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberModel createMember(@Valid @RequestBody MemberDTO dto){
        return memberService.createMember(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO loadMemberID(@PathVariable String id){
        return memberService.loadMemberById(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable String id){
        memberService.deleteMemberByID(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> listAllMembers(){
        return  memberService.loadAllMembers();
    }

    @PatchMapping("/{id}")
    public MemberDTO updateMember(@PathVariable String id, @Valid @RequestBody MemberDTO dto){
        return memberService.updateMember(id, dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> findAllMembers(@RequestParam(name = "email", required = false) String email){
        return  memberService.findAllMembers(email);
    }

}
