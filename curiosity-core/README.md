Curiosity CORE
===========

Todas as funcionalidades do ambiente que compoem a sonda e o planalto estão contidas dentro desse pacote.

Porque das coisas
-------------------

Temtei resumir o pouco que pensei ao contruir esse projetinho... 

- State: CompassEnum
  - Quem saber a proxima é a direção atual 
- Strategy: Walk
  - Andar é uma função igual para todas as direções um interface facilita o polimorfismo
- Chain Responsability: Walk
  - Em vez de controlar qual direção andar com um uma classe externa aos walk os proprios walks conhecem se podem ou nao processar o pedido 
- Tell, Don’t Ask: Probe e Plateaus
  - As duas classes tem position, mas ninguem de fora precisa conhecer
- Single Responsibility Principle: Probe, Plateaus, CompassEnum, ActionProbeEnum
  - Nem uma classe utrapassa os limites de suas responsabilidades...
- DSL: CuriosityService
- packge 
  - help: para classes auxiliares 
  - service: para classes serviços
  

