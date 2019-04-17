# Enunciado do Trabalho 2

**Objectivos**: Prática com `Stream<T>`, `Spliterator<T>` e o idioma internal
iteration.
<br>
**Data limite de entrega**: **25 de Abril de 2019**

**NOTA**: Implemente os testes unitários necessários para validar o
funcionamento das funcionalidades pedidas.

No âmbito da biblioteca jingle pretende-se substituir a utilização de
`Iterator<T>` por `Stream<T>`. 
Siga os passos seguintes para a criação do módulo referente a este
trabalho:

1. Copie a pasta `jingle-lazy` para uma nova pasta `jingle`.
2. Remova de `jingle/build.gradle` a linha: `compile project(':jingle-util')`
3. Copie de `jingle-util` para `jingle` o package `org.isel.jingle.util.req`
4. Adicione a `settings.gradle` o novo módulo `jingle`.

## Parte 1

Substitua no módulo `jingle` a utilização de `LazyQueries` e `Iterable` por
`Stream` ou `Supplier<Stream>`.

O novo método `cache()` adaptado para `Supplier<Stream>` deve ser implementado
numa classe `StreamUtils` do módulo `jingle`.

## Parte 2

Pretende-se adicionar à classe `Artist` o método `getTracksRank(String country)`
que retorna uma sequência de instâncias de `TrackRank` com as propriedades:
`name`, `url`, `duration` e `rank`, esta última de tipo `int`. A propriedade
`rank` tem o ranking dessa música (_track_) para o país passado em `country`.
Caso aquela música não conste do ranking do país então `rank` tem o seu valor
por omissão 0 (zero).

Implemente o método `getTracksRank(String country)` seguindo a mesma abordagem
dos métodos `getAlbums` e `getTracks`, isto é com recurso a um método
`getTracksRank(String artistMbId, String country)` de `JingleService` que por
sua vez recorre à classe `LastfmWebApi`. 

Abordagem: 

1. Implemente em `StreamUtils` um método `merge` que junta duas sequências com
   os critérios do exemplo seguinte. Dado `seq1 = {“isel”, “ola”, “dup”,
   “super”, “jingle” }` e `seq2 = {4, 5, 6, 7}` então fazendo o `merge` que
   tenha como critério de junção `(str, nr) -> str.length == nr` e como
   transformação `(str, nr) -> str + nr` resulta numa sequência `{“isel4”,
   “ola0”, “dup0”, “super5”, “jingle6”}`

2. Implemente em `LastfmWebApi` o método `getTopTracks(String country, int
   page)` que obtém uma página do ranking de musicas num país através do método
   _geo.getTopTracks_ da API Restful de Lastfm:  
   https://www.last.fm/api/show/geo.getTopTracks.

3. Implemente em `JingleService` o método `getTopTracks(String country)` que
   retorna o ranking completo de **todas** as músicas num país.

4. Implemente em `JingleService` o método `getTracksRank(String artistMbId,
   String country)` que faz o _merge_ das sequências dadas por
   `getTracks(String artistMbid)` e o **top 100** de um país dado por
   `getTopTracks(String country)`.
   Caso uma música de `artistMbid` não conste no ranking dado por `getTopTracks`
   então essa música fica com o ranking 0. Implemente esta funcionalidade com
   auxilio do método `merge` da alínea 1 ou uma adaptação desse método.

