package br.com.dbc.sincronizador.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.text.DecimalFormat;

import br.com.dbc.sincronizador.models.Conta;

/**
 * Classe contendo métodos que auxiliam nas operacções de sincronização do serviço
 * 
 * @author Anderson Florêncio
 */
public class SincronizacaoUtil {

    /**
     * Retorna uma coleção de Contas contendo os dados passados pelo arquivo CSV dos argumentos
     * @param fileReader
     * @return
     * @throws Exception
     */
    public Collection<Conta> popularContas(FileReader fileReader) throws Exception {
        BufferedReader br = null;
        String linha = "";
        String divisor = ";";
        Collection<Conta> contas = new ArrayList<>();

        try {

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

                    contas.add(conta);
                }
                index++;
            }
            fileReader.close();
            br.close();

        } catch (Exception e) {
            throw new Exception();
        }

        return contas;
    }

    /**
     * Gera um arquivo CSV no diretório atual com o resultado da sincronização com o serviço
     * @param contas
     * @throws Exception
     */
    public void gerarResultado(Collection<Conta> contas, String nomeArquivo) throws Exception {
        try (PrintWriter writer = new PrintWriter(new File(nomeArquivo))) {

            StringBuilder linha = new StringBuilder();

            linha.append("agencia;conta;saldo;status;resultado\n");

            contas.forEach(conta -> {
                linha.append(conta.getAgencia() + ";");
                linha.append(formatarStrContaComDigito(conta.getConta()) + ";");
                linha.append(formatarStrDouble(conta.getSaldo()) + ";");
                linha.append(conta.getStatus() + ";");
                linha.append(conta.getResultado());
                linha.append("\n");
            });

            writer.write(linha.toString());
            writer.close();

        } catch (Exception e) {
            throw new Exception();
        }
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
