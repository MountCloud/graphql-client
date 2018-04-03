package org.mountcloud.graphql.request.param;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 2018/2/11. 请求的参数
 * @author zhanghaishan
 * @version V1.0
 */
public class RequestParameter extends HashMap<String,Object> {

    /**
     * 封装构造
     */
    private RequestParameter(){

    }

    /**
     * 添加一个请求参数
     * @param key 参数名
     * @param obj 参数户的值
     * @return
     */
    public RequestParameter addParameter(String key, Object obj){
        put(key,obj);
        return this;
    }

    /**
     * 将Map内的值全部转为请求参数
     * @param map 需要转换的map
     * @return
     */
    public static RequestParameter buildByMap(Map map){
        RequestParameter requestParameter = build();
        requestParameter.putAll(map);
        return requestParameter;
    }

    /**
     * build一个graphql client
     * @return graphql client
     */
    public static RequestParameter build(){
        RequestParameter requestParameter = new RequestParameter();
        return requestParameter;
    }

    /**
     * 直接转为可用的string
     * @return request string
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
     * @param val 值
     * @return 封装后的字符串
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
