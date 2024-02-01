package com.example.kompletringsuppgift;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Note {

    private final int id;
    private String title;
    private String content;
    private final ObservableList<Tag> tags;

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = FXCollections.observableArrayList();
    }

    public Note() {
        this(0, "", "");
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ObservableList<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.addAssociatedNote(this); // Associate the note with the tag
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.removeAssociatedNote(this); // Remove the association
    }

    public void reset() {
        this.title = "";
        this.content = "";
    }

    @Override
    public String toString() {
        return title;
    }
}
