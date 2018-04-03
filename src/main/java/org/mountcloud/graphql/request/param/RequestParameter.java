package org.mountcloud.graphql.request.param;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.graphql.request.param
 * @Description: TODO
 * @date 2018/2/11.
 */
public class RequestParameter extends HashMap<String,Object> {

    /**
     * 封装构造
     */
    private RequestParameter(){

    }

    /**
     * 添加一个属性
     * @param key
     * @param obj
     * @return
     */
    public RequestParameter addParameter(String key, Object obj){
        put(key,obj);
        return this;
    }

    /**
     * 从Map初始化
     * @param map
     * @return
     */
    public static RequestParameter buildByMap(Map map){
        RequestParameter requestParameter = build();
        requestParameter.putAll(map);
        return requestParameter;
    }

    public static RequestParameter build(){
        RequestParameter requestParameter = new RequestParameter();
        return requestParameter;
    }

    /**
     * 直接转为可用的string
     * @return
     */
    @Override
    public String toString() {

        String stringVal = "(";

        char connect = ',';

        Set<String> keys = keySet();
        for(String key:keys){
            stringVal = stringVal+ key+":"+packVal(get(key))+connect;
        }

        char last = stringVal.charAt(stringVal.length()-1);

        if(connect == last){
            stringVal = stringVal.substring(0,stringVal.length()-1);
        }

        stringVal = stringVal + ")";

        return stringVal;
    }

    /**
     * 根据值类型返回相应的字符串
     * @param val
     * @return
     */
    private String packVal(Object val){
        if(val==null){
            return "null";
        }
        if(val instanceof Integer
                || val instanceof Boolean
                || val instanceof Float
                || val instanceof Double){
            return String.valueOf(val);
        }
        return "\\\""+String.valueOf(val)+"\\\"";
    }
}
