# Núcleo do JavaSkewb

Esta pasta documenta a lógica principal do Skewb implementada no pacote Java `br.com.javaskewb.core`, localizado em `src/main/java/br/com/javaskewb/core`. Ela não é um módulo Maven separado: o código continua compilado como parte do projeto.

## Visão geral

O núcleo representa estados do cubo, aplica movimentos, procura sequências de movimentos e organiza casos de métodos de resolução. A interface JavaFX e os controladores ficam fora deste pacote.

## Organização do código

| Pacote | Responsabilidade |
| --- | --- |
| `Cube` | Fachada `Skewb` e modelo `State` do quebra-cabeça. |
| `Mapping.Parts` | Peças de estado: centros (`Center`) e cantos (`Corner`). |
| `Mapping.Moves` | Movimentos e notações. `Moves` aplica transformações matriciais; `WCAMoves`, `AdvancedMoves` e `FLMoves` definem conjuntos de movimentos. |
| `Mapping.Moves.Matrices` | Matrizes que descrevem permutações de centros e faces dos cantos. |
| `Solution` | Busca de soluções e utilitários relacionados. `BFSSkewb` explora estados por distância; `Solution` reúne operações de busca e manipulação de sequências. |
| `Solution.utils` | Representações auxiliares de sequência e nó de busca (`Scramble`, `StateNode`). |
| `Patterns.base` | Tipos base para caso (`Case`), coleção de casos (`Cases`) e variantes. |
| `Patterns.Methods` | Catálogo e implementações de casos dos métodos NS, EG2, FL e FF, incluindo subcasos. |
| `Patterns.utils` | Estruturas auxiliares para casos, como `TreeCases`. |
| `utils` | Codificação compacta e classificação de estados (`BitState`, `StateRank`) e fila encadeada (`LinkedQueue`). |

## Modelo do cubo

`State` mantém seis centros e oito cantos. Os valores das peças representam faces; cada canto guarda três faces. `State.getSolvedState()` cria a configuração resolvida usada como referência. O estado também oferece cópia, mascaramento de camada/face e geração das 24 orientações espaciais (perspectivas) do estado.

`Skewb` reúne um estado atual, os estados resolvidos aceitos para comparação e os conjuntos de movimentos disponíveis. Por padrão usa a notação WCA; `toAdvanced()` seleciona a notação avançada e `toWCA()` retorna à WCA. `applyScramble` aplica uma sequência ao estado atual, e `reset()` restaura o estado inicial guardado.

## Movimentos

Um movimento é representado por `CentersFaces`: matrizes de permutação para centros e posições de faces nos cantos. `Moves.move` aplica as matrizes a um `State`; pode atualizar o estado recebido ou produzir um novo estado. As matrizes inversas fornecem movimentos inversos.

As notações são mapas de strings para transformações. Consulte `getNotation()` nas implementações para ver os tokens aceitos; não presuma que qualquer notação externa ou algoritmo seja validado automaticamente. `Skewb.applyScramble(String)` separa tokens por espaços.

## Busca de estados

`BFSSkewb.BFS(max, initialState, moves)` expande estados com prioridade pela distância em movimentos até o limite `max`, guardando uma sequência encontrada para cada estado. Estados equivalentes em diferentes perspectivas são tratados como visitados em conjunto. Os atalhos `getFLScrambles`, `getFFScrambles` e `getAllScrambles` preparam estados mascarados e limites específicos para gerar conjuntos usados pelos respectivos casos.

`Solution` oferece operações de busca e composição/inversão de sequências. A busca pode crescer rapidamente com a profundidade; os limites devem ser escolhidos considerando o conjunto de movimentos e o espaço de estados.

## Casos e métodos

`Case` descreve um caso por nome e transformação, e permite aplicá-lo a um estado ou obter suas variantes. `Cases` agrupa casos e pode escolher um aleatoriamente. As classes em `Patterns.Methods` especializam essas bases para NS, EG2, FL e FF e para seus subcasos. `Methods.getInstance()` fornece o catálogo dos grupos principais.

## Codificação de estado

`StateRank` converte estados em ranks de permutação e em `BitState`, que empacota os ranks em um identificador `long`; também reconstrói um `State` a partir desse identificador. A codificação pressupõe estados válidos do Skewb: não é uma validação genérica de dados arbitrários.

## Pontos de entrada úteis

- Criar estado resolvido: `State.getSolvedState()`.
- Criar cubo com movimentos e orientação inicial padrão: `new Skewb()`.
- Aplicar movimentos diretamente: `Moves.applyMove(String)` ou `Skewb.applyScramble(...)`.
- Listar movimentos de uma notação: `Moves.getNotation()`.
- Gerar estados alcançáveis até um limite: `BFSSkewb.BFS(...)`.
- Acessar coleções de métodos: `Methods.getInstance()`.

## Alterações no núcleo

Ao adicionar um movimento, defina sua transformação em `AdvancedMoves` (ou na implementação apropriada) e confirme a notação exposta pelo mapa. Ao adicionar famílias de casos, siga as abstrações de `Patterns.base` e registre o grupo no catálogo `Methods` quando ele precisar aparecer junto aos métodos existentes. Mantenha índices de centros, cantos e faces consistentes com a convenção usada em `State.getSolvedState()`.
