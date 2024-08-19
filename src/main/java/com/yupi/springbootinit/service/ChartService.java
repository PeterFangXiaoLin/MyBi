package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.dto.chart.ChartQueryRequest;
import com.yupi.springbootinit.model.entity.Chart;

/**
* @author helloworld
* @description 针对表【chart(图表信息)】的数据库操作Service
* @createDate 2024-08-01 15:54:43
*/
public interface ChartService extends IService<Chart> {
    QueryWrapper<Chart> getQueryWrapper(ChartQueryRequest chartQueryRequest);

}
