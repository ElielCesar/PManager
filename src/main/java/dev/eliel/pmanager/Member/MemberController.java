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
    public MemberModel createProject(@Valid @RequestBody MemberDTO dto){
        return memberService.createProject(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO listProject(@PathVariable String id){
        return  memberService.loadProject(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDTO> listProject(){
        return  memberService.loadAllProject();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable String id){
        memberService.excludeProject(id);
    }

    @PatchMapping("/{id}")
    public MemberDTO updateProject(@PathVariable String id, @Valid @RequestBody MemberDTO dto){
        return memberService.updateProject(id, dto);
    }

}
