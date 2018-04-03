package org.mountcloud.graphql.request.mutation;

import org.mountcloud.graphql.request.GraphqlRequest;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.graphql.mutation
 * @Description: TODO Mutation父类
 * @date 2018/2/11.
 */
public abstract class GraphqlMutation extends GraphqlRequest {

    /**
     * 不可见的构造
     *
     * @param requestName
     */
    protected GraphqlMutation(String requestName) {
        super(requestName);
    }

    @Override
    public String toString() {
        String superStr = super.toString();
        return "{\"query\":\"mutation{"+superStr+"}\"}";
    }
    
}
