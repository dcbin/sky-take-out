package com.sky.mapper;

import com.sky.annotations.AutoFill;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealMapper {

    /**
     * 条件分页查询,get传参
     * @param queryDTO
     * @return
     */
    List<Setmeal> listSetmealByPage(SetmealPageQueryDTO queryDTO);

    @AutoFill(OperationType.UPDATE)
    void update(SetmealDTO setmealDTO);
}
