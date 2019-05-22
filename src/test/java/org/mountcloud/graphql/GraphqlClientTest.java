package org.mountcloud.graphql;

import org.junit.Assert;
import org.junit.Test;
import org.mountcloud.graphql.request.mutation.DefaultGraphqlMutation;
import org.mountcloud.graphql.request.mutation.GraphqlMutation;
import org.mountcloud.graphql.request.param.RequestVariable;
import org.mountcloud.graphql.request.query.DefaultGraphqlQuery;
import org.mountcloud.graphql.request.query.Graphql;
import org.mountcloud.graphql.request.query.GraphqlQuery;
import org.mountcloud.graphql.request.result.ResultAttributtes;

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
    public void simpleQuery() {
        //graphql服务器地址
        String serverUrl = "http://localhost:8080/graphql";
        //build一个新的graphqlclient
        GraphqlClient graphqlClient = GraphqlClient.buildGraphqlClient(serverUrl);

        //如果说graphql需要健全信息我们可以用map来进行传递
        Map<String, String> httpHeaders = new HashMap<>();
        httpHeaders.put("token", "graphqltesttoken");
        //设置http请求的头
        graphqlClient.setHttpHeaders(httpHeaders);

        //创建一个Query并设置query的名字为findUser
        GraphqlQuery query = new DefaultGraphqlQuery("findUser");
        //我们需要查询user的 name，sex还有age，设置需要查询的这三个属性。

        query.addResultAttributes("name", "sex", "age");

        System.out.println(query.toString());

        //创建一个mutation并设置名字为updateUser
        GraphqlMutation mutation = new DefaultGraphqlMutation("updateUser");
        mutation.addResultAttributes("name", "sex", "age");
        System.out.println(mutation.toString());

    }

    @Test
    public void legacyQuery() {
        final String result = "{\"query\":\"{findUser{name sex age}}\"}";
        GraphqlQuery query = new DefaultGraphqlQuery("findUser");
        query.addResultAttributes("name", "sex", "age");
        Assert.assertEquals(result,query.toString());
    }

    @Test
    public void legacyQueryII() {
        final String result = "{\"query\":\"{findUsers(sex:\\\"man\\\",age:11){id name age sex class{ name code school{ name } } school{ name }}}\"}";
        GraphqlQuery query = new DefaultGraphqlQuery("findUsers");
//add query or mutation param
        query.addParameter("sex", "man").addParameter("age", 11);
//add query response basics attribute
        query.addResultAttributes("id", "name", "age", "sex");
//add query complex attributes
        ResultAttributtes classAttributte = new ResultAttributtes("class");
        classAttributte.addResultAttributes("name", "code");
//attributes can be more complex
        ResultAttributtes schoolAttributte = new ResultAttributtes("school");
        schoolAttributte.addResultAttributes("name");
//class add school attribute
        classAttributte.addResultAttributes(schoolAttributte);

        query.addResultAttributes(classAttributte, schoolAttributte);

//        System.out.println(query.toString());
        Assert.assertEquals(result,query.toString());
    }

    @Test
    public void legacyMutation() {
        final String result = "{\"query\":\"mutation{updateUser{name sex age}}\"}";
        GraphqlMutation mutation = new DefaultGraphqlMutation("updateUser");
        mutation.addResultAttributes("name", "sex", "age");
        Assert.assertEquals(result,mutation.toString());
    }

    @Test
    public void legacyMutationII() {
        final  String result = "{\"query\":\"mutation{updateUser(name:\\\"123\\\",id:1,age:18){}}\"}";
//create mutaion
        GraphqlMutation mutation = new DefaultGraphqlMutation("updateUser");
//create param
        mutation.addParameter("id",1).addParameter("name","123").addParameter("age",18);
//add more complex attribute to see do query demo
//        System.out.println(mutation);
        Assert.assertEquals(result,mutation.toString());

    }

    @Test
    public void query() {
        final String result = "{ \"query\": \"query{findByFields{found page results{ street zipCode district number state city } location{ lat lng }}}\"}";
        Graphql graphql = new Graphql("findByFields");
        graphql.addResultAttributes("found", "page");

        ResultAttributtes results = new ResultAttributtes("results");
        results.addResultAttributes("street", "zipCode", "district", "number", "state", "city");

        ResultAttributtes location = new ResultAttributtes("location");
        location.addResultAttributes("lat", "lng");

        graphql.addResultAttributes(results, location);
        System.out.println(graphql);
        Assert.assertEquals(result,graphql.toString());
    }

    @Test
    public void queryVariables() {
        final String result = "{ \"query\": \"query($address: AddressQueryParameter!){address{findByFields(address: $address){found page results{ street zipCode district number state city location{ lat lng } }}}}\",\"variables\":{\"address\":{\"zipCode\":\"05103060\",\"number\":123}}}";
        Graphql graphql = new Graphql("findByFields(address: $address)", "$address: AddressQueryParameter!");
        graphql.setGroup("address");
        graphql.addResultAttributes("found", "page");

        ResultAttributtes results = new ResultAttributtes("results");
        results.addResultAttributes("street", "zipCode", "district", "number", "state", "city");

        ResultAttributtes location = new ResultAttributtes("location");
        location.addResultAttributes("lat", "lng");

        results.addResultAttributes(location);
        graphql.addResultAttributes(results);

        RequestVariable variable = new RequestVariable("address");
        variable
                .addParameter("zipCode","05103060")
                .addParameter("number", 123);

        graphql.setVariable(variable);
        System.out.println(graphql);
        Assert.assertEquals(result,graphql.toString());
    }
}
