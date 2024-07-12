package org.koreait.Controller;

import org.koreait.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberController {
    static Scanner sc = new Scanner(System.in);
    static ResultSet rs = null;

    public static void run(String cmd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_JDBC_2024_07?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
//            System.out.println("연결 성공!");

            sql = "SELECT MEMBERNO, REGDATE, USERID, USERPW, USERNAME ";
            sql += "FROM MEMBER ";
            sql += "ORDER BY MEMBERNO DESC;";

            Statement stmtList = conn.createStatement();

            rs = stmtList.executeQuery(sql);

            ArrayList<Member> members = new ArrayList<>();


            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getInt("MEMBERNO"));
                member.setRegDate(rs.getString("REGDATE"));
                member.setUserId(rs.getString("USERID"));
                member.setUserPw(rs.getString("USERPW"));
                member.setUserName(rs.getString("USERNAME"));
                members.add(member);
            }

            if (cmd.equals("member join") || cmd.equals("m 1")) {
                String id;
                String pw;
                String name;
                boolean IdChack = true;
                System.out.println("== 회원가입 ==");

                while (true) {
                    System.out.print("ID : ");
                    id = sc.nextLine();
                    if (id.length() == 0) {
                        System.out.println("ID를 입력해주세요");
                        continue;
                    } else if (id.contains(" ")) {
                        System.out.println("공백은 사용할 수 없습니다.");
                        continue;
                    } else {
                        for (int i = 0; i < members.size(); i++) {
                            Member member = members.get(i);
                            if (id.equals(members.get(i).getUserId())) {
                                System.out.println(id + "는 이미 사용중인 ID 입니다");
                                IdChack = false;
                                break;
                            } else IdChack = true;
                        }
                    } if(IdChack) break;
                }

                while (true) {
                    System.out.print("PW : ");
                    pw = sc.nextLine();
                    if (pw.length() == 0) {
                        System.out.println("PW를 입력해주세요");
                        continue;
                    } else if (pw.contains(" ")) {
                        System.out.println("공백은 사용할 수 없습니다.");
                        continue;
                    }
                    while (true) {
                        System.out.print("PW 재확인 : ");
                        String pw2 = sc.nextLine();
                        if (pw.equals(pw2)) {
                            break;
                        } else {
                            System.out.println("PW가 틀렸습니다 PW를 다시 확인해주세요");
                        }
                    }
                    break;
                }

                while (true) {
                    System.out.print("NAME : ");
                    name = sc.nextLine();
                    if (name.length() == 0) {
                        System.out.println("NAME를 입력해주세요");
                        continue;
                    } else if (name.contains(" ")) {
                        System.out.println("공백은 사용할 수 없습니다.");
                        continue;
                    }
                    break;
                }

                sql = null;

                sql = "INSERT INTO `MEMBER` ";
                sql += "SET regDate = NOW(), ";
                sql += "USERID = '" + id + "',";
                sql += "USERPW = '" + pw + "',";
                sql += "USERNAME = '" + name + "';";

                Statement stmt = conn.createStatement();

                rs = null;
                rs = stmt.executeQuery(sql);

                System.out.print("회원가입이 완료되었습니다");
            } else if (cmd.equals("member login") || cmd.equals("m 2")) {
                String id;
                System.out.println("ID : ");
                id = sc.nextLine();
                for (int i = 0; i < members.size(); i++) {
                    Member member = members.get(i);
                    if (id.equals(members.get(i).getUserId())) {

                    }
                }
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
