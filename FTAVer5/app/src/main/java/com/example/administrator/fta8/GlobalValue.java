package com.example.administrator.fta8;

import android.app.Application;

public class GlobalValue extends Application {

    public String Process;

    protected GlobalValue(){};

    public String getProcess() {
        return Process;
    }

    public void setProcess(String process) {
        Process = process;
    }
}
