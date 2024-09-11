package hello.memberjoinservice.member;

import java.util.HashMap;
import java.util.Map;

class JoinRepositoryImpl implements JoinRepository {
    Map<String, MyMember> persistence = new HashMap<>();

    @Override
    public void save(MyMember member) {
        if (!persistence.containsKey(member.getUsername())) {
            persistence.put(member.getUsername(), member);
        } else {
            throw new IllegalArgumentException("already exists with username " + member.getUsername());
        }
    }
}
