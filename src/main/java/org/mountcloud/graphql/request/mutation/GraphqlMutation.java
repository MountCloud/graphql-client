package org.mountcloud.graphql.request.mutation;

import org.mountcloud.graphql.request.GraphqlRequest;

/**
 *  2018/2/11. Mutation父类
 * @author zhanghaishan
 * @version V1.0
 */
public abstract class GraphqlMutation extends GraphqlRequest {

    /**
     * 不可见的构造
     *
     * @param requestName mutation 的名字
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
