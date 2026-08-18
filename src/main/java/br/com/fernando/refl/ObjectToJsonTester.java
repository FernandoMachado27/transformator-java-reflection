package br.com.fernando.refl;

import br.com.fernando.Pessoa;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ObjectToJsonTester {

    public static void main(String[] args) throws JsonProcessingException {
        Pessoa pessoa = new Pessoa(1, "Fidalgo", "12345");
        ObjectToJson objectToJson = new ObjectToJson();

        System.out.println("Result: " + objectToJson.transform(pessoa));
    }

}
