package ru.netology.sql.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.sql.model.VerificationCode;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLHelper {

    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper(){}

    private static Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static VerificationCode getVerificationCode(String login){
        var sql = "SELECT ac.code FROM auth_codes ac " + "JOIN users u ON ac.user_id = u.id " + "WHERE u.login = ? " + "ORDER BY ac.created DESC LIMIT 1;";
        try (var connection = getConnection()){
            var code = runner.query(connection, sql, new ScalarHandler<String>(), login);
            return new VerificationCode(code);
        }
    }

    @SneakyThrows
    public static void cleanVerificationCodes() {
        try (var connection = getConnection()) {
            runner.update(connection, "DELETE FROM auth_codes;");
        }
    }

    @SneakyThrows
    public static void cleanDataBase() {
        try (var connection = getConnection()) {
            runner.update(connection, "DELETE FROM card_transactions;");
            runner.update(connection, "DELETE FROM auth_codes;");
            runner.update(connection, "DELETE FROM cards;");
            runner.update(connection, "DELETE FROM users;");
        }
    }
}
