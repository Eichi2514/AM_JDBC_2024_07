package org.koreait;

public class Article {
    private int id;
    private static String title;
    private static String body;

    @Override
    public String toString() {
        return id + " / " + title + " / " + body;
    }

    Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        title = title;
    }

    public static String getBody() {
        return body;
    }

    public static void setBody(String body) {
        body = body;
    }

}
