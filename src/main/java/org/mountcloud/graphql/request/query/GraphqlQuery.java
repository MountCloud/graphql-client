package org.mountcloud.graphql.request.query;

import org.mountcloud.graphql.request.GraphqlRequest;

/**
 * 2018/2/11. query 的总父类
 * @author zhanghaishan
 * @version V1.0
 */
public abstract class GraphqlQuery extends GraphqlRequest {

    /**
     * 不可见的构造
     *
     * @param requestName query的名字
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
