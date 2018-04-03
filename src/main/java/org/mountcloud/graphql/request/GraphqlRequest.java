package org.mountcloud.graphql.request;

import org.mountcloud.graphql.request.param.RequestParameter;
import org.mountcloud.graphql.request.result.ResultAttributtes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.graphql.request
 * @Description: TODO 所有请求父类
 * @date 2018/2/11.
 */
public abstract class GraphqlRequest {

    protected String requestName;

    protected RequestParameter requestParameter;

    protected List<ResultAttributtes> resultAttributes = new ArrayList<ResultAttributtes>();

    /**
     * 不可见的构造
     */
    protected GraphqlRequest(String requestName){
        this.requestName = requestName;
    }

    /**
     * 添加请求的参数
     * @param key
     * @param val
     * @return
     */
    public RequestParameter addParameter(String key, Object val){
        return getRequestParameter().addParameter(key,val);
    }

    /**
     * 返回参数列表
     * @return
     */
    public RequestParameter getRequestParameter(){
        if(this.requestParameter==null){
            this.requestParameter = RequestParameter.build();
        }
        return this.requestParameter;
    }

    /**
     * 添加返回内容
     * @param resultAttr
     * @return
     */
    public GraphqlRequest addResultAttributes(String... resultAttr){
        if(resultAttr!=null&&resultAttr.length>0){
            for(String str : resultAttr){
                ResultAttributtes ra = new ResultAttributtes(str);
                resultAttributes.add(ra);
            }

        }
        return this;
    }

    /**
     * 添加返回内容
     * @param resultAttr
     * @return
     */
    public GraphqlRequest addResultAttributes(ResultAttributtes... resultAttr){
        if(resultAttr!=null&&resultAttr.length>0){
            for(ResultAttributtes ra : resultAttr){
                resultAttributes.add(ra);
            }

        }
        return this;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    /**
     * 转为一个可用的json
     * @return
     */
    @Override
    public String toString() {

        StringBuffer requestBuffer = new StringBuffer(requestName);

        //参数列表字符串
        String paramStr = getRequestParameter().toString();

        StringBuffer resultAttrBuffer = new StringBuffer("");
        boolean first = true;
        //第一个前面不拼接","
        for(ResultAttributtes ra :resultAttributes) {
            if(first) {
                first=false;
            }else{
                resultAttrBuffer.append(" ");
            }
            resultAttrBuffer.append(ra.toString());
        }

        String resultAttrStr = resultAttrBuffer.toString();

        requestBuffer.append(paramStr);
        requestBuffer.append("{");
        requestBuffer.append(resultAttrStr);
        requestBuffer.append("}");

        return requestBuffer.toString();
    }
}
