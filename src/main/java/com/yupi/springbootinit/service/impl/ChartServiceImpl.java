package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.springbootinit.constant.CommonConstant;
import com.yupi.springbootinit.mapper.ChartMapper;
import com.yupi.springbootinit.model.dto.chart.ChartQueryRequest;
import com.yupi.springbootinit.model.entity.Chart;
import com.yupi.springbootinit.service.ChartService;
import com.yupi.springbootinit.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author helloworld
* @description 针对表【chart(图表信息)】的数据库操作Service实现
* @createDate 2024-08-01 15:54:43
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService{

    @Override
    public QueryWrapper<Chart> getQueryWrapper(ChartQueryRequest chartQueryRequest) {
        QueryWrapper<Chart> queryWrapper = new QueryWrapper<>();
        if (chartQueryRequest == null) {
            return queryWrapper;
        }
        String sortField = chartQueryRequest.getSortField();
        String sortOrder = chartQueryRequest.getSortOrder();
        Long id = chartQueryRequest.getId();
        String name = chartQueryRequest.getName();
        String goal = chartQueryRequest.getGoal();
        String chartType = chartQueryRequest.getChartType();
        Long userId = chartQueryRequest.getUserId();
        // 拼接查询条件
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.like(StringUtils.isNotBlank(goal), "goal", goal);
        queryWrapper.eq(StringUtils.isNotBlank(chartType), "chart_type", chartType);

        queryWrapper.eq(id != null && id > 0, "id", id);
        queryWrapper.eq(userId != null && userId > 0, "userId", userId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




