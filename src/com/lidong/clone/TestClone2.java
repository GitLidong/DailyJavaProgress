package com.lidong.clone;

public class TestClone2 {


    public static void main(String[] args) throws CloneNotSupportedException {
        Address2 address2 = new Address2();
        address2.setAdd("a1111");

        Student2 student2 = new Student2();
        student2.setNumber(11111);
        student2.setAddr(address2);

        Student2 student21 = (Student2) student2.clone();

        System.out.println("学生1:" + student2.getNumber() + ",地址:" + student2.getAddr().getAdd());
        System.out.println("学生2:" + student21.getNumber() + ",地址:" + student21.getAddr().getAdd());

        address2.setAdd("a2222");
        System.out.println("学生1:" + student2.getNumber() + ",地址:" + student2.getAddr().getAdd());
        System.out.println("学生2:" + student21.getNumber() + ",地址:" + student21.getAddr().getAdd());


    }

}

class Address2 implements Cloneable {
    private String add;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Address2 address2;
        address2 = (Address2) super.clone();
        return address2;
    }
}

class Student2 implements Cloneable {
    private int number;

    private Address2 addr;

    public Address2 getAddr() {
        return addr;
    }

    public void setAddr(Address2 addr) {
        this.addr = addr;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student2 stu = null;
        try {
            stu = (Student2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        stu.addr = (Address2) addr.clone();
        return stu;
    }
}

