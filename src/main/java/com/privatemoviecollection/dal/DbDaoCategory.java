package com.privatemoviecollection.dal;

import com.privatemoviecollection.be.Category;
import com.privatemoviecollection.dal.db.ConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbDaoCategory {
    private ConnectionManager dbConnection;

    public DbDaoCategory() throws IOException {
        this.dbConnection = new ConnectionManager();
    }

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try (
                Connection conn = dbConnection.getConnection();
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM Category")) {
            while (result.next()) {
                Category category = new Category();
                category.setId(result.getInt("id"));
                category.setName(result.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category getById(int id) {
        Category category = null;
        try (
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Category WHERE id = ?")) {
            stmt.setInt(1, id);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    category = new Category();
                    category.setId(result.getInt("id"));
                    category.setName(result.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public Category create(Category category) {
        try (
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Category (name) VALUES (?)",
                        Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, category.getName());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating category failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating category failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public Category update(Category category) {
        try (
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "UPDATE Category SET name = ? WHERE id = ?")) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public void delete(Category category) {
        try (
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Category WHERE id = ?")) {
            stmt.setInt(1, category.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}