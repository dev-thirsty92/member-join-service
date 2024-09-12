package hello.memberjoinservice.member.service;

import hello.memberjoinservice.member.domain.MyMemberDto;
import hello.memberjoinservice.member.repository.JoinRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
class MemberJoinService {
    private final JoinRepository joinRepository;

    MemberJoinService(final JoinRepository joinRepository) {
        this.joinRepository = joinRepository;
    }

    @PostMapping("/join")
    public ResponseEntity<String> signUp(@Valid @RequestBody MyMemberDto memberDto) {

//        MyMemberDto member = new MyMemberDto(request.username(), request.password());
        joinRepository.save(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created member");
    }
}
