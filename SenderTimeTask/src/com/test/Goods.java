package com.test;

public class Goods { 
    private void internalTracking(boolean b) {  
        if (b) {  
            class TrackingSlip {  
                private String id; 
                Test2 t = new Test2();
                TrackingSlip(String s) {  
                    id = s;  
                }  
                String getSlip() {  
                    return id;  
                } 
            }  
            TrackingSlip ts = new TrackingSlip("slip");  
            ts.getSlip();  
        }  
    }  
    public void track() {  
        internalTracking(true);  
    }  
    public static void main(String[] args) {  
        Goods g = new Goods();  
        g.track();  
    }  
} 