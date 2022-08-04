package by.halkina.authservice.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;

@Converter
@Slf4j
@RequiredArgsConstructor
public class HashMapConverter implements AttributeConverter<Map<String, String>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<String, String> data) {
        String dataJson = null;
        try {
            dataJson = objectMapper.writeValueAsString(data);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }
        return dataJson;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dataJson) {

        Map<String, String> data = null;
        try {
            data = objectMapper.readValue(dataJson, Map.class);
        } catch (final IOException e) {
            log.error("JSON reading error", e);
        }
        return data;
    }

}