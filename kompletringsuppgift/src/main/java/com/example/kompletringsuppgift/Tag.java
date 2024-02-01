package com.example.kompletringsuppgift;

import java.util.ArrayList;
import java.util.List;

public class Tag {

    private final int id;
    private String name;
    private List<Note> associatedNotes;

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
        this.associatedNotes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getAssociatedNotes() {
        return associatedNotes;
    }

    public void addAssociatedNote(Note note) {
        associatedNotes.add(note);
    }

    public void removeAssociatedNote(Note note) {
        associatedNotes.remove(note);
    }

    @Override
    public String toString() {
        return name;
    }
}
