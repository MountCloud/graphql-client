# English

## Graphql Client

The project is graphql client for java,support custom query and mutation.

The current version only supports post requests

You need java1.8 and maven.

Welcome to my home page:[Mount Cloud](http://www.mountcloud.org)

## Update 1.2 Note

Requested parameters support custom complex types and Enum types.

## Use You Project

maven:

    <dependency>
    	    <groupId>org.mountcloud</groupId>
    	    <artifactId>graphql-client</artifactId>
    	    <version>1.2</version>
    </dependency>

## Insall Project

    mvn install

## Demo

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

query is

```Java
query{
  findUsers(sex:"man",age:11){
    id
    name
    age
    sex
    class{
    	name
	code
	school{
	  name
	}
    }
  }
}
```

do multiple query

```Java
//crate client
GraphqlClient client = GraphqlClient.buildGraphqlClient("http://localhost:8081/graphql");
//create http headers
Map<String,String> headers = new HashMap<String,String>();
headers.put("token","123");
client.setHttpHeaders(headers);
//create query
SubGraphqlQuery subQuery1 = new DefaultSubGraphqlQuery("findUsers1:findUsers");
//add query or mutation param
subQuery1.addParameter("sex","man").addParameter("age",11);
//add query response basics attribute
subQuery1.addResultAttributes("id","name","age","sex");
//add query complex attributes
ResultAttributtes classAttributte1 = new ResultAttributtes("class");
classAttributte1.addResultAttributes("name","code");
//attributes can be more complex
ResultAttributtes schoolAttributte1 = new ResultAttributtes("school");
schoolAttributte1.addResultAttributes("name");
//class add school attribute
classAttributte1.addResultAttributes(schoolAttributte1);

SubGraphqlQuery subQuery2 = new DefaultSubGraphqlQuery("findUsers2:findUsers");
//add query or mutation param
subQuery2.addParameter("sex","woman").addParameter("age",12);
//add query response basics attribute
subQuery2.addResultAttributes("id","name","age","sex");
//add query complex attributes
ResultAttributtes classAttributte2 = new ResultAttributtes("class");
classAttributte2.addResultAttributes("name","code");
//attributes can be more complex
ResultAttributtes schoolAttributte2 = new ResultAttributtes("school");
schoolAttributte2.addResultAttributes("name");
//class add school attribute
classAttributte2.addResultAttributes(schoolAttributte2);
//do query
try {

    MultiGraphqlQuery query = new DefaultMultiGraphqlQuery();
    // add first sub query
    query.put(subQuery1);
    // add second sub query
    query.put(subQuery2);
    GraphqlResponse response = client.doQuery(query);

    //this map is graphql result
    Map data = response.getData();

} catch (IOException e) {
    e.printStackTrace();
}
```

query is

```Java
query{
  findUsers1:findUsers(sex:"man",age:11){
    id
    name
    age
    sex
    class{
    	name
	code
	school{
	  name
	}
    }
  }
  findUsers2:findUsers(sex:"woman",age:12){
    id
    name
    age
    sex
    class{
    	name
	code
	school{
	  name
	}
    }
  }
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

//result
mutation.addResultAttributes("code");
try {
    GraphqlResponse response = client.doMutation(mutation);
    //this map is graphql result
    Map data = response.getData();
} catch (IOException e) {
    e.printStackTrace();
}
```

mutation is

```Java
mutation{
  updateUser(id:1,name:"123",age:18){
    code
  }
}
```

## Complex structure request demo

Mutation demo,The query is consistent with the mutation.

```Java
    @Test
    public void testObjectParameter() throws IOException {



        String serverUrl = "http://localhost:8080/graphql";

        GraphqlClient graphqlClient = GraphqlClient.buildGraphqlClient(serverUrl);


        Map<String,String> httpHeaders = new HashMap<>();
        httpHeaders.put("token","graphqltesttoken");

        graphqlClient.setHttpHeaders(httpHeaders);

        GraphqlMutation mutation = new DefaultGraphqlMutation("addUser");

        List<User> users = new ArrayList<>();
        users.add(new User("tim",SexEnum.M));
        users.add(new User("sdf",SexEnum.F));
        mutation.getRequestParameter().addParameter("classId","123").addObjectParameter("users",users);

        mutation.addResultAttributes("code");

        System.out.println(mutation.toString());

    }

    /**
     * test user
     */
    class User{
        public User(String name, SexEnum sexEnum) {
            this.name = name;
            this.sexEnum = sexEnum;
        }

        private String name;
        private SexEnum sexEnum;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SexEnum getSexEnum() {
            return sexEnum;
        }

        public void setSexEnum(SexEnum sexEnum) {
            this.sexEnum = sexEnum;
        }
    }

    /**
     * test user sex
     */
    enum SexEnum{
        M,
        F
    }
```

mutation is

```Java
mutation{
  addUser(classId:"123",users:[{name:"tim",sexEnum:M},{name:"sdf",sexEnum:F}]){
    code
  }
}
```

# 中文

## Graphql Client

该项目是 java 的 graphql 客户端，支持自定义 query 和 mutation.

当前版本仅支持 post 请求

你需要 java1.8 和 maven.

欢迎观临我的主页:[Mount Cloud](http://www.mountcloud.org)

## 更新 1.2 日志

请求的参数支持自定义复杂类型和 Enum 类型。

## 使用方式

maven:

    <dependency>
    	    <groupId>org.mountcloud</groupId>
    	    <artifactId>graphql-client</artifactId>
    	    <version>1.2</version>
    </dependency>

## Insall Project

    mvn install

## Demo

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

query is

```Java
query{
  findUsers(sex:"man",age:11){
    id
    name
    age
    sex
    class{
    	name
	code
	school{
	  name
	}
    }
  }
}
```

do multiple query

```Java
//crate client
GraphqlClient client = GraphqlClient.buildGraphqlClient("http://localhost:8081/graphql");
//create http headers
Map<String,String> headers = new HashMap<String,String>();
headers.put("token","123");
client.setHttpHeaders(headers);
//create query
SubGraphqlQuery subQuery1 = new DefaultSubGraphqlQuery("findUsers1:findUsers");
//add query or mutation param
subQuery1.addParameter("sex","man").addParameter("age",11);
//add query response basics attribute
subQuery1.addResultAttributes("id","name","age","sex");
//add query complex attributes
ResultAttributtes classAttributte1 = new ResultAttributtes("class");
classAttributte1.addResultAttributes("name","code");
//attributes can be more complex
ResultAttributtes schoolAttributte1 = new ResultAttributtes("school");
schoolAttributte1.addResultAttributes("name");
//class add school attribute
classAttributte1.addResultAttributes(schoolAttributte1);

SubGraphqlQuery subQuery2 = new DefaultSubGraphqlQuery("findUsers2:findUsers");
//add query or mutation param
subQuery2.addParameter("sex","woman").addParameter("age",12);
//add query response basics attribute
subQuery2.addResultAttributes("id","name","age","sex");
//add query complex attributes
ResultAttributtes classAttributte2 = new ResultAttributtes("class");
classAttributte2.addResultAttributes("name","code");
//attributes can be more complex
ResultAttributtes schoolAttributte2 = new ResultAttributtes("school");
schoolAttributte2.addResultAttributes("name");
//class add school attribute
classAttributte2.addResultAttributes(schoolAttributte2);
//do query
try {

    MultiGraphqlQuery query = new DefaultMultiGraphqlQuery();
    // add first sub query
    query.put(subQuery1);
    // add second sub query
    query.put(subQuery2);
    GraphqlResponse response = client.doQuery(query);

    //this map is graphql result
    Map data = response.getData();

} catch (IOException e) {
    e.printStackTrace();
}
```

query is

```Java
query{
  findUsers1:findUsers(sex:"man",age:11){
    id
    name
    age
    sex
    class{
    	name
	code
	school{
	  name
	}
    }
  }
  findUsers2:findUsers(sex:"woman",age:12){
    id
    name
    age
    sex
    class{
    	name
	code
	school{
	  name
	}
    }
  }
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

//result
mutation.addResultAttributes("code");
try {
    GraphqlResponse response = client.doMutation(mutation);
    //this map is graphql result
    Map data = response.getData();
} catch (IOException e) {
    e.printStackTrace();
}
```

mutation is

```Java
mutation{
  updateUser(id:1,name:"123",age:18){
    code
  }
}
```

## 如何实现一个复杂请求

用 Mutation 做演示,query 与 mutation 原理一样.

```Java
    @Test
    public void testObjectParameter() throws IOException {



        String serverUrl = "http://localhost:8080/graphql";

        GraphqlClient graphqlClient = GraphqlClient.buildGraphqlClient(serverUrl);


        Map<String,String> httpHeaders = new HashMap<>();
        httpHeaders.put("token","graphqltesttoken");

        graphqlClient.setHttpHeaders(httpHeaders);

        GraphqlMutation mutation = new DefaultGraphqlMutation("addUser");

        List<User> users = new ArrayList<>();
        users.add(new User("tim",SexEnum.M));
        users.add(new User("sdf",SexEnum.F));
        mutation.getRequestParameter().addParameter("classId","123").addObjectParameter("users",users);

        mutation.addResultAttributes("code");

        System.out.println(mutation.toString());

    }

    /**
     * test user
     */
    class User{
        public User(String name, SexEnum sexEnum) {
            this.name = name;
            this.sexEnum = sexEnum;
        }

        private String name;
        private SexEnum sexEnum;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public SexEnum getSexEnum() {
            return sexEnum;
        }

        public void setSexEnum(SexEnum sexEnum) {
            this.sexEnum = sexEnum;
        }
    }

    /**
     * test user sex
     */
    enum SexEnum{
        M,
        F
    }
```

mutation is

```Java
mutation{
  addUser(classId:"123",users:[{name:"tim",sexEnum:M},{name:"sdf",sexEnum:F}]){
    code
  }
}
```
