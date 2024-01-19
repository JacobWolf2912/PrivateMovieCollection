package com.privatemoviecollection.ui.controllers;

import com.privatemoviecollection.be.Movie;
import com.privatemoviecollection.bll.MovieManager;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.ZoneId;

public class MovieController {
    public TextField txtFieldTitle;
    public TextField txtFieldRating;
    public TextField txtFieldFileLink;
    public DatePicker datePickerDate;
    public Button btnSave;
    public Button btnCancel;

    private MovieManager manager;

    public MovieController() {
        try {
            this.manager = new MovieManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveAction(ActionEvent actionEvent) {
        Movie movie = new Movie();

        movie.setName(this.txtFieldTitle.getText());
        movie.setRating(Integer.parseInt(this.txtFieldRating.getText()));
        movie.setFileLink(this.txtFieldFileLink.getText());
        movie.setDate(Date.from(datePickerDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        this.manager.createMovie(movie);

        clearForm();
    }

    public void btnCancelAction(ActionEvent actionEvent) {
        clearForm();

        Button btn = (Button) actionEvent.getSource();

        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    private void clearForm() {
        txtFieldTitle.clear();
        txtFieldRating.clear();
        txtFieldFileLink.clear();
        datePickerDate.getValue();
    }
}