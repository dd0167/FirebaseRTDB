package com.example.firebasertdb;

public class Student {

    private String firstName;
    private String lastName;
    private String studentID;
    private int stuGrade;
    private int stuClass;
    private String canGetVaccine;
    private Vaccine firstVaccine;
    private Vaccine secondVaccine;

    public Student (String firstName, String lastName, String studentID, int stuGrade, int stuClass, String canGetVaccine, Vaccine firstVaccine, Vaccine secondVaccine) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.studentID=studentID;
        this.stuGrade=stuGrade;
        this.stuClass=stuClass;
        this.canGetVaccine=canGetVaccine;
        this.firstVaccine=firstVaccine;
        this.secondVaccine=secondVaccine;
    }

    public Student()
    {}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getStudentID()
    {
        return studentID;
    }

    public int getStuGrade()
    {
        return stuGrade;
    }

    public int getStuClass()
    {
        return stuClass;
    }

    public String getCanGetVaccine()
    {
        return canGetVaccine;
    }

    public Vaccine getFirstVaccine()
    {
        return firstVaccine;
    }

    public Vaccine getSecondVaccine()
    {
        return secondVaccine;
    }
}

