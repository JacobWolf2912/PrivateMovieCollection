package com.privatemoviecollection.bll;
import java.io.IOException;
import java.util.List;

import com.privatemoviecollection.be.Movie;
import com.privatemoviecollection.dal.DbDaoMovie;


public class MovieManager {
    private DbDaoMovie dbDaoMovie;

    public MovieManager() throws IOException {
        this.dbDaoMovie = new DbDaoMovie();
    }

    public List<Movie> getAllMovies() {
        return this.dbDaoMovie.getAll();
    }

    public Movie getMovieById(int id) {
        return this.dbDaoMovie.getById(id);
    }

    public Movie createMovie(Movie movie) {
        return this.dbDaoMovie.create(movie);
    }

    public Movie updateMovie(Movie movie) {
        return this.dbDaoMovie.update(movie);
    }

    public void deleteMovie(Movie movie) {
        this.dbDaoMovie.delete(movie);
    }

    public Movie assignCategoryToMovie(int movieId, int categoryId) {
        return this.dbDaoMovie.assignCategory(movieId, categoryId);
    }

    public Movie removeCategoryFromMovie(int movieId, int categoryId) {
        return this.dbDaoMovie.removeCategory(movieId, categoryId);
    }
}
