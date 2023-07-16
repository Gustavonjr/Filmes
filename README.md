# README

Este é um repositório contendo um código Java que gerencia filmes. O código é composto por várias classes relacionadas à manipulação e armazenamento de filmes, bem como uma classe principal que permite interagir com o sistema através de um menu.

## Estrutura do Código

O código está estruturado em vários pacotes e classes. A seguir, descreveremos cada um deles:

### Pacote `Error`

Este pacote contém a classe `FilmeException`, que é uma exceção personalizada utilizada no código para tratar erros relacionados a filmes.

### Pacote `model`

Este pacote contém a classe `Filme`, que representa um filme no sistema. A classe possui atributos como título, diretores, nota, duração, ano, gêneros, número de votos e URL. Além disso, possui métodos getters, setters e overrides de métodos como `toString`, `equals`, `hashCode` e `compareTo`.

### Pacote `repository`

Este pacote contém a interface `FilmeDAO`, que define as operações de acesso a dados relacionadas aos filmes. A interface possui métodos para salvar, atualizar, excluir, buscar todos os filmes, buscar por ID, buscar por título, buscar por critérios de classificação e gerenciamento.

### Pacote `service`

Este pacote contém a implementação da interface `FilmeDAO`, denominada `FilmeDAOImpl`. Esta classe é responsável por realizar as operações de acesso a dados utilizando um banco de dados MySQL. As operações incluem salvar, atualizar, excluir, buscar todos os filmes, buscar por ID, buscar por título, buscar por critérios de classificação e gerenciamento.

### Pacote `test`

Este pacote contém a classe de teste `FilmeDAOTest`, que utiliza a biblioteca JUnit para realizar testes automatizados nas funcionalidades da classe `FilmeDAOImpl`. Os testes incluem a verificação da conexão com o banco de dados, busca de todos os filmes e busca por título.

### Pacote `Utils`

Este pacote contém a classe `utils`, que fornece métodos utilitários para o sistema. Os métodos incluem a validação de entrada, geração de tabela de filmes em uma janela, importação de dados a partir de um arquivo CSV e exibição de um menu interativo para o usuário.

### Classe `FilmeAdmin`

Esta é a classe principal do sistema, responsável por fornecer um menu interativo para o usuário. O menu possui opções para gerenciamento de filmes, listagem de filmes, cadastro de filmes e importação de dados a partir de um arquivo CSV.

## Utilização

Para utilizar o sistema, siga as instruções abaixo:

1. Clone o repositório para o seu ambiente local.
2. Certifique-se de ter o Java Development Kit (JDK) instalado em sua máquina.
3. Importe o projeto em sua IDE Java de preferência.
4. Certifique-se de ter um servidor MySQL em execução localmente na porta padrão (3306) e com um banco de dados chamado "impacta".
5. Verifique e atualize, se necessário, as configurações de conexão com o banco de dados na classe `FilmeDAOImpl`.
6. Execute a classe `FilmeAdmin` para iniciar o sistema.
7. Siga as opções do menu para interagir com o sistema.
