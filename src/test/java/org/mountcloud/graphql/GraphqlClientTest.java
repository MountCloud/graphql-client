package org.mountcloud.graphql;

import org.junit.Test;
import org.mountcloud.graphql.request.mutation.DefaultGraphqlMutation;
import org.mountcloud.graphql.request.mutation.GraphqlMutation;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.GraphqlQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO:
 * org.mountcloud.graphql
 * 2018/4/7.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class GraphqlClientTest {

    @Test
    public void simpleQuery(){
        //graphql服务器地址
        String serverUrl = "http://localhost:8080/graphql";
        //build一个新的graphqlclient
        GraphqlClient graphqlClient = GraphqlClient.buildGraphqlClient(serverUrl);

        //如果说graphql需要健全信息我们可以用map来进行传递
        Map<String,String> httpHeaders = new HashMap<>();
        httpHeaders.put("token","graphqltesttoken");
        //设置http请求的头
        graphqlClient.setHttpHeaders(httpHeaders);

        //创建一个Query并设置query的名字为findUser
        GraphqlQuery query = new DefaultGraphqlQuery("findUser");
        //我们需要查询user的 name，sex还有age，设置需要查询的这三个属性。

        query.addResultAttributes("name","sex","age");

        System.out.println(query.toString());

        //创建一个mutation并设置名字为updateUser
        GraphqlMutation mutation = new DefaultGraphqlMutation("updateUser");
        mutation.addResultAttributes("name","sex","age");
        System.out.println(mutation.toString());

    }
}
