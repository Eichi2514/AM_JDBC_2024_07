package org.koreait;

public class Member {
    int id;
    String regDate;
    String userId;
    String userPw;
    String userName;

    public Member(){
    }

    public Member(int id, String regDate, String userId, String userPw, String userName) {
        this.id = id;
        this.regDate = regDate;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
