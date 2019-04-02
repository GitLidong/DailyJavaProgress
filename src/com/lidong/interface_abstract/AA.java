package com.lidong.interface_abstract;

public abstract class AA {


    String Ainfo = "AA";
    Othres AAothres = new Othres();

    abstract public void AAInfo();

    public void AAInfo2() {
        System.out.println(Ainfo + " , AAInfo2 , " + AAothres.othres);
    }

    public void AAInfo3() {
        System.out.println(Ainfo + " , AAInfo3 , " + AAothres.othres);
    }

}
