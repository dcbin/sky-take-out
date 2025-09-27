package com.sky.service;

import com.github.pagehelper.PageInfo;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;

import java.util.List;

public interface CategoryService {
    void save(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    PageInfo<Category> listCategoryByPage(CategoryPageQueryDTO queryDTO);

    void setCategoryStatus(Long id, Integer status);

    void deleteById(Long id);

    List<Category> listCategoryByType(Integer type);
}
