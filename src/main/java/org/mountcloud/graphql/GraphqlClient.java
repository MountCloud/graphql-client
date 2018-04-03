package org.mountcloud.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mountcloud.graphql.request.GraphqlRequestType;
import org.mountcloud.graphql.request.mutation.GraphqlMutation;
import org.mountcloud.graphql.request.query.GraphqlQuery;
import org.mountcloud.graphql.response.DefaultGraphqlResponse;
import org.mountcloud.graphql.response.GraphqlResponse;
import org.mountcloud.graphql.util.HttpClientUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.util
 * @Description: TODO 自己动手丰衣足食，网上没有那就自己写呗
 * @date 2018/2/11.
 */
public class GraphqlClient {

    private HttpClientUtil httpClientUtil = new HttpClientUtil();

    private String graphqlServerUrl = null;

    private Map<String,String> httpHeaders = new HashMap<String,String>();

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 以Token初始化GraphqlUtil
     * @param graphqlUrl graphql server url
     */
    private GraphqlClient(String graphqlUrl){
        this.graphqlServerUrl = graphqlUrl;
    }

    /**
     * 构建一个Graphql客户端
     * @param graphqlUrl  graphql server url
     * @return
     */
    public static GraphqlClient buildGraphqlClient(String graphqlUrl){
        GraphqlClient graphqlClient = new GraphqlClient(graphqlUrl);
        return graphqlClient;
    }
////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 执行查询
     * @param query exec query
     * @param <T>
     * @return
     */
    public <T extends GraphqlQuery> GraphqlResponse doQuery(T query) throws IOException {
        return doQuery(query, GraphqlRequestType.POST);
    }

    /**
     * 执行查询
     * @param query  exec query
     * @param graphqlRequestType request type get or post,but no get now
     * @param <T>
     * @return
     */
    public <T extends GraphqlQuery> GraphqlResponse doQuery(T query, GraphqlRequestType graphqlRequestType) throws IOException {
        String json = query.toString();
        String result = doHttpRequest(json,graphqlRequestType);
        if(result==null){
            return null;
        }
        GraphqlResponse graphqlResponse = objectMapper.readValue(result, DefaultGraphqlResponse.class);
        return graphqlResponse;
    }
/////////////////////////////////////////////////////////////////////////////////////

    /**
     * 执行操作
     * @param mutation exec mutation
     * @param <T>
     * @return
     */
    public <T extends GraphqlMutation> GraphqlResponse doMutation(T mutation) throws IOException {
        return doMutation(mutation,GraphqlRequestType.POST);
    }

    /**
     * 执行操作
     * @param mutation exec mutation
     * @param graphqlRequestType request type get or post,but no get now
     * @param <T>
     * @return
     */
    public <T extends GraphqlMutation> GraphqlResponse doMutation(T mutation, GraphqlRequestType graphqlRequestType) throws IOException {
        String json = mutation.toString();
        String result = doHttpRequest(json,graphqlRequestType);
        if(result==null){
            return null;
        }
        GraphqlResponse graphqlResponse = objectMapper.readValue(result, DefaultGraphqlResponse.class);

        return graphqlResponse;
    }

/////////////////////////////////////////////////////////////////////////////////////

    /***
     * 执行http请求
     * @param json 发送的json
     * @param type 发送方式
     * @return 返回执行结果
     * @throws IOException
     */
    private String doHttpRequest(String json,GraphqlRequestType type) throws IOException {
        String result = null;
        if(type.equals(GraphqlRequestType.POST)){
            result = httpClientUtil.doPostJson(graphqlServerUrl,json,this.httpHeaders);
        }else{
            //result =
        }
        return result;
    }

    /**
     * 设置http的头
     * @param headers
     */
    public void setHttpHeaders(Map<String,String> headers){
        this.httpHeaders = headers;
    }

}
