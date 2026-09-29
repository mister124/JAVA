package pnu.cse.pbp;

import java.sql.*;

public class UserJdbcDemo {
    public static void main(String[] args) throws Exception {

        Connection conn =
                DriverManager.getConnection(
                        "jdbc:h2:mem:testdb",
                        "sa",
                        ""
                );

        Statement stmt = conn.createStatement();

        stmt.execute(
                "CREATE TABLE users (" +
                "id BIGINT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "email VARCHAR(50))"
        );

        System.out.println("테이블 'users'가 생성되었습니다.");

        UserPojo userToSave =
                new UserPojo(
                        202111120L,
                        "박진성",
                        "mister124@pnu.ac.kr"
                );

        String insertSql = String.format(
                "INSERT INTO users (id, name, email) " +
                "VALUES (%d, '%s', '%s')",
                userToSave.getId(),
                userToSave.getName(),
                userToSave.getEmail()
        );

        stmt.execute(insertSql);

        System.out.println(
                "객체를 DB에 저장했습니다: " + userToSave
        );

        ResultSet rs =
                stmt.executeQuery(
                        "SELECT * FROM users WHERE id = 202111120"
                );

        UserPojo userFromDb = null;

        if (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String email = rs.getString("email");

            userFromDb =
                    new UserPojo(id, name, email);
        }

        System.out.println(
                "DB에서 조회 후 변환한 객체: " + userFromDb
        );

        rs.close();
        stmt.close();
        conn.close();
    }
}