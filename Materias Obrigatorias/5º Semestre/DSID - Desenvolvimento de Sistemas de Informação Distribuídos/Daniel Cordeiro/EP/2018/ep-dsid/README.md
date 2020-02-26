Para executar todos os testes locais, execute o script `executeAllTestsLocal.sh` com a tecnologia como parâmetro.
A tecnologia pode ser GRPC ou OTHER. A especificação escolhida para OTHER foi AVRO.

Exemplo:

    executeAllTestsLocal.sh GRPC
  
O console printará então o resultado para todos os testes.


---

Para executar todos os testes em máquina externa, execute o script `executeAllTestsForIP.sh` com os parâmetros: host, port e tecnologia utilizada.

Exemplo:

    executeAllTestsForIP.sh 192.168.1.168 8081 OTHER
    
O console printará então o resultado para todos os testes.

---

Para executar os testes remotamente, disponibilizamos a facilidade e executar o servidor através do script `startServer.sh`, com a tecnologia como único parâmetro:

    startServer.sh GRPC