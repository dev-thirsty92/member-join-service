package hello.memberjoinservice.member;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
class JoinRepositoryImpl implements JoinRepository {
    Map<String, MyMemberDto> persistence = new HashMap<>();

    @Override
    public void save(MyMemberDto member) {
        if (!persistence.containsKey(member.getUsername())) {
            persistence.put(member.getUsername(), member);
        } else {
            throw new IllegalArgumentException("already exists with username " + member.getUsername());
        }
    }
}
