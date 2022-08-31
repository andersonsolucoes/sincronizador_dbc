# Sincronizador Receita

Esse projeto foi criado para realizar a sincronização dos dados bancários da empresa com o serviço da Receita (fake)

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

### 📋 Pré-requisitos

Ter o Java 8, Maven e Git instalados

### 🔧 Instalação

Para baixar o código basta realizar o clone do projeto:

```
git clone https://github.com/andersonsolucoes/sincronizador_dbc.git
```

após baixar o projeto, deverá realizar o build com o Maven (no mesmo diretório onde se encontra o pom.xml):

```
mvn package
```

prontinho!

## ⚙️ Executando os testes

Para realizar os testes, basta entrar no diretório do projeto e executar o seguinte comando:

```
java -jar .\target\sincronizador-0.0.1-SNAPSHOT.jar <caminho do arquivo CSV para sincronização>
```

Ao finalizar a execução, deverá ser gerado um arquivo "resultado.csv" contendo o resultado do processamento.

## 🛠️ Construído com
* VScode 1.70
* Java 8
* Maven 3.2
* Spring boot 2.7.3
* Git 2.36