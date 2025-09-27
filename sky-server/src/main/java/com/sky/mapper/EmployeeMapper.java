package com.sky.mapper;

import com.sky.annotations.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from sky_take_out.employee where username like concat('%', #{username}, '%')")
    Employee getByUsername(String username);

    @AutoFill(OperationType.INSERT)
    void insert(Employee employee);

    List<Employee> listEmployeeByPage(EmployeePageQueryDTO pageQueryDTO);

    Employee selectEmpById(Integer id);

    @AutoFill(OperationType.UPDATE)
    void update(Employee employee);

    @AutoFill(OperationType.UPDATE)
    void setEmployeeAccountStatus(Employee emp);
}
