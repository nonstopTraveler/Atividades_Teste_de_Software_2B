# Semana 11 — Testes de integração com Spring Boot e JUnit 5

Projeto-base da lista prática concluído com os testes de integração solicitados.

**Tecnologias:** Java 17, Maven, Spring Boot 3.3.13, Spring Data JPA, H2 em memória, JUnit 5 e MockMvc.

**Importante:** os testes não utilizam mocks para substituir `ProdutoService` ou `ProdutoRepository`. O fluxo executa os componentes reais da aplicação e persiste os dados no H2.

## Como executar

Na pasta que contém o `pom.xml`, execute:

```bash
mvn test
```

## Exercício 1 — Service + Repository + H2

Arquivo: `src/test/java/br/edu/exemplo/ProdutoServiceIntegrationTest.java`

| Caso | Teste | Verificação |
|---|---|---|
| CT01 | `deveCadastrarERecuperarProduto` | Cadastra Teclado por 150.0, confirma que recebeu ID e recupera o registro pelo repositório, conferindo nome e preço. |
| CT02 | `naoDevePersistirProdutoComNomeEmBranco` | Tenta cadastrar nome em branco, verifica `IllegalArgumentException` e confirma que a quantidade de registros não aumentou. |
| CT03 | `deveAtualizarPrecoPersistido` | Cadastra Mouse por 80.0, atualiza para 95.0 e consulta novamente o banco para conferir o novo preço. |

Os testes usam `@SpringBootTest`, `@Autowired` e `@Transactional`, conforme solicitado. No CT01 e CT03 também usam `EntityManager.flush()` e `EntityManager.clear()` antes da nova consulta. O `flush()` envia as alterações pendentes ao banco; o `clear()` limpa o contexto de persistência para evitar que a consulta retorne apenas a entidade que já estava no cache JPA.

## Exercício 2 — Controller + Service + Repository + H2 com MockMvc

Arquivo: `src/test/java/br/edu/exemplo/ProdutoControllerIntegrationTest.java`

| Caso | Teste | Verificação |
|---|---|---|
| CT04 | `deveCadastrarProdutoPelaApi` | Envia POST `/produtos` com Mouse e preço 80.0; verifica HTTP 200, ID numérico, nome e preço no JSON. |
| CT05 | `deveBuscarProdutoPelaApi` | Cadastra pelo POST, extrai o ID da resposta JSON com `ObjectMapper`/`JsonNode`, consulta pelo GET `/produtos/{id}` e confere os dados. |

O MockMvc permite exercitar o fluxo Spring MVC sem iniciar um servidor HTTP real. A requisição é processada dentro da aplicação, passando pelo Controller, Service, Repository e H2.

## Discussão

### Por que consultar `repository.findById()` é diferente de verificar apenas o objeto retornado pelo serviço?

O objeto retornado pelo serviço pode apenas refletir o objeto Java criado durante a operação. Consultar o repositório permite verificar se o registro pode ser recuperado pela camada de persistência. Nos testes CT01 e CT03, `flush()` e `clear()` ajudam a garantir que a consulta não use somente a entidade mantida no contexto de persistência em memória.

### O que `@Transactional` faz ao término de cada teste?

O Spring Test inicia uma transação para cada método de teste e, normalmente, executa rollback ao final. Assim, as alterações feitas durante um teste são desfeitas e não contaminam os demais testes. Isso ajuda a manter o isolamento entre os casos.

### Quais componentes são exercitados no Exercício 2?

São exercitados o roteamento do Spring MVC, a conversão do JSON, `ProdutoController`, `ProdutoService`, `ProdutoRepository`, o mecanismo JPA/Hibernate e o banco H2. A resposta também é convertida novamente para JSON.

### Por que o MockMvc não precisa de um servidor HTTP real?

O MockMvc executa o fluxo do Spring MVC dentro da mesma JVM, usando requisições e respostas de teste. Ele não precisa abrir uma porta nem iniciar um servidor externo, mas ainda permite testar o processamento da requisição pela aplicação.

### Qual a diferença para um teste unitário do Controller com dependências simuladas?

Em um teste unitário com mocks, o Service ou o Repository pode ser substituído por objetos simulados. Isso permite testar uma classe isoladamente, mas não comprova que as camadas funcionam juntas. Aqui, os componentes principais são reais, então o teste verifica também as regras do Service e a persistência no H2.

## Resultado da execução

Registre aqui o resultado obtido no seu computador após executar `mvn test`. Não considere testes vazios como evidência de funcionamento: cada caso precisa executar suas asserções.

