package br.com.dbc.sincronizador.unittests.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.com.dbc.sincronizador.controllers.SincronizacaoReceita;
import br.com.dbc.sincronizador.models.Conta;
import br.com.dbc.sincronizador.unittests.mocks.MockConta;
import br.com.dbc.sincronizador.utils.MensagemUtil;
import br.com.dbc.sincronizador.unittests.utils.SincronizacaoUtilTest;

/**
 * Classe responsável por realizar os testes unitários ao empacotar a aplicação
 * @author afbatista
 *
 */
public class SincronizacaoReceitaTest {
	
	SincronizacaoReceita sincronizador;
	MockConta mockConta;
	SincronizacaoUtilTest util;
	
	/**
	 * Antes de realizar os testes erão criados os arquivos fisicos mockados no diretoria da aplicação para a realização dos testes
	 * @throws Exception
	 */
	@BeforeEach
	void setupTests() throws Exception {
		sincronizador = new SincronizacaoReceita();
		mockConta = new MockConta();
		util = new SincronizacaoUtilTest();
		util.criarArquivo(mockConta.mockListEntity(), false);
		util.criarArquivo(mockConta.mockListEntity(), true);
	}

	/**
	 * Testa o caso de sucesso, passando um arquivo válido e com dados válidos como argumento
	 */
	@Test
	@Order(1)
	void sincronizarSucesso() {
		System.out.println("########## - INICIO TEST SINCRONIZAR SUCESSO - ##########");
		String retorno = sincronizador.sincronizar("arquivo_test_sucesso.csv", "resultado_test.csv");
		assertEquals(MensagemUtil.SUCESSO, retorno);
	}
	
	/**
	 * Teste a validação de passagem de um arquivo válido (csv) e existente como parametro
	 */
	@Test
	@Order(2)
	void sincronizarArquivoInvalido() {
		System.out.println("########## - INICIO TEST SINCRONIZAR ARQUIVO INVALIDO - ##########");
		String retorno = sincronizador.sincronizar("arquivo_test_sucesso.pdf", "resultado_test.csv");
		assertEquals(MensagemUtil.ERRO_INFORME_ARQUIVO_VALIDO, retorno);
	}
	
	/**
	 * Testa os dados retornados como Não sincronizados, passano dados invalidos para o serviço
	 * @throws Exception
	 */
	@Test
	@Order(3)
	void sincronizarArquivoDadosInvalidos() throws Exception {
		System.out.println("########## - INICIO TEST SINCRONIZAR ARQUIVO DADOS INVALIDOS - ##########");
		sincronizador.sincronizar("arquivo_test_erro.csv", "resultado_test.csv");
		List<Conta> contas = util.popularContas("resultado_test.csv");
		assertEquals(MensagemUtil.NAO_SINCRONIZADO, contas.get(3).getResultado());
	}
	
	/**
	 * Ao final de todos os testes, exclui os arquivos criados temporariamente
	 * @throws IOException
	 */
	@AfterAll
    public static void removerArquivosGerados() throws IOException {
		Path erro = Paths.get("arquivo_test_erro.csv");
		Files.delete(erro);
		Path sucesso = Paths.get("arquivo_test_sucesso.csv");
		Files.delete(sucesso);
		Path resultado = Paths.get("resultado_test.csv");
		Files.delete(resultado);
    }
	
	
}
