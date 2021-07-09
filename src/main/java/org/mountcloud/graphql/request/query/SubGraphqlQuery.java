package org.mountcloud.graphql.request.query;

import org.mountcloud.graphql.request.GraphqlRequest;

/**
 * 2021/7/9. 子请求 query 的总父类
 *
 * @author firstmetcs
 * @version V1.0
 */
public abstract class SubGraphqlQuery extends GraphqlRequest {

    /**
     * 不可见的构造
     *
     * @param requestName query的名字
     */
    protected SubGraphqlQuery(String requestName) {
        super(requestName);
    }

    @Override
    public String toString() {
        String superStr = super.toString();
        return superStr;
    }

}
