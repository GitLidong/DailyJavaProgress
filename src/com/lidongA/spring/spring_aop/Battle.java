package com.lidongA.spring.spring_aop;

public class Battle {

    public void tuan() {
        Luna luna = new Luna();
        luna.say("上去开团！");
        luna.operation();

        int i = 1 / 0;
    }

    public void tuan2() {
        Luna luna = new Luna();
        luna.say("上去开团！");
        luna.operation();
    }

}
