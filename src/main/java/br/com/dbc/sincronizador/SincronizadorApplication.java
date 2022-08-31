package br.com.dbc.sincronizador;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.com.dbc.sincronizador.controllers.SincronizacaoReceita;

@SpringBootApplication
public class SincronizadorApplication implements CommandLineRunner{

	public static void main(String[] args) {
		new SpringApplicationBuilder(SincronizadorApplication.class).headless(false).run(args);
	}

	@Override
    public void run(String... args) {
       SincronizacaoReceita sincronizador = new SincronizacaoReceita();
	   sincronizador.sincronizar(args);
    }

}
