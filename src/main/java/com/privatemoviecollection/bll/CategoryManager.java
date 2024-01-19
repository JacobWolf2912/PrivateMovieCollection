package com.privatemoviecollection.bll;

import com.privatemoviecollection.be.Category;
import com.privatemoviecollection.dal.DbDaoCategory;

import java.io.IOException;
import java.util.List;

public class CategoryManager {
    private DbDaoCategory dbDaoCategory;

    public CategoryManager() throws IOException{
        this.dbDaoCategory = new DbDaoCategory();
    }

    public List<Category> getAllCategories() {
        return dbDaoCategory.getAll();
    }

    public Category getCategoryById(int id) {
        return dbDaoCategory.getById(id);
    }

    public Category createCategory(Category category) {
        return dbDaoCategory.create(category);
    }

    public Category updateCategory(Category category) {
        return dbDaoCategory.update(category);
    }

    public void deleteCategory(Category category) {
        dbDaoCategory.delete(category);
    }
}