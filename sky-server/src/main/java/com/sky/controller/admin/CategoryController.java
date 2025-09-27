package com.sky.controller.admin;

import com.github.pagehelper.PageInfo;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result<Object> save(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return Result.success();
    }

    @PutMapping
    public Result<Object> update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result<PageResult> listByPage(CategoryPageQueryDTO pageQueryDTO) {
        PageInfo<Category> page = categoryService.listCategoryByPage(pageQueryDTO);
        PageResult result = PageResult.builder()
                                      .total(page.getTotal())
                                      .records(page.getList())
                                      .build();
        return Result.success(result);
    }

    @PostMapping("/status/{status}")
    public Result<Object> setStatus(@PathVariable("status") Integer status,
                                    @RequestParam("id") Long id) {
        categoryService.setCategoryStatus(id, status);
        return Result.success();
    }

    @DeleteMapping
    public Result<Object> deleteById(Category category) {
        categoryService.deleteById(category.getId());
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> listCategoryByType(@RequestParam(required = false) Integer type) {
        List<Category> categories = categoryService.listCategoryByType(type);
        return Result.success(categories);
    }
}
