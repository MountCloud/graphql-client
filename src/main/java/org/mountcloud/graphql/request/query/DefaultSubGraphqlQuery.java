package org.mountcloud.graphql.request.query;

/**
 * 2021/7/9. 默认的子请求query
 *
 * @author firstmetcs
 * @version V1.0
 */
public class DefaultSubGraphqlQuery extends SubGraphqlQuery {

    /**
     * 构造
     *
     * @param requestName query的名字
     */
    public DefaultSubGraphqlQuery(String requestName) {
        super(requestName);
    }
}
