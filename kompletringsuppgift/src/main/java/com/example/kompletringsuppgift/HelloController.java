package com.example.kompletringsuppgift;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField titleField;

    @FXML
    private Button addTagButton;

    @FXML
    private ListView<String> noteList;

    @FXML
    private TextField contentField;

    @FXML
    private Button saveNoteButton;

    @FXML
    private Button deleteNoteButton;

    @FXML
    private ListView<String> tagList;

    @FXML
    private void addTag() {
        // Handle the "Add Tag" button click event
    }

    @FXML
    private void showNoteDetails() {
        // Handle the note list item selection event
        String selectedNote = noteList.getSelectionModel().getSelectedItem();
        // Perform actions based on the selected note
    }

    @FXML
    private void saveNote() {
        // Handle the "Save Note" button click event
    }

    @FXML
    private void deleteNote() {
        // Handle the "Delete Note" button click event
    }

    @FXML
    private void removeTag() {
        // Handle the tag list item selection event
        String selectedTag = tagList.getSelectionModel().getSelectedItem();
        // Perform actions based on the selected tag
    }
}
