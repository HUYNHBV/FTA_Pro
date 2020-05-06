package com.example.administrator.fta8;

public class stackItem {
    String [][] arrLvFilter1;  // Stt
    String [][] arrLvFilter2; // Date
    String [][] arrLvFilter3; // filter1: list view "Model"
    String [][] arrLvFilter4; // filter2: list view "Lens"
    String [][] arrLvFilter5; // filter3: list view "Loi"
    String [][] arrImgFilter6; // filter4: list src image "Anh mo ta Loi"
    String [][] arrLvFilter7; // filter5: list view "Vi Tri"
    String [][] arrLvFilter8; // filter6: List view  "Bien Dang"
    String [][] arrLvFilter9; // filter7: List view "New Filter"

    public stackItem(String[][] arrLvFilter1, String[][] arrLvFilter2, String[][] arrLvFilter3, String[][] arrLvFilter4, String[][] arrLvFilter5, String[][] arrImgFilter6, String[][] arrLvFilter7, String[][] arrLvFilter8, String[][] arrLvFilter9) {
        this.arrLvFilter1 = arrLvFilter1;
        this.arrLvFilter2 = arrLvFilter2;
        this.arrLvFilter3 = arrLvFilter3;
        this.arrLvFilter4 = arrLvFilter4;
        this.arrLvFilter5 = arrLvFilter5;
        this.arrImgFilter6 = arrImgFilter6;
        this.arrLvFilter7 = arrLvFilter7;
        this.arrLvFilter8 = arrLvFilter8;
        this.arrLvFilter9 = arrLvFilter9;
    }

    public String[][] getArrLvFilter1() {
        return arrLvFilter1;
    }

    public void setArrLvFilter1(String[][] arrLvFilter1) {
        this.arrLvFilter1 = arrLvFilter1;
    }

    public String[][] getArrLvFilter2() {
        return arrLvFilter2;
    }

    public void setArrLvFilter2(String[][] arrLvFilter2) {
        this.arrLvFilter2 = arrLvFilter2;
    }

    public String[][] getArrLvFilter3() {
        return arrLvFilter3;
    }

    public void setArrLvFilter3(String[][] arrLvFilter3) {
        this.arrLvFilter3 = arrLvFilter3;
    }

    public String[][] getArrLvFilter4() {
        return arrLvFilter4;
    }

    public void setArrLvFilter4(String[][] arrLvFilter4) {
        this.arrLvFilter4 = arrLvFilter4;
    }

    public String[][] getArrLvFilter5() {
        return arrLvFilter5;
    }

    public void setArrLvFilter5(String[][] arrLvFilter5) {
        this.arrLvFilter5 = arrLvFilter5;
    }

    public String[][] getArrImgFilter6() {
        return arrImgFilter6;
    }

    public void setArrImgFilter6(String[][] arrImgFilter6) {
        this.arrImgFilter6 = arrImgFilter6;
    }

    public String[][] getArrLvFilter7() {
        return arrLvFilter7;
    }

    public void setArrLvFilter7(String[][] arrLvFilter7) {
        this.arrLvFilter7 = arrLvFilter7;
    }

    public String[][] getArrLvFilter8() {
        return arrLvFilter8;
    }

    public void setArrLvFilter8(String[][] arrLvFilter8) {
        this.arrLvFilter8 = arrLvFilter8;
    }

    public String[][] getArrLvFilter9() {
        return arrLvFilter9;
    }

    public void setArrLvFilter9(String[][] arrLvFilter9) {
        this.arrLvFilter9 = arrLvFilter9;
    }
}
