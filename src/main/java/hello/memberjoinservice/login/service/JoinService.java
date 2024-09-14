package hello.memberjoinservice.login.service;

import hello.memberjoinservice.login.dto.JoinDTO;
import hello.memberjoinservice.login.entitiy.UserEntity;
import hello.memberjoinservice.login.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDto){

        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        boolean isExist = userRepository.existsByUsername(username);

        if(isExist){
            return;
        }

        UserEntity data = new UserEntity();
        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");
        userRepository.save(data);


    }
}
