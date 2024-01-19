package com.privatemoviecollection.ui.controllers;

import com.privatemoviecollection.bll.CategoryManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import com.privatemoviecollection.be.Category;
import com.privatemoviecollection.dal.DbDaoCategory;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoryController {
    public TextField txtFieldCategoryName;

    private CategoryManager categoryManager;

    public CategoryController() {
        try {
            this.categoryManager = new CategoryManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveAction(ActionEvent actionEvent) {
        Category category = new Category();

        category.setName(this.txtFieldCategoryName.getText());

        this.categoryManager.createCategory(category);

        clearForm();
    }

    public void btnCancelAction(ActionEvent actionEvent) {
        clearForm();

        Button btn = (Button) actionEvent.getSource();

        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    private void clearForm() {
        txtFieldCategoryName.clear();
    }
}
