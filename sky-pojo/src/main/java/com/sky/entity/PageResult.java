package com.sky.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageResult {
    private Long total;
    private Object records;
}
