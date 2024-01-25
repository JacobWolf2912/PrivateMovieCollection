package com.privatemoviecollection.ui.controllers;

import com.privatemoviecollection.be.Category;
import com.privatemoviecollection.be.Movie;
import com.privatemoviecollection.bll.CategoryManager;
import com.privatemoviecollection.bll.MovieManager;
import com.privatemoviecollection.ui.HelloApplication;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    public Button categoryDeleteButton;
    public Button movieNewButton;
    @FXML
    public TableView<Category> categoryTableView;
    @FXML
    public TableView<Movie> allMoviesTableView;
    public ListView<Movie> songsInPlaylistListView;
    public TableColumn<Movie,String> nameColumn;
    public TableColumn<Movie,Integer> ratingColumn;
    public TableColumn<Movie,Date> lastViewColumn;
    public TableColumn<Movie,String> fileLinkColumn;
    public TableColumn<Category,String> categoryColumn;
    public MovieManager movieManager;
    public CategoryManager categoryManager;


    @FXML
    private Label welcomeText;


    public MainController() throws IOException {
        movieManager = new MovieManager();
        categoryManager = new CategoryManager();
    }

    @FXML
    public void playlistNewButtonAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/new-category.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    @FXML
    public void deleteButtonAction(ActionEvent event) throws IOException {
        /*
        if (getSelectedPlaylist() == null)
            return;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("delete-playlist.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Delete");  
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        PlaylistController playlistController = fxmlLoader.getController();
        playlistController.setSelectedPlaylist(getSelectedPlaylist());
        playlistController.setMainController(this);

         */
    }
    @FXML
    public void songNewButtonAction(ActionEvent event) throws IOException {
        String path = HelloApplication.class.getResource("/new-movie.fxml").getPath();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/new-movie.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTables();
    }


    @FXML
    public void initializeTables() {
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        ratingColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getRating()));
        lastViewColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDate()));
        fileLinkColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getFileLink()));
        categoryColumn.setCellValueFactory(cellData -> new  ReadOnlyStringWrapper(cellData.getValue().getName()));
        allMoviesTableView.setItems(FXCollections.observableList(movieManager.getAllMovies()));
        categoryTableView.setItems(FXCollections.observableList(categoryManager.getAllCategories()));
    }

    public void addMovieToCategory(MouseEvent mouseEvent) {
        Category selectedCategory = categoryTableView.getSelectionModel().getSelectedItem();
        Movie selectedMovie = allMoviesTableView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null && selectedMovie != null) {
            MovieManager movieManager = null;
            try {
                movieManager = new MovieManager();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (movieManager != null) {

                movieManager.assignCategoryToMovie(selectedMovie.getId(), selectedCategory.getId());
            }
        }
    }

    public void deletMovieInCategoryButtonAction(ActionEvent actionEvent) {
        Category selectedCategory = categoryTableView.getSelectionModel().getSelectedItem();
        Movie selectedMovie = songsInPlaylistListView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null && selectedMovie != null) {
            MovieManager movieManager = null;
            try {
                movieManager = new MovieManager();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (movieManager != null) {

                movieManager.removeCategoryFromMovie(selectedMovie.getId(), selectedCategory.getId());
            }
        }
    }
}