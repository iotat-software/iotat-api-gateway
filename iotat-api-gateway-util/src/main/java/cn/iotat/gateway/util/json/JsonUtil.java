package cn.iotat.gateway.util.json;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * fastjson的封装工具类
 */
public class JsonUtil {
    /**
     * 数组转换为json字串
     *
     * @param objects 数组
     * @return json字串
     */
    public static String array2JsonStr(Object[] objects) {
        List<Object> objectList = Arrays.asList(objects);
        return JSON.toJSONString(objectList);
    }

    /**
     * map转换为json字串
     *
     * @param map map
     * @return json字串
     */
    public static String map2JsonStr(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }


}
