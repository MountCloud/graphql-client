package org.mountcloud.graphql.request.param;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.mountcloud.graphql.request.param.jsonserializer.EnumSerializer;

/**
 * 2019/5/29. 复杂类型请求的参数，例如
 * demo：


 mutation{
     addUsers(classId:"123",users:[{name:"123",age:1}],covers:[{name:"321",age:2}]){
         code
     }
 }

 *
 * @author zhanghaishan
 * @version V1.0
 */
public class RequestObjectParameter {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Object data;

    /**
     * 构造参数
     * @param data 数据
     */
    public RequestObjectParameter(Object data) {

        //初始化objectMapper
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //属性名去掉双引号
        objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        //返回enum名字，去掉双引号:   type:"JPG" is  type:JPG
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Enum.class,new EnumSerializer());
        objectMapper.registerModule(simpleModule);

        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String json = dataToJson();
        json = json.replaceAll("\\\"","\\\\\"");
        return json;
    }

    /**
     * data转string
     * @return data转string
     */
    private String dataToJson(){
        String str = "null";
        try {
            str = objectMapper.writeValueAsString(this.getData());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }

}
