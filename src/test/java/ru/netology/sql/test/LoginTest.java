package ru.netology.sql.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.sql.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.sql.data.DataHelper.*;
import static ru.netology.sql.data.SQLHelper.*;

public class LoginTest {

    LoginPage loginPage;

    @BeforeEach
    public void setUp(){
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterEach
    public void tearDown(){
        cleanVerificationCodes();
    }

    @AfterAll
    public static void tearDownAll(){
        cleanDataBase();
    }

    @Test
    public void shouldSuccessfulLogin(){
        var authenticationInformation = getFirstAuthenticationInformation();
        var verificationPage = loginPage.createVerificationPage(authenticationInformation);
        var verificationCode = getVerificationCode(authenticationInformation.getLogin());
        var dashboardPage = verificationPage.createDashboardPage(verificationCode);
        dashboardPage.shouldDashboardPage();
    }

    @Test
    public void shouldGetErrorIfInvalidPassword(){
        var authenticationInformation = getAuthenticationInformationWithInvalidPassword();
        loginPage.login(authenticationInformation);
        loginPage.errorNotification("Ошибка! " + "Неверно указан логин или пароль");
    }

    @Test
    public void shouldShowErrorIfEmptyLogin(){
        var authenticationInformation = getAuthenticationInformationWithEmptyLogin();
        loginPage.login(authenticationInformation);
        loginPage.loginFieldError("Поле обязательно для заполнения");
    }

    @Test
    public void shouldShowErrorIfEmptyPassword(){
        var authenticationInformation = getAuthenticationInformationWithEmptyPassword();
        loginPage.login(authenticationInformation);
        loginPage.passwordFieldError("Поле обязательно для заполнения");
    }

    @Test
    public void shouldGetErrorIfUnregisteredUser(){
        var authenticationInformation = getRandomAuthenticationInformation();
        loginPage.login(authenticationInformation);
        loginPage.errorNotification("Ошибка! " + "Неверно указан логин или пароль");
    }

    @Test
    public void shouldGetErrorIfInvalidVerificationCode(){
        var authenticationInformation = getSecondAuthenticationInformation();
        var verificationPage = loginPage.createVerificationPage(authenticationInformation);
        var verificationCode = getRandomVerificationCode();
        var dashboardPage = verificationPage.createDashboardPage(verificationCode);
        verificationPage.errorNotification("Ошибка! " + "Неверно указан код! Попробуйте ещё раз.");
    }

    @Test
    public void shouldShowErrorIfEmptyVerificationCode(){
        var authenticationInformation = getFirstAuthenticationInformation();
        var verificationPage = loginPage.createVerificationPage(authenticationInformation);
        var verificationCode = getEmptyVerificationCode();
        var dashboardPage = verificationPage.createDashboardPage(verificationCode);
        verificationPage.codeFieldError("Поле обязательно для заполнения");
    }
}
