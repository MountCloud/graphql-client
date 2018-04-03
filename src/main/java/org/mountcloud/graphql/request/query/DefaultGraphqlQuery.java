package org.mountcloud.graphql.request.query;

/**
 * 2018/2/11. 默认的query
 * @author zhanghaishan
 * @version V1.0
 */
public class DefaultGraphqlQuery extends GraphqlQuery {

    /**
     * 构造
     *
     * @param requestName query的名字
     */
    public DefaultGraphqlQuery(String requestName) {
        super(requestName);
    }
}
