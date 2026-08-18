package br.com.fernando.refl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ObjectToJson {

    public String transform(Object object) throws JsonProcessingException {
        String result = null;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // identa no output o formato Json

        Map<String, Object> mapper = new HashMap<>(); // Toda chave vai ser String, valor pode ser qualquer
        Class<?> classToBeTransformed = object.getClass();

        // transformando chave/valor
        Arrays.stream(classToBeTransformed.getDeclaredFields()).toList().forEach(
                field -> {
                    field.setAccessible(true);
                    String key = field.getName();
                    Object value = null;
                    try {
                        value = field.get(object);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    mapper.put(key, value);
                }
        );
        // para retornar String com formato Json
        result = objectMapper.writeValueAsString(mapper);

        return result;
    }
}
