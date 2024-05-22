package com.lnrs.response;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.lnrs.mapper.CompanyList;

import java.io.IOException;

public class ResponseSerializer extends JsonSerializer<CompanyList> {
    @Override
    public void serialize(CompanyList myResponse, com.fasterxml.jackson.core.JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("items");
        serializerProvider.defaultSerializeValue(myResponse.getItems(), jsonGenerator);

        jsonGenerator.writeFieldName(" total_results");
        serializerProvider.defaultSerializeValue(myResponse.getTotalResults(), jsonGenerator);

        jsonGenerator.writeEndObject();
    }
}
