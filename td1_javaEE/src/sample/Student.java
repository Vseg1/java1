package sample;

import java.time.LocalDate;
import java.util.Date;

public class Student {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Double getMark() {
        return Mark;
    }

    public void setMark(double mark) {
        Mark = mark;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    @Override
    public String toString(){
        return("[" + id + "] " + name + "-" + gender + "(" + birth_date + ") image : " + URL);
    }

    public Student(int id, String name, String gender, LocalDate birth_date, String URL, double mark, String comment) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.URL = URL;
        this.Mark = mark;
        this.Comment = comment;
    }

    public Student(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public Student(){

    }

    private int id;
    private String name;
    private String gender;
    private LocalDate birth_date;
    private String URL;
    private double Mark;
    private String Comment;


}
