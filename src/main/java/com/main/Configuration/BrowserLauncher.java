//package com.main.Configuration;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BrowserLauncher implements ApplicationListener<ApplicationReadyEvent> {
//    @Override
//    public void onApplicationEvent(ApplicationReadyEvent event) {
//        try {
//            String url = "http://localhost:8080/login";
//
//            // Replace this path if your Chrome is installed elsewhere
//            String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
//
//            Runtime.getRuntime().exec(new String[] { chromePath, url });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
