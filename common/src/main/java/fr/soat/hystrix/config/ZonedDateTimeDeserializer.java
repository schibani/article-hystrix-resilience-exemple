package fr.soat.hystrix.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.base.Strings;

import java.io.IOException;
import java.time.ZonedDateTime;


public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctx)
            throws IOException {
        if (Strings.isNullOrEmpty(jp.getText())) {
            return null;
        }
        return ZonedDateTime.parse(jp.getText());
    }

}
