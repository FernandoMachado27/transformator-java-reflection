package br.com.fernando.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Transformator {

    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException { // input -> classe que o suário vai passar
        Class<?> source = input.getClass(); // recebe a classe
        Class<?> target = Class.forName(source + "DTO"); // instanciar classe + DTO

        // busca o construtor da classe xDTO
        O targetClass = (O) target.getDeclaredConstructor().newInstance(); // -> "O" pois será o output

        // retorna todos os campos da classe X
        Field[] sourcesFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        // forEach nas 2 classes para comparar os campos
        Arrays.stream(sourcesFields).forEach(sourcesField ->
                Arrays.stream(targetFields).forEach(targetField -> validate(sourcesField, targetField)));

        return targetClass;
    }

    private void validate(Field sourceField, Field targetField) {
        if(sourceField.getName().equals(targetField.getName())
                && sourceField.getType().equals(targetField.getType())) {

        }
    }

}
