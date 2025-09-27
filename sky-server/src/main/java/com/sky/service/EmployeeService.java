package com.sky.service;

import com.github.pagehelper.PageInfo;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    PageInfo<Employee> listEmployeeByPage(EmployeePageQueryDTO pageQueryDTO);

    Employee selectById(Integer id);

    void update(EmployeeDTO employeeDTO);

    void setEmployeeAccountStatus(Long id, Integer status);
}
