package br.com.dbc.sincronizador.models;

/**
 * Modelagem do Objeto Conta
 * 
 * @author Anderson Florêncio
 */
public class Conta {
    String agencia;
    String conta;
    Double saldo;
    String status;
    String resultado;

    public String getAgencia() {
        return agencia;
    }
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    public String getConta() {
        return conta;
    }
    public void setConta(String conta) {
        this.conta = conta;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
        result = prime * result + ((conta == null) ? 0 : conta.hashCode());
        result = prime * result + ((resultado == null) ? 0 : resultado.hashCode());
        result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Conta other = (Conta) obj;
        if (agencia == null) {
            if (other.agencia != null)
                return false;
        } else if (!agencia.equals(other.agencia))
            return false;
        if (conta == null) {
            if (other.conta != null)
                return false;
        } else if (!conta.equals(other.conta))
            return false;
        if (resultado == null) {
            if (other.resultado != null)
                return false;
        } else if (!resultado.equals(other.resultado))
            return false;
        if (saldo == null) {
            if (other.saldo != null)
                return false;
        } else if (!saldo.equals(other.saldo))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    
    
    
}
