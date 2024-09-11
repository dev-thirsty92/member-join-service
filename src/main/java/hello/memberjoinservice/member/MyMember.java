package hello.memberjoinservice.member;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

class MyMember {

    @NotNull
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private final String username;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private final String password;

    public MyMember(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}
