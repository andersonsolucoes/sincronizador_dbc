package br.com.dbc.sincronizador.unittests.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.dbc.sincronizador.models.Conta;

/**
 * Classe que simula a criação do objeto Conta com dados fictícios (Mock)
 * @author afbatista
 *
 */
public class MockConta {
	
	public Conta mockEntity(Integer number) {
		Conta conta = new Conta();
		conta.setAgencia("777" + number);
		conta.setConta("77777" + number);
		conta.setSaldo(77.77);
		conta.setStatus(number%2 == 0 ? "A" : "P");
		return conta;
	}
	
	public List<Conta> mockListEntity() {
		List<Conta> contas = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			contas.add(mockEntity(i));
		}
		return contas;
	}
}
