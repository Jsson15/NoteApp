package com.example.kompletringsuppgift;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.stream.Collectors;

public class TagInputController {

    @FXML
    private TextField tagInputField;

    @FXML
    private Button addTagButton;

    @FXML
    private Button removeTagButton;

    @FXML
    private ListView<Tag> allTagsListView;

    private ListView<Note> notesListView;
    private ListView<Tag> tagsListView;

    private NoteController noteController;

    public void setListViews(ListView<Note> notesListView, ListView<Tag> tagsListView) {
        this.notesListView = notesListView;
        this.tagsListView = tagsListView;
    }

    public void setNoteController(NoteController noteController) {
        this.noteController = noteController;
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
                Tag tag = new Tag(allTagsListView.getItems().size() + 1, tagName);
                allTagsListView.getItems().add(tag);

                // Associate the tag with the selected note
                selectedNote.getTags().add(0,tag);
                tag.addAssociatedNote(selectedNote);

                updateTagsListView(selectedNote.getTags());
            });
        }
    }

    @FXML
    void removeTagButtonClicked() {
        Tag selectedTag = allTagsListView.getSelectionModel().getSelectedItem();
        if (selectedTag != null) {
            // Remove the tag from the associated notes
            selectedTag.getAssociatedNotes().forEach(note -> note.getTags().remove(selectedTag));

            // Remove the tag from the allTagsListView
            allTagsListView.getItems().remove(selectedTag);

            // Update the tagsListView
            Note selectedNote = notesListView.getSelectionModel().getSelectedItem();
            if (selectedNote != null) {
                updateTagsListView(selectedNote.getTags());
            }
        }
    }


    @FXML
    void updateTagsListView(ObservableList<? extends Tag> tags) {
        tagsListView.getItems().setAll(tags);
        tagsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayAssociatedNotes(newValue.getAssociatedNotes());
            }
        });
    }





    @FXML
    void displayAssociatedNotes(List<Note> notes) {
        if (!notes.isEmpty() && noteController != null) {
            Note selectedNote = notes.get(0);
            noteController.getTitleField().setText(selectedNote.getTitle());
            noteController.getContentArea().setText(selectedNote.getContent());
        }
    }

}
