package org.koreait;

import java.sql.*;
import java.util.ArrayList;

public class JDBCSelectTest {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");


            String sql = "SELECT * ";
            sql += "FROM article ";
            sql += "ORDER BY id DESC;";


            Statement stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            ArrayList<Article> articles = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String regDate = rs.getString("regDate");
                String updateDate = rs.getString("updateDate");
                String title = rs.getString("title");
                String body = rs.getString("body");
                Article article = new Article(id, regDate, updateDate, title, body);
                articles.add(article);
            }

            for (int i = 0; i < articles.size(); i++) {
                System.out.println(articles.get(i).getId() + " / " +
                        articles.get(i).getRegDate() + " / " +
                        articles.get(i).getUpdateDate() + " / " +
                        articles.get(i).getTitle() + " / " +
                        articles.get(i).getBody());
            }



            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (rs != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}

