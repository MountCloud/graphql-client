package org.mountcloud.graphql.request.result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.graphql.request.result
 * @Description: TODO
 * @date 2018/3/9.
 */
public class ResultAttributtes {

    private String name;

    //是否包含子属性
    public List<ResultAttributtes> resultAttributtes = new ArrayList<>();

    public ResultAttributtes(String name){
        this.name = name;
    }

    /**
     * 添加返回内容
     * @param resultAttr
     * @return
     */
    public ResultAttributtes addResultAttributes(String... resultAttr){
        if(resultAttr!=null&&resultAttr.length>0){
            for(String str : resultAttr){
                ResultAttributtes ra = new ResultAttributtes(str);
                resultAttributtes.add(ra);
            }

        }
        return this;
    }

    /**
     * 添加返回内容
     * @param resultAttr
     * @return
     */
    public ResultAttributtes addResultAttributes(ResultAttributtes... resultAttr){
        if(resultAttr!=null&&resultAttr.length>0){
            for(ResultAttributtes ra : resultAttr){
                resultAttributtes.add(ra);
            }

        }
        return this;
    }

    @Override
    public String toString() {
        if(resultAttributtes.size()==0){
            return name;
        }
        String str = name+"{";
        for(ResultAttributtes ra : resultAttributtes){
            str = str+" " + ra.toString();
        }
        str = str + " }";
        return str;
    }
}
