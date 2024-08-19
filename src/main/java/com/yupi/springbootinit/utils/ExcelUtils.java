package com.yupi.springbootinit.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * excel 工具类
 */
@Slf4j
public class ExcelUtils {
    public static String excelToCsv(MultipartFile multipartFile) {
//        File file = null;
//        try {
//            file = ResourceUtils.getFile("classpath:网站数据.xlsx");
//        } catch (FileNotFoundException e) {
//
//        }
        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
//            throw new RuntimeException(e);
            log.error("文件解析失败", e);
        }

        LinkedHashMap<Integer, String> head = (LinkedHashMap<Integer, String>) list.get(0);

        StringBuilder result = new StringBuilder();

        List<String> headData = head.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());

        result.append(StringUtils.join(headData, ",")).append("\n");

        for (int i = 1; i < list.size(); i++) {
            LinkedHashMap<Integer, String> data = (LinkedHashMap<Integer, String>) list.get(i);
            List<String> dataList = data.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());

            result.append(StringUtils.join(dataList, ",")).append("\n");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String s = excelToCsv(null);
        System.out.println(s);
    }
}
