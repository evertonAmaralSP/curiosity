Curiosity Joytick Console
===========

Para navegar foi criado um jar que excuta os comandos basicos para operar as sondas.


Requirements
-------------------

 * Java => 8.0
 * Maven3
 

Installation
--------------
	 # Baixando jar
    donwload: https://github.com/evertonAmaralSP/curiosity/raw/master/curiosity-api/download/curiosity-api.jar
    java -jar curiosity-api.jar
    # console
    git clone https://github.com/evertonAmaralSP/curiosity
    cd curiosity
    mvn clean install
    cd curiosity-api
    mvn package && java -jar target/curiosity-api.jar
    curl http://localhost:8080/explorer?plateaus=5 5&probe1=0 0 N&probe2=5 5 S&intruction2=M&intruction1=M
