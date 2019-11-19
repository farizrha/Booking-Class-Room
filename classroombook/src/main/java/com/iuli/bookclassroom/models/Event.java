package com.iuli.bookclassroom.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    1. Class
    2. Makeupclass
    3. Seminar
    4. Others
     */
    private String type;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "event")
    private List<Book> books;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
