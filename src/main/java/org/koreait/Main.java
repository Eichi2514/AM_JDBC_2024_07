package org.koreait;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("명령어) ");
            String com = sc.nextLine();
            String[] coms = com.split(" ");
            String Action = coms[0];

            if (com.equals("help") || com.equals("?")) {
                System.out.println("0)      exit              : 프로그램 종료");
                System.out.println("1) article write          : 글 작성");
                System.out.println("2) article list           : 글 목록");
                System.out.println("3) article modify + 번호  : 글 수정");
                System.out.println("4) article delete + 번호  : 글 삭제");
                System.out.println("5) article diteil + 번호  : 글 상세보기");

            } else if (com.equals("exit") || com.equals("?0")) {
                System.out.println("== 프로그램 종료 ==");
                sc.close();
                break;
            }else if (Action.equals("article") || Action.equals("?")) {
                JDBC.main(com);
            } else {
                System.out.println("잘못된 명령어입니다.");
            }System.out.println();
        }
    }
}