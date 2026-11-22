package ru.netology.sql.data;

import com.github.javafaker.Faker;
import ru.netology.sql.model.AuthenticationInformation;
import ru.netology.sql.model.VerificationCode;

import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper(){}

    public static String getRandomLogin(){
        return faker.name().username();
    }

    public static String getRandomPassword(){
        return faker.internet().password();
    }

    public static String getRandomCode(){
        return faker.numerify("######");
    }

    public static AuthenticationInformation getRandomAuthenticationInformation(){
        return new AuthenticationInformation(getRandomLogin(), getRandomPassword());
    }

    public static AuthenticationInformation getFirstAuthenticationInformation(){
        return new AuthenticationInformation("vasya", "qwerty123");
    }

    public static AuthenticationInformation getSecondAuthenticationInformation(){
        return new AuthenticationInformation("petya", "123qwerty");
    }

    public static AuthenticationInformation getAuthenticationInformationWithInvalidPassword(){
        return new AuthenticationInformation("petya", getRandomPassword());
    }

    public static AuthenticationInformation getAuthenticationInformationWithEmptyLogin() {
        return new AuthenticationInformation("", getRandomPassword());
    }

    public static AuthenticationInformation getAuthenticationInformationWithEmptyPassword() {
        return new AuthenticationInformation(getRandomLogin(), "");
    }

    public static VerificationCode getRandomVerificationCode(){
        return new VerificationCode(getRandomCode());
    }

    public static VerificationCode getEmptyVerificationCode(){
        return new VerificationCode("");
    }
}
