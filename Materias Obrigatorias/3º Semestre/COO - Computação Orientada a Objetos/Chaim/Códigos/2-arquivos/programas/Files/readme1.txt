Para executar a Jabuti
java -cp Jabuti-bin.zip br.jabuti.gui.jabutiGUI

Para executar um caso de teste
java -cp Jabuti-bin.zip br.jabuti.probe.ProberLoader -p <nome projeto> <classe a executar> [parâmetros para execução]

Para instrumentar e armazenar instrumentado
java -cp Jabuti-bin.zip br.jabuti.probe.ProberInstrum -o <arquivo .jar> -p <nome projeto> <classe a executar>

Uma vez instrumentado, para executar um caso de teste
java -cp <arquivo .jar> <classe a executar> [parâmetros para execução]
