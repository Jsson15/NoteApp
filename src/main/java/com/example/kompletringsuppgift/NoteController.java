package com.example.kompletringsuppgift;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class NoteController {

    private ObservableList<Note> notes = FXCollections.observableArrayList();

    @FXML
    private ListView<Note> notesListView;

    @FXML
    private Button addButton;

    @FXML
    private Button addNoteButton;

    @FXML
    private Button removeButton;

    @FXML
    private TextField titleField;

    @FXML
    private TextArea contentArea;

    @FXML
    private ListView<Tag> tagsListView;

    @FXML
    private TagInputController tagInputController;

    @FXML
    private void addNoteButtonClicked() {
        String title = titleField.getText();
        String content = contentArea.getText();

        if (!title.isEmpty() || !content.isEmpty()) {
            Note newNote = new Note();
            newNote.setTitle(title);
            newNote.setContent(content);
            notes.add(newNote);

            updateNoteUI(newNote);

            titleField.clear();
            contentArea.clear();
        }
    }

    @FXML
    private void addButtonClicked() {
        titleField.clear();
        contentArea.clear();
    }

    @FXML
    private void removeButtonClicked() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            notes.remove(selectedNote);
            notesListView.getSelectionModel().clearSelection();
            titleField.clear();
            contentArea.clear();
        }
    }

    @FXML
    private void initialize() {
        notesListView.setItems(notes);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("taginput.fxml"));
        Parent tagInputParent;

        try {
            tagInputParent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        tagInputController = loader.getController();
        tagInputController.setListViews(notesListView, tagsListView);



        notesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateNoteUI(newValue);
            }
        });
    }

    @FXML
    private void addTagButtonClicked() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            TextInputDialog dialog = new TextInputDialog("New Tag");
            dialog.setTitle("Add Tag");
            dialog.setHeaderText("Enter the new tag:");
            dialog.setContentText("Tag:");

            dialog.showAndWait().ifPresent(tagName -> {
                int tagId = selectedNote.getTags().size() + 1;
                Tag tag = new Tag(tagId, tagName);
                selectedNote.addTag(tag);
                tagInputController.updateTagsListView(selectedNote.getTags());
            });
        }
    }

    @FXML
    private void removeTagButtonClicked() {
        Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
        if (selectedNote != null) {
            tagInputController.removeTagButtonClicked();
        }
    }

    @FXML
    void updateTagsListView(List<Tag> tags) {
        tagsListView.getItems().setAll(tags);
        tagsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayAssociatedNotes(newValue.getAssociatedNotes());
            }
        });
    }

    void displayAssociatedNotes(List<Note> notes) {
        StringBuilder notesInfo = new StringBuilder("Associated Notes:\n");

        for (Note note : notes) {
            notesInfo.append("Title: ").append(note.getTitle()).append("\n");
            notesInfo.append("Content: ").append(note.getContent()).append("\n\n");
        }

        titleField.setText("Associated Notes");
        contentArea.setText(notesInfo.toString());
    }


    @FXML
    public TextField getTitleField() {
        return titleField;
    }

    @FXML
    public TextArea getContentArea() {
        return contentArea;
    }

    private void updateNoteUI(Note note) {
        titleField.setText(note.getTitle());
        contentArea.setText(note.getContent());
    }
}
