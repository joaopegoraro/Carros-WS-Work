# Carros WS Work

## Screenshots
<p float="left">
  <img src="https://github.com/joaopegoraro/Carros-WS-Work/blob/master/screenshots/screenshot1.png" width="32%" />
  <img src="https://github.com/joaopegoraro/Carros-WS-Work/blob/master/screenshots/screenshot2.png" width="32%" />
  <img src="https://github.com/joaopegoraro/Carros-WS-Work/blob/master/screenshots/screenshot3.png" width="32%" />
</p>
<p float="left">
  <img src="https://github.com/joaopegoraro/Carros-WS-Work/blob/master/screenshots/screenshot4.jpeg" width="32%" />
  <img src="https://github.com/joaopegoraro/Carros-WS-Work/blob/master/screenshots/screenshot5.jpeg" width="32%" />
  <img src="https://github.com/joaopegoraro/Carros-WS-Work/blob/master/screenshots/screenshot6.jpeg" width="32%" />
</p>

## Arquitetura

O app está usando a arquitetura MVVM recomendada pela Google. 
Possui um banco de dados Room com tabelas para o perfil do usuário, para as Leads e para os carros. Cada entidade (Perfil, Carro, Lead) possui um 
repositório que manipula os dados tanto localmente através do banco de dados, quanto remotamente através da API (usando Retrofit). Esse repositório é 
utilizado por uma única ViewModel no app chamada CarrosViewModel, que possui a lógica de negócio do app, com metódos para favoritar carros, 
atualizar a lista, além de uma classe chamada CarroState que armazena o estado das interfaces (como por exemplo a lista de carros, 
de leads, o perfil, etc). Essa classe de estado armazena o estado em forma de flow, que provém diretamente do banco de dados, 
esse sendo a única fonte de verdade dos dados do aplicativo. Portanto, quando a viewModel busca os dados da API, o repositório atualiza o banco, 
que por sua vez emite um flow para o CarroState e as telas que o utilizam possam se recompor. O aplicativo utiliza a biblioteca Koin para fazer a injeção 
de dependências.

Existem duas telas no aplicativo, TelaCarros, e TelaMinhaLista, e a navegação entre elas é feita através de uma drawer.
Na TelaCarros, há a lista de carros carregada do banco, dados esses provenientes da API. Cada item de carro na lista 
contém informações do carro, e um botão "EU QUERO" para poder favoritar o carro. Ao fazer isso, é criada uma lead, salva no banco e enviada para API, 
porém apenas se o usuário tiver um e-mail cadastrado. Caso contrário, é mostrada uma dialog que direcionada o usuário a cadastrar um e-mail, isso feito 
no topo da drawer de navegação.
A outra tela TelaMinhaLista, contém as leads do usuário, em uma interface semelhante à da TelaCarros, com a diferença que ao invés de ter um botão 
"EU QUERO", possui um botão com um símbolo de remover, que remove a lead do banco.

Também, através da biblioteca do WorkManager, há um worker que periodicamente (de 1 em 1 hora), envia as leads salvas no banco (caso existam), 
para o backend.

A interface do aplicativo e a paleta de cores foram inspiradas no site da [WS Work](https://www.wswork.com.br/)
