## Sobre a API

A API utilizada neste projeto faz parte de um curso do **Júlio de Lima** voltado para o aprendizado de testes de API. O foco deste repositório é a criação de **testes automatizados** para essa API, utilizando ferramentas como **JUnit** e **RestAssured**. Não fui responsável pelo desenvolvimento da API; minha contribuição está na elaboração dos testes para cobrir diferentes cenários de uso e validação de funcionalidades.

Para mais informações sobre o curso e a API, visite o canal do youtube do Júlio de Lima.

# Gerenciador de Viagens - Testes Automatizados de API

Este projeto contém uma suíte de testes automatizados para a API do sistema de **Gerenciamento de Viagens**, desenvolvido como parte do meu aprendizado para me tornar um QA Engineer. Os testes utilizam **JUnit** e **RestAssured** para verificar diferentes funcionalidades da API, incluindo o cadastro, listagem, atualização e exclusão de viagens.

## Tecnologias Utilizadas

- **Java**
- **JUnit**: Framework de testes unitários para Java.
- **RestAssured**: Biblioteca Java para testes de API RESTful.
- **Maven**: Gerenciador de dependências e build.

## Pré-requisitos

Certifique-se de ter o seguinte instalado em seu ambiente:

- **Java JDK 8+**
- **Maven**
- **IntelliJ IDEA** ou qualquer outra IDE de sua escolha

## Funcionalidades Testadas

A suíte de testes cobre os seguintes cenários:

### Cadastro de Viagem

- **Como Administrador**: Com sucesso (`201 Created`).
- **Como Usuário Comum**: Falha devido a permissões insuficientes (`403 Forbidden`).
- **Sem um Token Válido**: Falha (`403 Forbidden`).

### Listagem de Viagens

- **Listagem como Usuário Comum**: Sucesso (`200 OK`).
- **Tentativa de Listagem como Administrador Sem Permissão**: Falha (`403 Forbidden`).

### Atualização de Viagem

- **Atualização de uma Viagem Existente pelo ID como Administrador**: Sucesso (`204 No Content`).

### Exclusão de Viagem

- **Exclusão de uma Viagem Existente pelo ID como Administrador**: Sucesso (`204 No Content`).
- **Tentativa de Exclusão de uma Viagem Inexistente**: Resultado em erro do servidor (`500 Internal Server Error`).

## Notas Importantes

- A API está configurada para rodar localmente em `http://localhost:8089/api`. Certifique-se de que o servidor da API esteja em execução antes de iniciar os testes.
- Tokens de autenticação são gerados pela classe `TokenViagem.java`. Verifique se os tokens são válidos e atualizados para os testes.
