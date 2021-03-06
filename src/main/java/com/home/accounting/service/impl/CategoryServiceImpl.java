package com.home.accounting.service.impl;

import com.home.accounting.entity.Category;
import com.home.accounting.entity.User;
import com.home.accounting.repository.CategoryRepository;
import com.home.accounting.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired/*позволит Spring инициализировать наш сервис*/
    private CategoryRepository categoryRepository;/*объевление нашего сервиса (обратите внимание, что это интерфейс, а не реализация), который позволит нам использовать его бизнес-логику*/

    @Override
    public void addCategory(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public void editCategory(Category category) {
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category findCategory(long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public List<Category> listAllCategories() /*получаем все данные с БД*/ {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> listCategoriesByUser(User user) {
        return categoryRepository.findCategoriesByUser(user);
    }

    @Override
    public boolean isCategoryUnique(Category category) {
        return category.equals(findCategoryByName(category.getName())); //(categoryFind == null || ((category.getId() != null) && (category.getId()==categoryFind.getId())));;
    }

    @Override
    public boolean isCategoryUnique(Category category, User user) {
        Category categ = categoryRepository.findCategoriesByUserAndName(user, category.getName());
        if (categ == null) return true;
        return category.equals(categ.getName());
    }
}
