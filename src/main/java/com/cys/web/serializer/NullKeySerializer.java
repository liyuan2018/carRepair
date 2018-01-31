package com.cys.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * jackson的null key序列化器
 * Created by liyuan on 2018/1/31.
 */
public class NullKeySerializer extends StdSerializer<Object> {

    public NullKeySerializer() {
        this(null);
    }

    public NullKeySerializer(Class<Object> obj) {
        super(obj);
    }

    @Override
    public void serialize(Object nullKey, JsonGenerator jsonGenerator, SerializerProvider unused)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeFieldName("");
    }
}
