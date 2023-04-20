package com.example.petstore.Models.DirectorModels;

import com.example.petstore.Entities.Sex;

public class DirectorCreateModel {
    private String Name;
    private Sex Gender;
    private double Salary;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Sex getGender() {
        return Gender;
    }

    public void setGender(Sex gender) {
        Gender = gender;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}
