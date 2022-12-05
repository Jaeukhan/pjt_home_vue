package com.ssafy.finalbe.model;

public class CommentDto {
    private int cno;
    private int articleno;
    private String cwriter;
    private String ccontent;
    ////////////////////////////////////////////////////////////////////////
    public int getCno() {
        return cno;
    }
    public void setCno(int cno) {
        this.cno = cno;
    }
    public int getArticleno() {
        return articleno;
    }
    public void setArticleno(int articleno) {
        this.articleno = articleno;
    }
    public String getCwriter() {
        return cwriter;
    }
    public void setCwriter(String cwriter) {
        this.cwriter = cwriter;
    }
    public String getCcontent() {
        return ccontent;
    }
    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    @Override
    public String toString() {
        return "CommentDTO [cno=" + cno + ", articleno=" + articleno + ", cwriter=" + cwriter + ", ccontent=" + ccontent + "]";
    }
}
