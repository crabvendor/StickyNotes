package com.codecool.model;

import javax.persistence.*;

@Entity(name = "sticky_notes")
public class StickyNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(length = 8000)
    private String content;

    private double positionX;

    private double positionY;

    public StickyNote() {
        this.title = "Edit me!";
        this.content = "Edit me!";
        this.positionX = 80;
        this.positionY = 80;
    }
}
