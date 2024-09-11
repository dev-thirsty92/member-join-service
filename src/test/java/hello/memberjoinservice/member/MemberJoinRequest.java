package hello.memberjoinservice.member;

record MemberJoinRequest(String username, String password) {

    MemberJoinRequest {
        int upperCnt = 0;
        int lowerCnt = 0;
        int digitCnt = 0;

        // username 검증
        int userNameSize = username.length();
        if (userNameSize < 4 || userNameSize > 10) {
            throw new IllegalArgumentException("username length must be between 4 and 10");
        }

        for (char c : username.toCharArray()) {
            if (!Character.isLetterOrDigit(c) || Character.isUpperCase(c)) {
                throw new IllegalArgumentException("username contains invalid characters");
            }

            if (Character.isLowerCase(c)) {
                lowerCnt++;
                continue;
            }
            if (Character.isDigit(c)) {
                digitCnt++;
            }
        }

        int usernameResult = lowerCnt * digitCnt;
        if (usernameResult == 0) {
            throw new IllegalArgumentException("username must consist of lowercase letters and numbers.");
        }


        //password 검증

        lowerCnt = 0;
        digitCnt = 0;
        int passwordSize = password.length();
        if (passwordSize < 8 || passwordSize > 15) {
            throw new IllegalArgumentException("password length must be between 8 and 15");
        }


        for (char c : password.toCharArray()) {

            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException("username contains invalid characters");
            }

            if (Character.isUpperCase(c)) {
                upperCnt++;
                continue;
            }
            if (Character.isLowerCase(c)) {
                lowerCnt++;
                continue;
            }
            if (Character.isDigit(c)) {
                digitCnt++;
            }
        }

        int result = upperCnt * lowerCnt * digitCnt;
        if (result == 0) {
            throw new IllegalArgumentException("password must consist of upper case, lower case, and number");
        }

    }
}
