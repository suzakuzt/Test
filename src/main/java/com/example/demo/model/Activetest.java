package com.example.demo.model;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Document(collection="activities")
public class Activetest {
//    @Id
    private String id;
    private String title;
    private String start_date;
    private String endd_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEndd_date() {
        return endd_date;
    }

    public void setEndd_date(String endd_date) {
        this.endd_date = endd_date;
    }

    @Override
    public String toString() {
        return "Activetest{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", start_date='" + start_date + '\'' +
                ", endd_date='" + endd_date + '\'' +
                '}';
    }
}


