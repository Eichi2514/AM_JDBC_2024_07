package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);
        int listId = 0;

        while (true) {
            System.out.print("명령어) ");
            String com = sc.nextLine();

            if (com.equals("article write")) {
                System.out.println("== 게시글 작성 ==");
                int id = listId + 1;
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body);
                articles.add(article);

                JDBCConnTest.main(args);

                listId++;

                System.out.println(id + "번 글이 작성되었습니다.");
            } else if (com.equals("article list")) {
                if (articles.size() <= 0) {
                    System.out.println("작성된 게시글이 없습니다.");
                    continue;
                }
                System.out.println("== 게시글 목록 ==");
                System.out.println("번호 / 제목 / 내용");
                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);
                    System.out.println(article.toString());
                }

            } else if (com.equals("exit")) {
                System.out.println("== 프로그램 종료 ==");
                sc.close();
                break;
            } else {
                System.out.println("잘못된 명령어입니다.");
            }
        }
    }
}