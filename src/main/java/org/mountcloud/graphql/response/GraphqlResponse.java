package org.mountcloud.graphql.response;

import java.util.Map;

/**
 * 2018/2/12. graphql response父接口
 * @author zhanghaishan
 * @version V1.0
 */
public interface GraphqlResponse {

    /**
     * 返回数据
     * @return data
     */
    Map getData();

}
