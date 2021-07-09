package org.mountcloud.graphql.request;

import java.util.ArrayList;
import java.util.List;

/**
 * 2021/7/9. 多请求父类
 *
 * @author firstmetcs
 * @version V1.0
 */
public class MultiGraphqlRequest {

    protected List<GraphqlRequest> subGraphqlRequests = new ArrayList<>();

    /**
     * 转为一个可用的json
     *
     * @return 返回requet的json字符串
     */
    @Override
    public String toString() {

        StringBuffer requestBuffer = new StringBuffer();

        // subQueryObjects
        if (subGraphqlRequests.size() != 0) {
            for (GraphqlRequest subGraphqlRequest : subGraphqlRequests) {
                requestBuffer.append(" " + subGraphqlRequest.toString());
            }
        }

        return requestBuffer.toString();
    }

    public void put(GraphqlRequest subGraphqlRequest) {
        subGraphqlRequests.add(subGraphqlRequest);
    }
}
