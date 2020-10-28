package server;

import java.sql.*;

public class BDHandler {
    private static Connection connection;
    private static PreparedStatement getNickname;
    private static PreparedStatement registration;


    public static boolean connect () {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            prepareAallStmt();
            return true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    private static void prepareAallStmt() throws SQLException {
        getNickname = connection.prepareStatement("SELECT nickname FROM users WHERE login = ? AND password = ?;");
        registration= connection.prepareStatement("INSERT INTO users(login, password, nickname) VALUES (? ,? ,? );");
    }

    public static String getNicknameByLoginAndPassword(String login, String password) {
        String nickName = null;
        try {
            getNickname.setString(1, login);
            getNickname.setString(2, password);
            ResultSet rs = getNickname.executeQuery();
            if (rs.next()) {
                nickName = rs.getString(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nickName;
    }

    public static boolean registration(String login, String password, String nickname) {
        try {
            registration.setString(1, login);
            registration.setString(2, password);
            registration.setString(3, nickname);
            registration.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
