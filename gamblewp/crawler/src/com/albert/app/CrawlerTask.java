package com.albert.app;

import java.util.Date;
import java.util.TimerTask;

public class CrawlerTask extends TimerTask{
    public void run() {
        System.out.println("���Ȯɶ��G" + new Date());
    }
}
