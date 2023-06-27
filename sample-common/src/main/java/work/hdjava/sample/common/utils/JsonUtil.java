package work.hdjava.sample.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Author: suncl
 * @Date: 2021/7/19 18:28
 */
public class JsonUtil extends JSON {
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
    }
}
