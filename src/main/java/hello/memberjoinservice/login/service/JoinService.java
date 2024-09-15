package hello.memberjoinservice.login.service;

import hello.memberjoinservice.login.dto.UserDTO;
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

    public void joinProcess(UserDTO userDto) throws Exception {

        String username = userDto.getUsername();
        String password = userDto.getPassword();

        boolean isExist = userRepository.existsByUsername(username);

        if(isExist){
            throw new Exception();
//            return;
        }

        UserEntity data = new UserEntity();
        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");
        userRepository.save(data);


    }
}
