package com.privatemoviecollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.privatemoviecollection.be.Category;
import com.privatemoviecollection.be.Movie;
import com.privatemoviecollection.dal.db.ConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbDaoMovie {
    private ConnectionManager dbConnection;
    public DbDaoMovie() throws IOException {
        this.dbConnection = new ConnectionManager();
    }

    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();
        try(Connection conn = dbConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM movie")) {
            while (result.next()) {
                Movie movie = new Movie();
                movie.setId(result.getInt("id"));
                movie.setName(result.getString("name"));
                movie.setRating(result.getInt("rating"));
                movie.setFileLink(result.getString("fileLink"));
                movie.setDate(result.getDate("date"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public Movie getById(int id) {
        Movie movie = null;
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM movie WHERE id = ?")) {
            stmt.setInt(1, id);
            try(ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    movie = new Movie();
                    movie.setId(result.getInt("id"));
                    movie.setName(result.getString("name"));
                    movie.setRating(result.getInt("rating"));
                    movie.setFileLink(result.getString("fileLink"));
                    movie.setDate(result.getDate("date"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Movie create(Movie movie) {
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO movie (name, rating, fileLink, date) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, movie.getName());
            stmt.setInt(2, movie.getRating());
            stmt.setString(3, movie.getFileLink());
            stmt.setDate(4, new java.sql.Date(movie.getDate().getTime()));
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating movie failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movie.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating movie failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Movie update(Movie movie) {
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE movie SET name = ?, rating = ?, fileLink = ?, date = ? WHERE id = ?")) {
            stmt.setString(1, movie.getName());
            stmt.setInt(2, movie.getRating());
            stmt.setString(3, movie.getFileLink());
            stmt.setDate(4, new java.sql.Date(movie.getDate().getTime()));
            stmt.setInt(5, movie.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public void delete(Movie movie) {
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM movie WHERE id = ?")) {
            stmt.setInt(1, movie.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Movie assignCategory(int movieId, int categoryId) {
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO CatMovie (movieId, categoryId) VALUES (?, ?)")) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, categoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(movieId);
    }

    public Movie removeCategory(int movieId, int categoryId) {
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM CatMovie WHERE movieId = ? AND categoryId = ?")) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, categoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.getById(movieId);
    }

    private boolean isMovieInCategory(int movieId, int categoryId) {
        try(Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COUNT(*) FROM CatMovie WHERE movieId = ? AND categoryId = ?")) {
            stmt.setInt(1, movieId);
            stmt.setInt(2, categoryId);
            try(ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    return result.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
