package br.com.fernando.refl;

import br.com.fernando.Pessoa;
import br.com.fernando.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest {

    Pessoa pessoa = new Pessoa(1, "João", "1234");

    @Test
    public void shouldTransform() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Transformator transformator = new Transformator();
        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO); // valida se realmente é uma pessoaDTO
        Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf());
        System.out.println(pessoaDTO.getNome());
    }

}
