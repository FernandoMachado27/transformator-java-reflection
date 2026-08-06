package br.com.fernando.refl;

import br.com.fernando.Endereco;
import br.com.fernando.Pessoa;
import br.com.fernando.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest {

    Pessoa pessoa = new Pessoa(1, "João", "1234");
    Endereco endereco = new Endereco("Rua 10", 10);

    @Test
    public void shouldTransform() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Transformator transformator = new Transformator();
        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO); // valida se realmente é uma pessoaDTO
        Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf());
        System.out.println(pessoaDTO.getNome());
    }

    @Test
    public void shouldNotTransform() {
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            Transformator transformator = new Transformator();
            transformator.transform(endereco);
        });
    }

    @Test
    public void shouldTransformWhenSomeFieldsNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pessoa pessoaSemCPF = new Pessoa("João");
        Transformator transformator = new Transformator();
        PessoaDTO pessoaSTOSemCPF = transformator.transform(pessoaSemCPF);

        Assertions.assertEquals(pessoa.getNome(), pessoaSTOSemCPF.getNome());
        Assertions.assertNull(pessoaSTOSemCPF.getCpf());
    }

}
