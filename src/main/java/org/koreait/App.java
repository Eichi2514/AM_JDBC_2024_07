package org.koreait;

import org.koreait.Controller.ArticleController;
import org.koreait.Controller.MemberController;

import java.util.Scanner;

public class App {
    public static void run() {
        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("명령어) ");
            String cmd = sc.nextLine();

            String[] cmds = cmd.split(" ");
            String Action = cmds[0];

            if (cmd.equals("help") || cmd.equals("a")) {
                System.out.println("0)      exit              : 프로그램 종료");
                System.out.println("a1) article write          : 글 작성");
                System.out.println("a2) article list           : 글 목록");
                System.out.println("a3) article modify + 번호  : 글 수정");
                System.out.println("a4) article delete + 번호  : 글 삭제");
                System.out.println("a5) article diteil + 번호  : 글 상세보기");
                System.out.println("m1) member join          : 회원가입");

            } else if (cmd.equals("exit") || cmd.equals("a 0")) {
                System.out.println("== 프로그램 종료 ==");
                sc.close();
                break;
            }else if (Action.equals("article") || Action.equals("a")) {
                ArticleController.run(cmd);
            } else if (Action.equals("member") || Action.equals("m")) {
                MemberController.run(cmd);
            } else {
                System.out.println("잘못된 명령어입니다.");
            }System.out.println();
        }
    }
}

