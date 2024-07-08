package org.koreait;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Article> articles = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int listId = 0;

        while (true){
            System.out.print("명령어) ");
            String com = sc.nextLine();

            if (com.equals("article write")){
                System.out.println("== 게시글 작성 ==");
                int id = listId+1;
                System.out.print("제목 : ");
                String title = sc.nextLine();
                System.out.print("내용 : ");
                String body = sc.nextLine();

                Article article = new Article(id, title, body);
                articles.add(article);
                listId++;

                System.out.println(id+"번 글이 작성되었습니다.");
            }else if (com.equals("article list")){
                System.out.println("== 게시글 목록 ==");
                System.out.println("번호 / 제목 / 내용");
                for (int i = articles.size()-1; i >=0 ; i--){
                    Article article = articles.get(i);
                    System.out.println(article.getId()+" / "+article.getTitle()+" / "+article.getBody());
                }

            }
        }
    }
}