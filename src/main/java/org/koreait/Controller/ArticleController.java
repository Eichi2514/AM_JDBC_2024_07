package org.koreait.Controller;

import org.koreait.Article;
import org.koreait.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.koreait.Controller.MemberController.loginChacks;

public class ArticleController {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs = null;
    static String loginChack = null;

    public static void run(String cmd) {

        if (loginChacks == null) {
            System.out.println("로그인이 필요한 서비스 입니다");
            return;
        }else {
            loginChack = loginChacks.get(0);
        }

        int id = 1;
        int numberChack = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        boolean chack = true;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
//            System.out.println("연결 성공!");


            sql = "SELECT id, regDate, updateDate, loginId, title, body ";
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
                article.setLoginId(rs.getString("loginId"));
                article.setTitle(rs.getString("title"));
                article.setBody(rs.getString("body"));
                articles.add(article);
            }

            if (cmd.equals("article write") || cmd.equals("a 1")) {
                id = Number.number();

                System.out.println("== 게시글 작성 ==");
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();
                sql = "INSERT INTO article ";
                sql += "SET regDate = NOW(), ";
                sql += "updateDate = NOW(), ";
                sql += "loginId = '" + loginChack + "', ";
                sql += "title = '" + title + "', ";
                sql += "`body` = '" + body + "';";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                chack = false;
                System.out.println(id + "번 글이 작성되었습니다.");

            } else if (cmd.equals("article list") || cmd.equals("a 2")) {
                if (articles.size() <= 0) {
                    System.out.println("작성된 게시글이 없습니다.");
                } else {
                    System.out.println("== 게시글 목록 ==");
                    System.out.println("번호 / 작성자 / 제목  / 내용");

                    for (int i = 0; i < articles.size(); i++) {
                        System.out.println(articles.get(i).getId() + "    / " +
                                articles.get(i).getLoginId() + "    / " +
                                articles.get(i).getTitle() + "    / " +
                                articles.get(i).getBody());
                    }
                }
                chack = false;
            } else if (cmd.contains("article modify") || cmd.contains("a 3")) {
                String[] coms = cmd.split(" ");
                numberChack = Integer.parseInt(coms[2]);

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.getId() == numberChack) {
                        if (!loginChack.equals(article.getLoginId())) {
                            System.out.println("다른 사람의 게시글은 수정할 수 없습니다");
                            return;
                        }
                        System.out.println("제목(기존) : " + articles.get(i).getTitle());
                        System.out.println("내용(기존) : " + articles.get(i).getBody());
                        System.out.print("제목 : ");
                        String title = sc.nextLine();
                        System.out.print("내용 : ");
                        String body = sc.nextLine();

                        sql = "UPDATE article ";
                        sql += "SET loginId = '" + loginChack + "', ";
                        sql += "title = '" + title + "', ";
                        sql += "`body` = '" + body + "', ";
                        sql += "updateDate = NOW() ";
                        sql += "WHERE id = " + numberChack + ";";

                        System.out.println(numberChack + "번 게시글이 수정되었습니다.");

                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        chack = false;
                    }
                }
            } else if (cmd.contains("article delete") || cmd.contains("a 4")) {
                String[] coms = cmd.split(" ");
                numberChack = Integer.parseInt(coms[2]);
                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.getId() == numberChack) {
                        if (!loginChack.equals(article.getLoginId())) {
                            System.out.println("다른 사람의 게시글은 삭제할 수 없습니다");
                            return;
                        }
                        sql = "DELETE FROM article ";
                        sql += "WHERE id = " + numberChack + ";";

                        System.out.println(numberChack + "번 게시글이 삭제되었습니다.");

                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        chack = false;
                    }
                }
            } else if (cmd.contains("article diteil") || cmd.contains("a 5")) {
                String[] coms = cmd.split(" ");
                numberChack = Integer.parseInt(coms[2]);

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.getId() == numberChack) {
                        System.out.println("번호 : " + article.getId());
                        System.out.println("작성날짜 : " + article.getRegDate().substring(0, 19));
                        System.out.println("수정날짜 : " + article.getUpdateDate().substring(0, 19));
                        System.out.println("작성자 : " + article.getLoginId());
                        System.out.println("제목 : " + article.getTitle());
                        System.out.println("내용 : " + article.getBody());
                        chack = false;
                    }
                }

            }
            if (chack) System.out.println(numberChack + "번 게시글이 존재하지 않습니다.");

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

class Number {
    static int number() throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
        Connection conn = DriverManager.getConnection(url, "root", "");


        int id = 0;
        String sql2 = "SELECT MAX(id)+1 ";
        sql2 += "FROM article;";

        Statement stmt2 = conn.createStatement();

        ResultSet rs2 = stmt2.executeQuery(sql2);

        while (rs2.next()) {
            id = rs2.getInt("MAX(id)+1");
        }

        return id;
    }
}
