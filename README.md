# PROGRAMAÇÃO ORIENTADA A 		OBJETOS I
## PROJETO FINAL DA DISCIPLINA
 

O sistema planejado para o projeto foi pensado para ser uma central de oportunidades para Devs freelancers. Este sistema irá permitir que pessoas físicas e empresas realizem o cadastro. No caso de pessoas físicas, o cadastro dará direito a visualizar e realizar candidaturas nas vagas temporárias cadastradas no sistema. Já as empresas cadastradas, terão seus dados e poderão divulgar projetos e, abrir processos seletivos para eles, em seu CNPJ para que os freelancers se candidatem. Cada projeto publicado terá um processo seletivo que conterá uma lista de candidatos interessados onde a empresa terá acesso a cada perfil e poderá efetivar a contratação de um profissional.

O sistema funciona a partir de uma tela inicial que usa a classe console para realizar o login. A partir disso o sistema detecta se o usuário que logou é uma pessoa física ou empresa, pois existem 2 menus diferentes que irão interagir com o usuário baseado em seu tipo. 

### Os projetos terão pré-requisitos, descrição, id, empresa, lista de inscritos, contratado/os (ao final do processo de contratação) e possuirão um processo seletivo cada.  Como métodos a classe possui métodos para abrir e fechar o processo seletivo, iniciar processo seletivo.

### Freelancers (pessoa física) poderão se inscrever e ser contratados para mais de um projeto de trabalho simultaneamente, os freelancers terão um enum que dirá seu nível de experiência, CPF, nome, uma lista para guardar processos seletivos em que se inscreveu e outra lista para guardar os projetos ativos.

### Processos seletivos possuem um requisito, um array list de candidatos, uma empresa responsável, um projeto para seleção, salário e status (variável booleana que define se o processo está aberto ou fechado). Nesta classe existem métodos para a inscrição de uma pessoa física no processo, listagem dos candidatos já existentes, remover candidatos do processo e contratação de um selecionado.

### Empresas possuem nome, CNPJ, lista de projetos, lista de funcionários contratados. O método contratação escolherá um dos candidatos da lista de vagas e o tornará contratado. Método abrir ou fechar vaga pode ser usado pela empresa mediante a disponibilidade. Método demissão para caso o trabalhador tenha sido demitido.

```mermaid
graph LR
    U[Usuario] ----> PF[Pessoa Fisica]
    U ----> E[Empresa]
    PF -- n:n --> PS[ProcessoSeletivo]
    E-- 1:n --> P[Projeto]
	P--1:1-->PS
	PF--n:n-->P
