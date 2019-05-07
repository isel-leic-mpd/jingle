# Enunciado do Trabalho 3

**Objectivos**: Prática com API assíncrona, `CompletableFuture<T>` e _reactive streams_.
<br>
**Data limite de entrega**: **3 de Junho de 2019**

Implemente os testes unitários necessários para validar o
funcionamento das funcionalidades pedidas.

**NOTA 1**: não poderá criar ou usar explicitamente fios de execução (i.e.
`Thread`), nem por diferimento de tarefas (i.e.
`CompletableFuture.supplyAsync(...)`) nem através de qualquer outro meio.

**NOTA 2**: não poderá bloquear sobre o resultado das computações assíncronas
(i.e. `.join()` ou `.get()`) com excepção aos testes unitários.

## Parte 0

No âmbito da biblioteca jingle pretende-se tornar a sua API assíncrona. Deverá
criar um novo projeto `jingle-async` com base no anterior `jingle` e adaptá-lo de
acordo com os requisitos deste enunciado.

## Parte 1

De modo a que biblioteca `jingle-async` passe a usar IO não-bloqueante, crie uma
nova a interface `AsyncRequest` com um método `getLines()` que tenha API
assíncrona. 
A implementação desta interface para pedidos HTTP GET deve recorrer a uma
biblioteca para realização de pedidos HTTP não bloqueantes, como por exemplo
[AsyncHttpClient](https://github.com/AsyncHttpClient/async-http-client) ou
[java.net.http](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html)
do JDK 11.

Da mesma forma devem ser actualizados os tipos do modelo de domínio e serviço,
para que passem a oferecer uma API assíncrona baseada no tipo
[Observable](http://reactivex.io/RxJava/2.x/javadoc/io/reactivex/Observable.html)
em vez de `Stream` ou arrays.

## Parte 2

Implemente uma aplicação Web usando a tecnologia
[VertX](https://vertx.io/docs/vertx-web/java/) com handlers assíncronos.
A aplicação deve disponibilizar as seguintes páginas:

1.  Listagem de artistas com um determinado nome recebido por _query-string_.
    Cada artista tem 2 links: uma para a listagem dos seus álbuns e outro para a
    listagem das suas músicas.
2.  Listagem de álbuns de um artista. Cada álbum tem um link para a listagem de
    músicas desse álbum.
3.  Listagem de todas as músicas de um artista.
4.  Listagem de músicas de um álbum.

As páginas anteriores são acessíveis através dos seguintes caminhos (paths): 

1.	`/artists?name=...`
2.	`/artists/:id/albums`
3.	`/artists/:id/tracks`
4.	`/albums/:id/tracks`

A aplicação web **nunca poderá bloquear** (não fazer `join()` e nem `get()`) na
obtenção de um resultado. 

As listagens devem ser retornadas no corpo da reposta HTTP em modo chunked
(`response.setChunked(true)`) sendo a user-interface construída de forma
progressiva à medida que a resposta é recebida no browser.
