package com.sky.vo;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class EmployeePageVO {
    private Long total;
    private Object records;
}
