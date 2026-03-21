package com.onik.eduspring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页结果封装类
 */
@Data
@AllArgsConstructor
public class PageResult<T> {

    private Long total;
    private List<T> rows;
}
