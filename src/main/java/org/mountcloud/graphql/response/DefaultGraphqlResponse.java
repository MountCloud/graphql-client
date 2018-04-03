package org.mountcloud.graphql.response;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2018/2/12. 默认的返回结果 基本蛮族一切
 * @author zhanghaishan
 * @version V1.0
 */
public class DefaultGraphqlResponse extends LinkedHashMap implements GraphqlResponse {
    @Override
    public Map getData() {
        return this;
    }
}
