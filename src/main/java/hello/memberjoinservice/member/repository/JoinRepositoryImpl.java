//package hello.memberjoinservice.member.repository;
//
//import hello.memberjoinservice.member.domain.MyMemberDto;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@Repository
//public class JoinRepositoryImpl {
//
//    private JpaRepository joinRepository;
//
//    public JoinRepositoryImpl(JpaRepository joinRepository) {
//        this.joinRepository = joinRepository;
//    }
//
//    public void save(MyMemberDto member) {
//        MyMemberDto o = (MyMemberDto) joinRepository.findById(member.getId()).get();
//
//
//        joinRepository.save(member);
//        if (!persistence.containsKey(member.getUsername())) {
//            persistence.put(member.getUsername(), member);
//        } else {
//            throw new IllegalArgumentException("already exists with username " + member.getUsername());
//        }
//    }
//}
