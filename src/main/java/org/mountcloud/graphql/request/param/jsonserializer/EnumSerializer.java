package org.mountcloud.graphql.request.param.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * TODO:
 * org.mountcloud.graphql.util.serializer
 * 2019/5/29.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class EnumSerializer extends JsonSerializer<Enum> {
    @Override
    public void serialize(Enum anEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(anEnum.name());
    }
}
