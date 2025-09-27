package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.EmployeeMapper;
import com.sky.service.CategoryService;
import com.sky.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .sort(categoryDTO.getSort())
                .status(0)
                .type(categoryDTO.getType())
                .build();
        categoryMapper.insert(category);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .id(categoryDTO.getId())
                .type(categoryDTO.getType())
                .sort(categoryDTO.getSort())
                .name(categoryDTO.getName())
                .build();
        categoryMapper.update(category);
    }

    @Override
    public PageInfo<Category> listCategoryByPage(CategoryPageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());
        List<Category> categories = categoryMapper.listCategoryByPage(queryDTO);
        return new PageInfo<>(categories);
    }

    @Override
    public void setCategoryStatus(Long id, Integer status) {
        Category category = Category.builder()
                .status(status)
                .id(id)
                .build();
        categoryMapper.setCategoryStatus(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryMapper.deleteById(id);
    }

    @Override
    public List<Category> listCategoryByType(Integer type) {
        return categoryMapper.listCategoryByType(type);
    }
}
