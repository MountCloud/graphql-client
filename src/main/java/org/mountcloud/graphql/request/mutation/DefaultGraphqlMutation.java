package org.mountcloud.graphql.request.mutation;

/**
 * 2018/2/11.默认的graphql mutation
 * @author zhanghaishan
 * @version V1.0
 */
public class DefaultGraphqlMutation extends GraphqlMutation {

    /**
     * 构造
     *
     * @param requestName mutaion 的名字
     */
    public DefaultGraphqlMutation(String requestName) {
        super(requestName);
    }
}
