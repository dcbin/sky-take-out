package com.sky.service;

import com.github.pagehelper.PageInfo;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;

public interface SetmealService {

    PageInfo<Setmeal> listSetmealByPage(SetmealPageQueryDTO queryDTO);

    void updateSetmeal(SetmealDTO setmealDTO);


}
