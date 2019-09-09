# Demo Apache Kafka com Spring Boot

## O que é Apache Kafka

Apache Kafka é uma plataforma distribuída de mensagens e streaming.

Leituras importantes antes de seguir com a Demo:
* https://medium.com/luizalabs/entendendo-o-apache-kafka-i-27342ec9e29
* https://medium.com/luizalabs/entendendo-o-apache-kafka-ii-b19d79e175f5

Documentação:
* https://kafka.apache.org/

## Executar a Demo na minha máquina

### Ambiente necessário:

* Maven 3.x.x
* Java 8 ou superior
* Docker

### Executando:

1. Clonar o projeto
2. No projeto exsite um docker-compose para subir a instância do Apache Kafka. Executar o seguinte comando raiz do projeto: `docker-compose up -d`
3. Executar o comando `mvn clean install`
4. Executar o comando `mvn spring-boot:run` ou executar a classe Application.class.

### Testando a Demo:

1. Enviar uma mensagem do tipo `String` para um tópico para ser consumida:

    1. Executar a url: http://localhost:8080/api/kafka?mensagem=Hello
    2. No console da aplicação deverá existir um log com a seguinte mensagem: b.c.d.controller.MessageController: ==> Recebido mensagem 'Hello'
    3. Na sequência deve existir uma outra mensagem no console: b.c.d.kafka.KafkaConsumer: ====> Consumiu mensagem: 'Hello'

            Executado os 3 passos com sucesso, quer dizer que o serviço recebeu uma mensgem do tipo string, adicionou em un tópico no kafka, e após a mensagem foi consumida da fila.

2. Enviar uma mensagem do tipo `Mensagem` para um tópico, e ser consumida:
    1. Executar a url: http://localhost:8080/api/kafka/object?mensagem=Hello
    2. No console da aplicação deverá existir um log com a seguinte mensagem: b.c.d.controller.MessageController: ==> Recebido mensagem 'Hello'
    3. Na sequência deve existir uma outra mensagem no console: b.c.d.kafka.KafkaConsumer: ====> Consumiu mensagem: 'Message[uuid=19b495bc-08ae-485d-b0bc-6ece3bb354e5, message='Hello', date=2019-01-17T14:39:38.302017300]'


            Executado os 3 passos com sucesso, quer dizer que o serviço recebeu uma mensgem do tipo string, criou um objeto do tipo Mensagem, adicionou em un tópico no kafka, e após a mensagem foi consumida da fila.

### Passo a passo de desenvolvimento:

1. Criar um projeto Spring Boot e adicionar a dependência:

        <dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>

2. Configurar em `/src/main/resources/application.yml` o endereço do servidor Apache Kafka:

        kafka:
          bootstrapAddress: localhost:9092

        Obs: o endereço do Apache Kafka vai depender de onde você estiver rodando o serviço, caso esteja no Docker na sua própria maquina o endereço vai ser localhost:9092. Caso esteja rodando no Docker na sua máquina, mas utlizando o Docker Toolbox para Windows, você deverá utilizar no lugar do localhost o IP que for gerado na inicialização.

3. Configurar o producer conforme a classe `br.com.db1globalsoftaware.kafka.KafkaProducerConfig`.
4. Configurar o consumer conforme a classe `br.com.db1globalsoftaware.kafka.KafkaConsumerConfig`.
5. Implementar consumer conforme a classe `br.com.db1globalsoftaware.kafka.KafkaConsumer`.
6. Injetar producers em adicionar mensagens na fila conforme a classe `br.com.db1globalsoftaware.controller.MessageController`.