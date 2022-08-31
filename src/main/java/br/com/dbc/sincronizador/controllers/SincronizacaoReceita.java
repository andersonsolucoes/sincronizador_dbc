/*
Cenário de Negócio:
Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e organiza as informações de 
contas para enviar ao Banco Central. Todas agencias e cooperativas enviam arquivos Excel à Retaguarda. Hoje o Sicredi 
já possiu mais de 4 milhões de contas ativas.
Esse usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita Federal, 
antes as 10:00 da manhã na abertura das agências.

Requisito:
Usar o "serviço da receita" (fake) para processamento automático do arquivo.

Funcionalidade:
0. Criar uma aplicação SprintBoot standalone. Exemplo: java -jar SincronizacaoReceita <input-file>
1. Processa um arquivo CSV de entrada com o formato abaixo.
2. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
3. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando o resultado em uma 
nova coluna.


Formato CSV:
agencia;conta;saldo;status
0101;12225-6;100,00;A
0101;12226-8;3200,50;A
3202;40011-1;-35,12;I
3202;54001-2;0,00;P
3202;00321-2;34500,00;B
...

*/
package br.com.dbc.sincronizador.controllers;

import java.io.FileReader;
import java.util.Collection;

import br.com.dbc.sincronizador.models.Conta;
import br.com.dbc.sincronizador.services.ReceitaService;
import br.com.dbc.sincronizador.utils.SincronizacaoUtil;
import br.com.dbc.sincronizador.validators.Validator;

/**
 * Classe responsável por realizar a sincronização dos dados informados com os serviços da Receita
 * 
 * @author Anderson Florêncio
 */
public class SincronizacaoReceita {

    public SincronizacaoReceita() {
    }

    /**
     * Método responsável por realizar a chamada para sincronização dos dados do arquivo informado com o serviço da Receita
     * @param args
     */
    public void sincronizar(String[] args) {
        Validator validator = new Validator();
        SincronizacaoUtil util = new SincronizacaoUtil();
        if (validator.validarArgs(args)) {

            try {
                Collection<Conta> contas = util.popularContas(new FileReader(args[0]));
                realizarSincronizacao(contas);
                util.gerarResultado(contas);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getCause());
            }

        } else {
            System.out.println("Informe um arquivo com extensão CSV válido para importação!");
        }
    }

    /**
     * Método que consome o serviço de sincronização da Receita
     * @param contas
     * @return
     */
    private Collection<Conta> realizarSincronizacao(Collection<Conta> contas) {
        contas.forEach(conta -> {
            ReceitaService service = new ReceitaService();
            try {
                conta.setResultado(service.atualizarConta(conta.getAgencia(), conta.getConta(), conta.getSaldo(),
                        conta.getStatus()) ? "Sincronizado" : "Não Sincronizado");
            } catch (RuntimeException | InterruptedException e) {
                conta.setResultado("Erro no serviço");
            }
        });
        return contas;
    }
}
