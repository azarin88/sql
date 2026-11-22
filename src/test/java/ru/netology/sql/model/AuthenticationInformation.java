package ru.netology.sql.model;

import lombok.Value;

@Value
public class AuthenticationInformation {
    String login;
    String password;
}
