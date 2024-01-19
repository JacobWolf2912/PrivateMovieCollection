package com.privatemoviecollection.ui.controllers;

import com.privatemoviecollection.be.Category;
import com.privatemoviecollection.be.Movie;
import com.privatemoviecollection.bll.MovieManager;
import com.privatemoviecollection.ui.HelloApplication;
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
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    public Button categoryDeleteButton;
    public Button songsInCategoryDeleteButton;
    public Button movieNewButton;
    @FXML
    public TableView<Category> playlistsTableView;
    @FXML
    public Button moveSongToPlaylistButton;
    public TableColumn totalTimeColumn;
    @FXML
    public TableView<Movie> allMoviesTableView;
    public ListView<Movie> songsInPlaylistListView;
    public TableColumn playlistNameColumn;


    @FXML
    private Label welcomeText;


    public MainController() throws IOException {
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
    public void playlistEditButtonAction(ActionEvent event) throws IOException {
        /*
        if (getSelectedPlaylist() == null)
            return;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("edit-playlist.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        PlaylistController playlistController = fxmlLoader.getController();
        playlistController.setSelectedPlaylist(getSelectedPlaylist());
        playlistController.setMainController(this);
         */
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
    public void songEditButtonAction(ActionEvent event) throws IOException {
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AllMovies-edit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

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
        // TODO:
    }

    public void playlistDeleteButtonAction(ActionEvent actionEvent) throws IOException {
        /*
        if (getSelectedPlaylist() == null)
            return;
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("delete-playlist.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Delete Category?");
        stage.setScene(scene);
        stage.show();

        PlaylistController playlistController = loader.getController();
        playlistController.setSelectedPlaylist(getSelectedPlaylist());
        playlistController.setMainController(this);

         */
    }

    public void moveSongtoPlaylist(ActionEvent actionEvent) {
        /*
        if (getSelectedPlaylist() == null || getSelectedSong() == null)
            return;
        categoryManager.assignSong(getSelectedPlaylist(),getSelectedSong(),1);

         */
    }

    public void removeSongButtonAction(ActionEvent actionEvent) {
        /*
        var selectedSong = songsInPlaylistListView.getSelectionModel().getSelectedItem();
        if (selectedSong == null)
            return;
        categoryManager.removeSong(selectedSong.getPlaylistId(), selectedSong.getSongId());

         */
    }

    public void addMovieToCategory(MouseEvent mouseEvent) {
        Category selectedCategory = playlistsTableView.getSelectionModel().getSelectedItem();
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
        Category selectedCategory = playlistsTableView.getSelectionModel().getSelectedItem();
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