/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devfreelas.view;

import devfreelas.model.Empresa;
import devfreelas.model.PessoaFisica;
import devfreelas.model.ProcessoSeletivo;
import devfreelas.model.Projeto;
import devfreelas.model.ProjetoDAO;
import devfreelas.model.Usuario;
import java.util.Scanner;
import devfreelas.model.UsuarioDAO;

public class MenuEmpresa { 
    UsuarioDAO udao = new UsuarioDAO();;
    Empresa emp = null;
    Projeto proj = new Projeto();
    ProcessoSeletivo ps = new ProcessoSeletivo();
    
    public void MenuPrincipal(Usuario usuario)
    {
        this.emp = (Empresa) usuario;
        int count = 1;
        Scanner ler = new Scanner(System.in);
        do 
        {
            System.out.println("_______MENU EMPRESA_______");
            System.out.println("0.Sair");
            System.out.println("1.Projetos");
            System.out.println("2.Contratantes");
            System.out.println("3.Dev's");
            System.out.println("4.Meu perfil");
            System. out. println("Digite o codigo desejado:");
            try {
                count = Integer.parseInt(ler.nextLine());
                System.out.println("\n");
                switch (count){
                    case 0:
                        break;
                    case 1:
                        this.ProjetosMenu(usuario);                          //1.Listar Projetos, 2.Criar Projeto, 3.Meus Projetos
                        break;
                    case 2:
                        this.contratantesMenu();                             //Escolha uma empresa para abrir o perfil
                        break;
                    case 3:       
                        this.devsMenu();                                    //Listar Devs, Listar Devs contratados
                        break;
                    case 4:
                        this.meuPerfilMenu();                               //Ver Perfil, Editar Perfil
                        break;
                    default:
                        System.out.println("Digite um numero valido.");             // precaução contra o usuario
                        break;
                } 
            }catch (NumberFormatException  e) {    
                System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");        // precaução contra o usuario
                continue;
            }
        } while (count != 0);
    }

    private void ProjetosMenu(Usuario usuario) {
        int count = 1;
        int cod;
        ProjetoDAO pdao = new ProjetoDAO();
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("_______MENU PROJETOS_______");    
            System.out.println("0.Voltar");
            System.out.println("1.Listar Projetos");
            System.out.println("2.Criar Projeto");
            System.out.println("3.Meus Projetos");
            System.out.println("Digite o codigo desejado:");
            try {
                count = Integer.parseInt(ler.nextLine());
                System.out.println("\n");
                switch (count) {
                    case 1:
                        pdao.listar();                                                          // Listar projetos
                        break;
                    case 2:
                        pdao.inserir(criarProjeto());                                       // Criar um Projeto
                        break;
                    case 3:
                        System.out.println("_______MENU SELECAO_______");
                        pdao.listarProjeto(usuario);                                    // Listar meus Projetos
                        System.out.println("Digite o codigo do projeto que deseja abrir:");
                        cod = Integer.parseInt(ler.nextLine());
                        try
                        {
                            proj = pdao.pesquisarPeloID(cod); 
                            System.out.println(proj + "\n");
                            if(proj != null && proj.getEmpresa().getId() == usuario.getId())
                            {
                                int count2 = 1;
                                do
                                {
                                    System.out.println("0.Sair");
                                    System.out.println("1.Criar processo seletivo");
                                    System.out.println("2.Realizar selecao");
                                    System.out.println("3.Encerrar processo seletivo");
                                    System.out.println("4.Reabrir processo seletivo");
                                    System.out.println("5.Excluir projeto");
                                    try{
                                        count2 = Integer.parseInt(ler.nextLine());
                                        switch (count2) {
                                            case 0:
                                                break;
                                            case 1:
                                                criarSelecao(proj);                                             // Cria um processo seletivo para o projeto
                                                break;
                                            case 2:
                                                PessoaFisica pes = proj.getProcessoSeletivo().contratar();
                                                if(pes != null){ 
                                                    System.out.println("Candidato contratado:\n" + pes  + "\n");  // efetua contratação
                                                }
                                                else
                                                    System.out.println("Sem candidatos no momento");                        //verifica se existem inscrições          
                                                break;
                                            case 3:
                                                proj.encerraProcessoSeletivo();
                                                System.out.println("Projeto encerrado com sucesso!\n");                 //encerra processo seletivo
                                                break;
                                            case 4:
                                                proj.reabreProcessoSeletivo();
                                                System.out.println("Projeto reaberto com sucesso!");                    //retoma processo seletivo
                                                break;
                                            case 5:
                                                System.out.println("_______MENU EXCLUIR PROJETO_______");
                                                
                                                if (proj != null && proj.getEmpresa().getId() == usuario.getId()) {
                                                    if (pdao.excluir(proj)) {
                                                        System.out.println("Projeto excluído com sucesso!\n");
                                                    } else {
                                                        System.out.println("Falha ao excluir o projeto.\n");
                                                    }
                                                } else {
                                                    System.out.println("Projeto não encontrado ou você não é o proprietário do projeto.\n");
                                                }
                                                break;
                                            default:
                                                System.out.println("Digite um número válido."); // prevenção contra o usuário
                                                break;
                                        }
                                    }catch (NumberFormatException e) {                                                  //prevenção contra o usuario
                                        System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");
                                        count = 10;
                                    } 
                                    String pausa = ler.nextLine();
                                } while (count2 != 0);
                            }
                            else
                            {
                                //prevenção contra o usuario
                                System.out.println("Voce nao tem um projeto com este codigo!");
                            }
                        }catch (NumberFormatException  e) {                                                 //prevenção contra o usuario
                            System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");
                            continue;
                        } 
                        break;
                }       
            }catch (NumberFormatException  e) {    
                System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");    //prevenção contra o usuario
                continue;
            }    
            String pausa = ler.nextLine(); //pause
        } while (count != 0);
    }

    private void contratantesMenu() 
    {
        int count = 1;
        int id = 0;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("_______MENU CONTRATANTES_______");
            udao.listarEmp();                                                          //Lista empresa
            System.out.println("Escolha uma empresa para abrir o perfil: ");
            System.out.println("0.Voltar");
            try {
                id = Integer.parseInt(ler.nextLine());                              //le do usuario
                if(id == 0)
                {
                    count = 0;                                                         //opção voltar
                }
                else
                {
                    Usuario user = udao.pesquisarPeloID(id);                            //Pesquisa ID de empresa fornecido
                    if(user instanceof Empresa){
                       System.out.println("\n" + user + "\n");                         // se existe printa
                    }
                    else
                    {
                      System.out.println("Insira um ID valido!");                     //se não erro
                    }
                }
            }   
            catch (NumberFormatException e) {
            System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");             //prevenção contra o usuario
            count = 10;
            }      
            String pausa = ler.nextLine();                                      //PAUSE
        } while (count != 0);
    }

    private void devsMenu() {
        int count = 1;
        int id = 0;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("_______MENU DEVS_______");
            System.out.println("1. Listar Devs");
            System.out.println("2. Listar Devs contratados");
            System.out.println("0. Voltar");
            System.out.println("Escolha uma opcao: ");

            try {
                int option = Integer.parseInt(ler.nextLine());                              // LE VARIAVEL
                switch (option) {
                    case 1:
                        System.out.println("_______LISTAR DEVS_______");
                        udao.listarDev();                                                   //Lista os Dev's existentes
                        System.out.println("Escolha um Dev para abrir o perfil: ");
                        System.out.println("0. Voltar");
                        id = Integer.parseInt(ler.nextLine());                          //lê o id do Dev escolhido

                        if (id == 0) {                                                      //acaba com o do e volta
                            count = 0;
                        } else {
                            try {
                                Usuario user = udao.pesquisarPeloID(id);                    //pequisa o dev com o id como parametro
                                if (user instanceof PessoaFisica) {                         
                                 System.out.println("\n" + user + "\n");                    //printa a classe na tela
                                } else {
                                    System.out.println("ID invalido! Tente novamente.");    //caso não encontrar o dev
                                }
                            } catch (Exception e) {
                                System.out.println("Erro ao pesquisar o usuário. Por favor, tente novamente mais tarde."); // em caso de erro
                            }
                        }
                        break;

                    case 2:
                        System.out.println("_______LISTAR DEVS CONTRATADOS_______");
                        emp.listarFuncionarios();                                               //lista os funcionarios da empresa logada
                        System.out.println("Escolha um Dev para abrir o perfil: ");
                        System.out.println("0. Voltar");
                        id = Integer.parseInt(ler.nextLine());

                        if (id == 0) {                                                          //reseta o do while
                            count = 0;
                        } else {                                      
                            Usuario user = udao.pesquisarPeloID(id);
                            if (user instanceof PessoaFisica) {
                                System.out.println("\n" + user + "\n");                         //printa o dev na tela
                            } else {
                                System.out.println("ID invalido! Tente novamente.");            //caso nao exista o dado procurado com a ID
                            }
                        }
                        break;
                    case 0:
                        count = 0;                                                              //acaba com a execução do do while
                        break;
                    default:
                        System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");        //em caso de opção que não exista
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Por favor, digite um numero valido.\n");              //em caso de erro
            }
            String pausa = ler.nextLine();                                                          //PAUSE 
        } while (count != 0);
    }


    private void meuPerfilMenu() {
    int count = 1;
    Scanner ler = new Scanner(System.in);
    do {
        System.out.println("_______MEU PERFIL_______");
        System.out.println("0.Voltar");
        System.out.println("1.Ver Perfil");
        System.out.println("2.Editar Perfil");
        System.out.println("Digite o codigo desejado: ");
        try {
            count = Integer.parseInt(ler.nextLine());
            System.out.println("\n");
            switch (count) {
                case 1:
                    System.out.println(emp);                //mostra o perfil
                    emp.listarFuncionarios();                 //lista os funcionario abaixo
                    break;
                case 2:
                    int subcount = 0;
                    do {
                        System.out.println("_______EDITAR MEU PERFIL_______");
                        System.out.println("0.Voltar");
                        System.out.println("1.Editar nome");
                        System.out.println("2.Alterar senha");
                        System.out.println("Digite o codigo desejado:");
                        try {
                            subcount = Integer.parseInt(ler.nextLine());
                            System.out.println("\n");
                            switch (subcount) {
                                case 0:
                                    break;                                                          //reseta o do while
                                case 1:
                                System.out.println("Insira o novo nome: ");                                 
                                    emp.setNome(ler.nextLine());                                //le o novo nome
                                    if(udao.editar(emp))
                                        System.out.println("Nome alterado com sucesso!");           //executa alteração e confirma
                                    else 
                                        System.out.println("Erro na alteracao!");                   //caso não funcione
                                    break;
                                case 2:
                                    System.out.println("Insira a nova senha: ");                            // Le a nova senha
                                    emp.setSenha(ler.nextLine());
                                    if(udao.editar(emp))
                                        System.out.println("Senha alterada com sucesso!");          //executa alteração e confirma
                                    else 
                                        System.out.println("Erro na alteracao!");                   //caso não funcione
                                    break;
                                default:
                                    System.out.println("Digito invalido!");                        //em caso de digito inexistente no switch
                                    break;
                            }
                    
                        }catch (NumberFormatException e) {
                            System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");        //em caso de erro
                        }
                        break;
                    }while(subcount != 0);
            }
        }catch (NumberFormatException e) {
            System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");                // em caso de erro
        }
        String pausa = ler.nextLine();
    } while (count != 0);
}

    private Projeto criarProjeto() {                                                    //cria um novo projeto e retorna ele
        Scanner ler = new Scanner(System.in);
        ProjetoDAO pdao = new ProjetoDAO();
        
        System.out.println("Qual sera o nome do novo projeto?");
        proj.setNome(ler.nextLine());                                               //le o nome
        System.out.println("Crie uma descricao do seu projeto:");
        proj.setDescricao(ler.nextLine());                                      //a descrição
        proj.setEmpresa(emp);                                                     // salva a empresa que acessou a função
        int nivel;
        do
        {
            System.out.println("Selecione o nivel exigido:\n1.Junior\n2.Pleno\n3.Senior");
            nivel = Integer.parseInt(ler.nextLine());                                       //le o novo nivel por codigo
            switch (nivel) {
                case 1:
                    proj.setRequisito(Nivel.JUNIOR);                                //seta na variavel
                    break;
                case 2:
                    proj.setRequisito(Nivel.PLENO);                                //seta na variavel
                    break;
                case 3:
                    proj.setRequisito(Nivel.SENIOR);                                //seta na variavel
                    break;
                default:
                    nivel = 0;                                                              //acaba a execução da do while
                    break;
            }
        }while (nivel == 0);
        String pausa = ler.nextLine();
        return proj;                                                                        //retorna o projeto
    }

private void criarSelecao(Projeto p) {                  //recebe os dados para abrir o processo seletivo para a vaga
        Scanner ler = new Scanner(System.in);
        if(p.getProcessoSeletivo() == null)                                     //testa se o processo ja existe para o projeto
        {
            System.out.println("Digite o salario oferecido pela vaga: ");
            ps.setSalario(ler.nextDouble());                            //recebe o salario
            ps.setRequisito(p.getRequisito());                                      //pega a variavel requisito de projeto
            p.iniciarProcessoSeletivo(ps.getRequisito(), ps.getSalario());  //inicia projeto seletivo
        }else{
            System.out.println("Ja existe processo seletivo para este projeto!\n");   //não executa em caso de ja existir
        }
        String pausa = ler.nextLine();                                                  //pause
    }
}