# Graphql Client
The project is graphql client for java,support custom query and mutation.

The current version only supports post requests

You need java1.8 and maven.

Welcome to my home page:[Mount Cloud](http://www.mountcloud.org)

# Use You Project

maven:

	<dependency>
		    <groupId>org.mountcloud</groupId>
		    <artifactId>graphql-client</artifactId>
		    <version>1.1</version>
	</dependency>

# Insall Project

	mvn install


# Demo 

do query

```Java
//crate client
GraphqlClient client = GraphqlClient.buildGraphqlClient("http://localhost:8081/graphql");
//create http headers
Map<String,String> headers = new HashMap<String,String>();
headers.put("token","123");
client.setHttpHeaders(headers);
//create query
GraphqlQuery query = new DefaultGraphqlQuery("findUsers");
//add query or mutation param
query.addParameter("sex","man").addParameter("age",11);
//add query response basics attribute
query.addResultAttributes("id","name","age","sex");
//add query complex attributes
ResultAttributtes classAttributte = new ResultAttributtes("class");
classAttributte.addResultAttributes("name","code");
//attributes can be more complex
ResultAttributtes schoolAttributte = new ResultAttributtes("school");
schoolAttributte.addResultAttributes("name");
//class add school attribute
classAttributte.addResultAttributes(schoolAttributte);
//do query
try {
    GraphqlResponse response = client.doQuery(query);

    //this map is graphql result
    Map data = response.getData();

} catch (IOException e) {
    e.printStackTrace();
}
```

do mutation

```Java
//crate client
GraphqlClient client = GraphqlClient.buildGraphqlClient("http://localhost:8081/graphql");
//create http headers
Map<String,String> headers = new HashMap<String,String>();
headers.put("token","123");
client.setHttpHeaders(headers);
//create mutaion
GraphqlMutation mutation = new DefaultGraphqlMutation("updateUser");
//create param
mutation.addParameter("id",1).addParameter("name","123").addParameter("age",18);
//add more complex attribute to see do query demo
try {
    GraphqlResponse response = client.doMutation(mutation);
    //this map is graphql result
    Map data = response.getData();
} catch (IOException e) {
    e.printStackTrace();
}
```
