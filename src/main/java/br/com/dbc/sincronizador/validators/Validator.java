package br.com.dbc.sincronizador.validators;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Classe dos validadores dos argumentos
 * 
 * @author Anderson Florêncio
 */
public class Validator {

    /**
     * Verifica se os argumentos foram passados corretamente na chamada da aplicação
     * @param args
     * @return
     */
    public boolean validarArgs(String[] args) {
        try {
            Path path = Paths.get(args[0]);
            boolean exists = Files.exists(path);

            if (exists) {
                String fileName = path.toFile().getName();
                if (fileName.contains(".")) {
                    String ext = Arrays.stream(fileName.split("\\.")).reduce((a, b) -> b).orElse(null);
                    if (ext != null && ext.toLowerCase().equals("csv"))
                        return true;
                    else
                        return false;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
