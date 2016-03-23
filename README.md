Projecto de Sistemas Distribuídos

Primeira entrega - 2014-04-11 - 20h

Grupo de SD 24

Repositório T-05-18-24

David Limpo 70630 david.limpo30@gmail.com
Francisco Pedreira 71033 pedreira.francisco@gmail.com
Rui Santos 71042 rddsantos@gmail.com

-------------------------------------------------------------------------------

Serviço RegistoFatura

Instruções de instalação para Windows

[1] Iniciar servidores

JUDDI:
> startup


[2] Criar directoria de trabalho

cd temp
mkdir SD
cd SD


[3] Obter versão entregue

svn co svn+ssh://ist171033@sigma.ist.utl.pt/afs/ist.utl.pt/groups/leic-es/svn/T-05-15-24/registofatura-ws/tags/R_2 registofatura-ws
svn co svn+ssh://ist171033@sigma.ist.utl.pt/afs/ist.utl.pt/groups/leic-es/svn/T-05-15-24/registofatura-ws-cli/tags/R_2_cli registofatura-ws-cli

[4] Geração de stubs Serviço Alive

Abrir registofatura-ws/src/registofatura/ws/RegistoFaturaMain.java com um editor de texto
Comentar as linhas 15,16 e 25 até 106 (inclusivé)

[5] Importar stubs Serviço Alive

cd registofatura-ws
ant clean build db-clean db-build db-build2 db-build3 run -Darg0=Backup1 -Darg1=Alive1 -Darg2=8082 -Darg3=0 -Darg4=regfatdb -Darg5=regfatdbuser -Darg6=0

Abrir nova linha de comandos em registofatura-ws.

ant wsimportAlive
ant build

Parar Backup1.

[6] Executar servidores secundários

Descomentar as linhas de codigo anteriormente referidas.

cd registofatura-ws
ant run -Darg0=BackupXXX -Darg1=AliveXXX -Darg2=PORT -Darg3=0 -Darg4=regfatdbZZZ -Darg5=regfatdbuserZZZ -Darg6=KKK

Onde:
XXX = 1,2 ... N (incrementado sequencialmente a cada novo servidor)
PORT= porto de comunicação desejado (exemplo: 8082)
ZZZ = null, 2 ou 3
KKK = numero de servidores secundários

[7] Executar servidores primario
Nota: executar todos os servidores secundários primeiro que o primário

cd registofatura-ws
ant run -Darg0=RegistoFatura -Darg1=Alive0 -Darg2=PORT -Darg3=1 -Darg4=regfatdbZZZ -Darg5=regfatdbuserZZZ -Darg6=KKK

Onde:
XXX = 1,2 ... N (incrementado sequencialmente a cada novo servidor)
PORT= porto de comunicação desejado (exemplo: 8080)
YYY = 1
ZZZ = null, 2 ou 3
KKK = numero de servidores secundários

[8] Construir cliente

[Abrir outra linha de comandos]

cd registofatura-ws-cli
ant clean build run -Darg0=http://localhost:8081 -Darg1=RegistoFatura


[9] EXEMPLO:
cd registofatura-ws
ant run -Darg0=Backup1 -Darg1=Alive1 -Darg2=8082 -Darg3=0 -Darg4=regfatdb2 -Darg5=regfatdbuser2 -Darg6=2

cd registofatura-ws
ant run -Darg0=Backup2 -Darg1=Alive2 -Darg2=8083 -Darg3=0 -Darg4=regfatdb3 -Darg5=regfatdbuser3 -Darg6=2

cd registofatura-ws
ant run -Darg0=RegistoFatura -Darg1=Alive0 -Darg2=8080 -Darg3=1 -Darg4=regfatdb -Darg5=regfatdbuser -Darg6=2

Executar cliente.



-------------------------------------------------------------------------------

Instruções de teste:
(Como verificar que todas as funcionalidades estão a funcionar correctamente)


[1] Ao executar o run no cliente como feito anteriormente, são testadas todas as respostas 
	possiveis (incluindo os casos de erro) nos serviços disponiveis.


-------------------------------------------------------------------------------
FIM
