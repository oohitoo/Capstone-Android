package com.example.a13110091.github;

/**
 * Created by 13110091 on 2018-05-14.
 */

public class Senser {

    String senser1;
    String senser2;
    String senser3;
    String senser4;
    String senser5;
    String senser6;
    String posture;
    String data_hora;

    public String getSenser1() {
        return senser1;
    }

    public void setSenser1(String senser1) {
        this.senser1 = senser1;
    }

    public String getSenser2() {
        return senser2;
    }

    public void setSenser2(String senser2) {
        this.senser2 = senser2;
    }

    public String getSenser3() {
        return senser3;
    }

    public void setSenser3(String senser3) {
        this.senser3 = senser3;
    }

    public String getSenser4() {
        return senser4;
    }

    public void setSenser4(String senser4) {
        this.senser4 = senser4;
    }

    public String getSenser5() {
        return senser5;
    }

    public void setSenser5(String senser5) {
        this.senser5 = senser5;
    }

    public String getSenser6() {
        return senser6;
    }

    public void setSenser6(String senser6) {
        this.senser6 = senser6;
    }

    public String getPosture() {
        return posture;
    }

    public void setPosture(String posture) {
        this.posture = posture;
    }

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    public Senser(/*String senser1, String senser2, String senser3, String senser4, String senser5, String senser6,*/ String posture, String data_hora) {
        this.senser1 = senser1;
        this.senser2 = senser2;
        this.senser3 = senser3;
        this.senser4 = senser4;
        this.senser5 = senser5;
        this.senser6 = senser6;
        this.posture = posture;
        this.data_hora = data_hora;
    }
}
