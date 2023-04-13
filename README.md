# Reprodutor

### Resumo
Esse programa é uma tentativa de fazer um reprodutor de música em java.

### História
Eu fiquei muito tempo tentando fazer um reprodutor de música em java, um verdadeiro desafio.

Primeiramente eu comecei com a biblioteca de Clip, AudioSystem e InputStream nativas do Java para fazer isso, contudo, infelizmente o Java só suporta o formato .wav e outros formatos que não conheço, não dava pra usar essas bibliotecas para o que eu queria.

Posteriormente eu me arrisquei na biblioteca JLayer, que em primeiro momento eu não consegui utilizar, mas posteriormente eu achei um site onde era ensinado quais os passos que deveriam ser realizados para utilizar essa biblioteca. A biblioteca JLayer é boa, ela suporta mais formatos, mas infelizmente ela não tem algo essencial que eu buscava que é poder mudar a posição da música que você está ouvindo, por isso eu parti pra próxima.

Depois de passar algumas outras bibliotecas que eu não gostei, eu cheguei no JavaFX. O JavaFX é um conjunto de bibliotecas do Java (que pra instalar dá um trabalho grande, precisa instalar o Java SE. Obs.: não é o JDK) que vem em substituição ao Swing. Você me pergunta: "Mas porque ele não está nativamente no Java?". Não sei :).

O JavaFX consegue ser a melhor biblioteca no sentido de músicas, tanto que é a que eu adotei aqui, porque ela suporta vários formatos e consegue mudar a posição da música, pausar, parar, decidir qual evento irá acontecer quando a música começar, pausar ou parar (Sim, tem isso), é possível até controlar o volume da música. Infelizmente não suporta alguns formatos como .flac e aparentemente alguns tipos de .wav que eu testei e não funcionou.

### Projeto
O projeto atualmente possibilita escolher uma pasta de músicas como uma biblioteca (ele não pega as músicas que estão em subpastas) e te permite saber a posição atual da música, o tamanho dela e mudar a posição da música através do Slider do JavaFX.

### Instalação
Para testar esse projeto é possível apenas executar o arquivo <a href="Executar.bat">Executar.bat</a> que está na raiz do projeto. Se você quiser abrir ele na sua IDE, é necessário instalar o JavaFX através da instalação do Java SE e configurar na sua IDE.

### Agradecimentos
Agradeço a minha família, amigos e a você que leu até aqui (Parabéns!), sinta-se a vontade para melhorar o projeto :).
