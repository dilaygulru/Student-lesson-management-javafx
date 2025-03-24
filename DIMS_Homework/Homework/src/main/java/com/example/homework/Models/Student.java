package com.example.homework.Models;

public class Student {
   private int id;
   private String Name;
   private String Surname;

   public Student() {
   }

   public Student(int id,String Name,String Surname){
       this.id=id;
       this.Name =Name;
       this.Surname =Surname;
   }

    public int getId() {
       return id;
    }

    public void setId(int id) {
       this.id = id;
   }

    public String getName() {
       return Name;
    }

    public void setName(String name) {

       this.Name = name;
    }

    public String getSurname() {
       return Surname;
    }

    public void setSurname(String surname) {
        this.Surname = surname;
    }
}
