package com.xzx.dic.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzx.dic.entity.Dic;

import java.util.ArrayList;

/**
 * Created by xzx14 on 2017/6/15.
 */
public class JsonDic {
    public static String getJson(Dic dic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dic);

    }
    public static String getArrayListJson(ArrayList<Dic> arrayList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(arrayList);
    }
}
