package ru.netology.data;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static String getFirstNumberCard() {
        return "5559000000000001";
    }

    public static String getSecondNumberCard() {
        return "5559000000000002";
    }

    public static int getZero() {
        return 0;
    }

}


