package org.mountcloud.graphql.response;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.graphql.response
 * @Description: TODO
 * @date 2018/2/12.
 */
public class DefaultGraphqlResponse extends LinkedHashMap implements GraphqlResponse {
    @Override
    public Map getData() {
        return this;
    }
}
