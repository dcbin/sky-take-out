package com.sky.controller.admin;

import com.github.pagehelper.PageInfo;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @GetMapping("/page")
    Result<PageResult> listSetmealByPage(@RequestParam(required = true) Integer pageSize, @RequestParam(required = true) Integer page, Integer categoryId, Integer status, String name) {
        SetmealPageQueryDTO setmealPageQueryDTO = SetmealPageQueryDTO.builder()
                .categoryId(categoryId)
                .name(name)
                .page(page)
                .pageSize(pageSize)
                .status(status)
                .build();
        PageInfo<Setmeal> listSetmealByPage = setmealService.listSetmealByPage(setmealPageQueryDTO);
        PageResult pageResult = new PageResult(listSetmealByPage.getTotal(),
                                               listSetmealByPage.getList());
        return Result.success(pageResult);
    }
}
