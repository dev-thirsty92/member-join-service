package hello.memberjoinservice.login.controller;

import hello.memberjoinservice.login.dto.JoinDTO;
import hello.memberjoinservice.login.entitiy.UserEntity;
import hello.memberjoinservice.login.service.JoinService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@AllArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String join(JoinDTO joinDTO){
        System.out.println(joinDTO.getUsername());
        joinService.joinProcess(joinDTO);


        return "ok";
    }
}
