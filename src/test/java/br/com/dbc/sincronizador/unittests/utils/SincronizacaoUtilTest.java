package br.com.dbc.sincronizador.unittests.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.dbc.sincronizador.models.Conta;

/**
 * Classe de utilitários para os testes realizados
 * @author afbatista
 *
 */
public class SincronizacaoUtilTest {
	
	/**
     * Retorna uma coleção de Contas contendo os dados passados pelo arquivo CSV dos argumentos
     * @param fileReader
     * @return
	 * @throws Exception 
     * @throws Exception
     */
    public List<Conta> popularContas(String arquivo) throws Exception {
        BufferedReader br = null;
        String linha = "";
        String divisor = ";";
        List<Conta> contas = new ArrayList<>();

    	FileReader fileReader = new FileReader(arquivo);
        br = new BufferedReader(fileReader);
        int index = 0;

        while ((linha = br.readLine()) != null) {
            if (index > 0) {
                String[] linhaConta = linha.split(divisor);
                Conta conta = new Conta();
                conta.setAgencia(linhaConta[0]);
                conta.setConta(linhaConta[1].replace("-", ""));
                conta.setSaldo(Double.valueOf(linhaConta[2].replace(",", ".")));
                conta.setStatus(linhaConta[3]);
                conta.setResultado(linhaConta[4]);

                contas.add(conta);
            }
            index++;
        }
        fileReader.close();
        br.close();
        return contas;
    }
	
	/**
	 * Gera um arquivo temporário com dados mockados para teste
	 * @param contas
	 * @throws Exception 
	 */
	public void criarArquivo(Collection<Conta> contas, boolean induzirAoErro) throws Exception {
        PrintWriter writer = new PrintWriter(new File("arquivo_test" + (induzirAoErro ? "_erro" : "_sucesso") + ".csv"));

            StringBuilder linha = new StringBuilder();

            linha.append("agencia;conta;saldo;status\n");

            contas.forEach(conta -> {
                linha.append(conta.getAgencia() + ";");
                linha.append(formatarStrContaComDigito(conta.getConta()) + (induzirAoErro ? "000" : "") + ";");
                linha.append(formatarStrDouble(conta.getSaldo()) + ";");
                linha.append(conta.getStatus());
                linha.append("\n");
            });

            writer.write(linha.toString());
            writer.close();
    }
	
	/**
     * Retorna uma string formata 000000,00
     * @param numero
     * @return
     */
    private String formatarStrDouble(Double numero) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(numero);
    }

    /**
     * Retorna uma string da conta formatada com o hífen identificando o dígito
     * @param conta
     * @return
     */
    private String formatarStrContaComDigito(String conta) {
        String contaAux = conta.substring(0, 5);
        String contaDigito = conta.substring(5);
        return contaAux + "-" + contaDigito;
    }
}
