package com.sky.mapper;

import com.sky.annotations.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @AutoFill(OperationType.INSERT)
    void insert(Category category);

    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    List<Category> listCategoryByPage(CategoryPageQueryDTO queryDTO);

    @AutoFill(OperationType.UPDATE)
    void setCategoryStatus(Category category);

    @Delete("delete from sky_take_out.category where id=#{id}")
    void deleteById(Long id);

    @Select("select * from sky_take_out.category where type=#{type}")
    List<Category> listCategoryByType(Integer type);
}
