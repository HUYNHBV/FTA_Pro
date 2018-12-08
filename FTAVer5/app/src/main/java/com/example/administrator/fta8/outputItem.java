package com.example.administrator.fta8;

public class outputItem {
    String cause;
    String[] descrip;
    String[] descripPic;
    String descripPicDir;
    String[] action;
    String[] detail;

    public outputItem(String cause, String[] descripPic, String descripPicDir, String[] descrip, String[] action, String[] detail) {
        this.cause = cause;
        this.descrip = descrip;
        this.descripPic = descripPic;
        this.descripPicDir = descripPicDir;
        this.action = action;
        this.detail = detail;
    }

    public String getDescripPicDir() {
        return descripPicDir;
    }

    public void setDescripPicDir(String descripPicDir) {
        this.descripPicDir = descripPicDir;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String couse) {
        this.cause = couse;
    }

    public String[] getDescrip() {
        return descrip;
    }

    public void setDescrip(String[] descrip) {
        this.descrip = descrip;
    }

    public String[] getDescripPic() {
        return descripPic;
    }

    public void setDescripPic(String[] descripPic) {
        this.descripPic = descripPic;
    }

    public String[] getAction() {
        return action;
    }

    public void setAction(String[] action) {
        this.action = action;
    }

    public String[] getDetail() {
        return detail;
    }

    public void setDetail(String[] detail) {
        this.detail = detail;
    }
}
