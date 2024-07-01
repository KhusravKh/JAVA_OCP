package org.example;

public class Student {

    private String name;

    private int precent;

    public Student(String name, int precent) {
        this.name = name;
        this.precent = precent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrecent() {
        return precent;
    }

    public void setPrecent(int precent) {
        this.precent = precent;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", precent=" + precent +
                '}';
    }
}
