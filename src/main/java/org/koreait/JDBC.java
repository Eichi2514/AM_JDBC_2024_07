package org.koreait;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBC {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs = null;

    public static void main(String com) {

        int id = 1;
        int numberChack = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
//            System.out.println("연결 성공!");

            sql = "SELECT * ";
            sql += "FROM article ";
            sql += "ORDER BY id DESC;";

            Statement stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            ArrayList<Article> articles = new ArrayList<>();

            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setRegDate(rs.getString("regDate"));
                article.setUpdateDate(rs.getString("updateDate"));
                article.setTitle(rs.getString("title"));
                article.setBody(rs.getString("body"));
                articles.add(article);
            }
            if (articles.size() > 0) {
                id = articles.get(0).getId() + 1;
            }

            if (com.equals("article write") || com.equals("? 1")) {
                System.out.println("== 게시글 작성 ==");
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();
                System.out.println(id + "번 글이 작성되었습니다.");
                sql = "INSERT INTO article ";
                sql += "SET regDate = NOW(), ";
                sql += "updateDate = NOW(), ";
                sql += "title = '" + title + "', ";
                sql += "`body` = '" + body + "';";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();

            } else if (com.equals("article list") || com.equals("? 2")) {
                if (articles.size() <= 0) {
                    System.out.println("작성된 게시글이 없습니다.");
                } else {
                    System.out.println("== 게시글 목록 ==");
                    System.out.println("번호 / 제목  / 내용");

                    for (int i = 0; i < articles.size(); i++) {
                        System.out.println(articles.get(i).getId() + "    / " +
                                articles.get(i).getTitle() + " / " +
                                articles.get(i).getBody());
                    }
                }
            } else if (com.contains("article modify") || com.contains("? 3")) {
                String[] coms = com.split(" ");
                numberChack = Integer.parseInt(coms[2]);
                boolean chack = true;

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.getId() == numberChack) {
                        System.out.println("제목(기존) : " + articles.get(i).getTitle());
                        System.out.println("내용(기존) : " + articles.get(i).getBody());
                        System.out.print("제목 : ");
                        String title = sc.nextLine();
                        System.out.print("내용 : ");
                        String body = sc.nextLine();

                        sql = "UPDATE article ";
                        sql += "SET title = '" + title + "', ";
                        sql += "`body` = '" + body + "', ";
                        sql += "updateDate = NOW() ";
                        sql += "WHERE id = " + numberChack + ";";

                        System.out.println(numberChack + "번 게시글이 수정되었습니다.");

                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        chack = false;
                    }
                }
                if (chack) System.out.println(numberChack + "번 게시글이 존재하지 않아 수정할 수 없습니다.");
            } else if (com.contains("article delete") || com.contains("? 4")) {
                String[] coms = com.split(" ");
                numberChack = Integer.parseInt(coms[2]);
                boolean chack = true;
                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.getId() == numberChack) {

                        sql = "DELETE FROM article ";
                        sql += "WHERE id = " + numberChack + ";";

                        System.out.println(numberChack + "번 게시글이 삭제되었습니다.");

                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        chack = false;
                    }
                }
                if (chack) System.out.println(numberChack + "번 게시글이 존재하지 않아 삭제할 수 없습니다.");
            } else if (com.contains("article diteil") || com.contains("? 5")) {
                String[] coms = com.split(" ");
                numberChack = Integer.parseInt(coms[2]);
                boolean chack = true;

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.getId() == numberChack) {
                        System.out.println("번호 : " + article.getId());
                        System.out.println("작성날짜 : " + article.getRegDate().substring(0, 19));
                        System.out.println("수정날짜 : " + article.getUpdateDate().substring(0, 19));
                        System.out.println("제목 : " + article.getTitle());
                        System.out.println("내용 : " + article.getBody());
                        chack = false;
                    }
                }if (chack) System.out.println(numberChack + "번 게시글이 존재하지 않아 불러올 수 없습니다.");

            }

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
                if (pstmt != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}

