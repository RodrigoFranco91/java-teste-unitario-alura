package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {

    private Funcionario funcionario;
    private BonusService service;

    @BeforeEach
    public void inicializar(){
         funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("25000"));
         service = new BonusService();
    }


    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAltoComAssertThrows(){

        //O primeiro argumento do método é o tipo de Exception esperado, e o segundo argumento é a chamada
        // do metodo usando lambda!
        Assertions.assertThrows( IllegalArgumentException.class, () -> service.calcularBonus(funcionario));

    }

    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAltoComTryCatch(){

        try{
            service.calcularBonus(funcionario);
            fail("Não deu a Exception que eu esperava!");
            //Se chegar aqui é porque o teste falhou, como eu espero um exception após chamar o método tem que ir pro catch
        }catch (IllegalArgumentException e){
            //Comparando se a mensagem de exception é a esperada!
            Assertions.assertEquals("Funcionário com salário maior do que R$1000.00 não pode receber bonus.", e.getMessage());
        }
    }

    @Test
    void bonusDeveSer10PorCentoDoSalario(){
        BigDecimal bonus = service.calcularBonus(funcionario);
        Assertions.assertEquals(new BigDecimal("250.00"), bonus);
    }

    @Test
    void bonusDeveSer10PorCentoDoParaSalarioDeExatamente10000(){
        BigDecimal bonus = service.calcularBonus(funcionario);
        Assertions.assertEquals(new BigDecimal("1000.00"), bonus);
    }

}