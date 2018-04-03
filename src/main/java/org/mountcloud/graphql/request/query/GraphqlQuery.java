package org.mountcloud.graphql.request.query;

import org.mountcloud.graphql.request.GraphqlRequest;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.graphql.query
 * @Description: TODO query父类
 * @date 2018/2/11.
 */
public abstract class GraphqlQuery extends GraphqlRequest {

    /**
     * 不可见的构造
     *
     * @param requestName
     */
    protected GraphqlQuery(String requestName) {
        super(requestName);
    }

    @Override
    public String toString() {
        String superStr = super.toString();
        return "{\"query\":\"{"+superStr+"}\"}";
    }

}
