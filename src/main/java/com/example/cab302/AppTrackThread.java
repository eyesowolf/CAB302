package com.example.cab302;

import java.util.Objects;

public class AppTrackThread implements Runnable{

    private volatile String lastapplication;
    private boolean appTrackToggle = true;

    public String getLastapplication(){
        return lastapplication;
    }

    public boolean getAppTrackToggle(){ return appTrackToggle;}
    public void setAppTrackToggle(boolean value){this.appTrackToggle = value;}
    public void run() {
        while(appTrackToggle) {
            String currentApplication = ApplicationTracker.getActiveWindow();
            if (!Objects.equals(currentApplication, "java")) {
                lastapplication = currentApplication;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
