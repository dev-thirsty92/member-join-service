package hello.memberjoinservice.member;

class MyMember {
    private final String username;
    private final String password;

    public MyMember(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}
