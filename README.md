Curiosity
===========

Problema
---------------

Um conjunto de sondas foi enviado pela NASA à Marte e irá pousar num planalto. Esse planalto, que curiosamente é retangular, deve ser explorado pelas sondas para que suas câmera embutidas consigam ter uma visão completa da área e enviar as imagens de volta para a Terra.


Requirements
-------------------

 * Java => 8.0
 * Maven3
 

Installation
--------------

    # Baixando o codigo fonte
    git clone https://github.com/evertonAmaralSP/curiosity
    cd curiosity

    # Use maven para installar as dependencias
    mvn clean compile


Running curiosity
------------------------------

Start aplicação:

    mvn jetty:run

Eclipse curiosity
------------------------------

Start project eclipse:

    mvn eclipse:eclipse -Dwtpversion=2.0

Configurações
------------------------------

    Pasta raiz para exportar arquivos: curiosity.properties

Contributors
------------

 * Everton Amaral <everton.amaral@gmail.com>


License
-------

  (The MIT License)

  Permission is hereby granted, free of charge, to any person obtaining
  a copy of this software and associated documentation files (the
  'Software'), to deal in the Software without restriction, including
  without limitation the rights to use, copy, modify, merge, publish,
  distribute, sublicense, and/or sell copies of the Software, and to
  permit persons to whom the Software is furnished to do so, subject to
  the following conditions:

  The above copyright notice and this permission notice shall be
  included in all copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
  TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
