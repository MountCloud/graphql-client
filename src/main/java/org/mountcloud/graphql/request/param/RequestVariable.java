package org.mountcloud.graphql.request.param;

import java.util.HashMap;
import java.util.Set;

public class RequestVariable extends HashMap<String,Object> {

    private  String varibleName;

    public RequestVariable(String name) {
        this.varibleName = name;
    }

    public  RequestVariable addParameter(String key, Object obj){
        put(key,obj);
        return this;
    }


    @Override
    public String toString() {


        Set<String> keys = keySet();

        if(keys.size()==0){
            return "";
        }

        StringBuilder stringVal = new StringBuilder("{");

        char connect = ',';

        for(String key:keys){
            stringVal
                    .append("\"")
                    .append(key)
                    .append("\"")
                    .append(":")
                    .append(packVal(get(key)))
                    .append(connect);
        }

        char last = stringVal.charAt(stringVal.length()-1);

        if(connect == last){
            stringVal = new StringBuilder(stringVal.substring(0, stringVal.length() - 1));
        }

        stringVal.append("}");

        return "{\"" + this.varibleName + "\":" + stringVal + "}";
    }

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
        return "\"" + val + "\"";
    }
}
