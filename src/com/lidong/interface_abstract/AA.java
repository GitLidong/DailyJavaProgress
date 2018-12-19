package com.lidong.interface_abstract;

public abstract class AA {


    String Ainfo = "AA";
    Othres othres = new Othres();

    abstract public void AAInfo();

    public void AAInfo2() {
        System.out.println(Ainfo + " , " + othres.othres);
    }

}
