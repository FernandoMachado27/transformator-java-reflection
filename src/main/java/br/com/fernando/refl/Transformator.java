package br.com.fernando.refl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class Transformator {

    public <I, O> O transform(I input) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException { // input -> classe que o suário vai passar
        Class<?> source = input.getClass(); // recebe a classe
        Class<?> target = Class.forName(source.getName() + "DTO"); // instanciar classe + DTO

        // busca o construtor da classe xDTO
        O targetClass = (O) target.getDeclaredConstructor().newInstance(); // -> "O" pois será o output

        // retorna todos os campos da classe X
        Field[] sourcesFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        // forEach nas 2 classes para comparar os campos
        Arrays.stream(sourcesFields).forEach(sourceField ->
                Arrays.stream(targetFields).forEach(targetField -> {

                    if (validate(sourceField, targetField)) {
                        try {
                            sourceField.setAccessible(true); // acesso às infos privadas da classe de origem
                            targetField.setAccessible(true); // acesso às infos privadas da classe de destino

                            // preenche no targetClass o que está salvo no campo do input
                            targetField.set(targetClass, sourceField.get(input));

                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
        );

        return targetClass;
    }

    // metodo de comparação
    private boolean validate(Field sourceField, Field targetField) {
        return sourceField.getName().equals(targetField.getName())
                && sourceField.getType().equals(targetField.getType());
    }
}
