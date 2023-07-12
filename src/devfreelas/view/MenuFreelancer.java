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
import devfreelas.model.UsuarioDAO;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuFreelancer {
//    Usuario usuario = null;
    UsuarioDAO udao = new UsuarioDAO();;
    PessoaFisica pf = null;
    Projeto proj = null;
    
    public void MenuPrincipal(Usuario usuario)
    {
        this.pf = (PessoaFisica) usuario;                   //transforma o usuario recebido como parametro em pessoa fisica
        int count = 1;
        Scanner ler = new Scanner(System.in);
        do 
        {
            System.out.println("_______MENU FREELANCER_______");
            System.out.println("0.Sair");
            System.out.println("1.Projetos");                 //listar projetos, inscreva-se, cancelar inscrição, inscrições
            System.out.println("2.Contratantes");             //lista o nome das empresas
                                                                //seleciona a empresa para visualizar informações
            System.out.println("3.Dev's");                    //lista o nome dos freelas
                                                                //seleciona o freela para visualizar informações                  
            System.out.println("4.Meu perfil");
            System. out. println("Digite o codigo desejado:");
            try {
                count = Integer.parseInt(ler.nextLine());
                System.out.println("\n");
                switch (count){
                    case 0:
                        break;
                    case 1:
                        this.projetosMenu();
                        //listar projetos , inscreva-se, cancelar inscrição, inscrições
                        break;
                    case 2:
                        this.contratantesMenu();
                        //lista o nome das empresas                                  
                        //seleciona a empresa para visualizar informações                     
                        break;
                    case 3:
                        this.devsMenu();
                        //lista o nome dos freelas                                   
                        //seleciona o freela para visualizar informações                
                        break;
                    case 4:
                        if(this.meuPerfilMenu() == false)                       //caso o usuario delete seu perfil sera deslogado
                            return;
                        else
                        //mostrar seu perfil                                        
                        //poder editar perfil                                       
                        break;
                    default:
                        System.out.println("Digite um numero valido.");         //caso o case não exista
                        break;
                }
            }catch (NumberFormatException e) {
                System.out.println("Opcao invalida. Por favor, digite um numero valido.\n"); //em caso de erro
            }
        } while (count != 0);
    }
    private void projetosMenu() {
        int count = 1;
        int cod = 0;
        ProjetoDAO pdao = new ProjetoDAO();
        ProcessoSeletivo pSel = null;

        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("_______MENU PROJETOS_______");
            System.out.println("0.Voltar"); // OK
            System.out.println("1.Listar Projetos"); // OK
            System.out.println("2.Inscrever-se em um Processo Seletivo"); // OK
            System.out.println("3.Cancelar Inscricao em um Projeto"); // OK
            System.out.println("4.Ver Inscricoes em Processos Seletivos"); // OK
            System.out.println("Digite o codigo desejado:");
            try {
                count = ler.nextInt();
                System.out.println("\n");
                switch (count) {
                    case 0:
                        break;
                    case 1:
                        // Listar projetos
                        pdao.listar();
                        break;
                    case 2:
                        // fazer inscricao em um processo seletivo
                        System.out.println("Processos Seletivos: ");
                        pdao.listarPseletivo();
                        System.out.println("0.Voltar");
                        System.out.println("Digite o codigo da vaga que deseja se inscrever:");
                        cod = ler.nextInt();
                        if (cod == 0) {
                            break;
                        }
                        proj = pdao.pesquisarPeloID(cod);
                        pSel = proj.getProcessoSeletivo();
                        pSel.inscrever(pf);
                        pSel.listarCandidatos();
                        break;
                    case 3:
                        // Cancelar Inscrição em um processo seletivo
                        System.out.println("Processos Inscritos: ");
                        pf.listarInscricoes();
                        System.out.println("Digite o codigo do processo que deseja cancelar a inscricao:");
                        cod = ler.nextInt();
                        proj = pdao.pesquisarPeloID(cod);
                        pSel = proj.getProcessoSeletivo();
                        if (pSel.removerCandidato(pf)) {
                            System.out.println("Voce desistiu de concorrer a esta vaga!");
                        } else
                            System.out.println("Voce nao e candidato desta vaga!");
                        break;
                    case 4:
                        // Ver Inscrições do meu perfil
                        pf.listarInscricoes();
                        break;
                    default:
                        System.out.println("Digite um numero valido.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");        //EM CASO DE ERRO
                ler.nextLine(); 
            }
            String pausa = ler.nextLine();  //PAUSE
        } while (count != 0);
    }     
    
    private void contratantesMenu() {
        int count = 1;

        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("_______MENU CONTRATANTES_______");
            System.out.println("0.Voltar");
            System.out.println("1.Listar Empresas");
            System.out.println("2.Visualizar Informacoes de uma Empresa");
            System.out.println("Digite o codigo desejado:");
            try {
                count = Integer.parseInt(ler.nextLine());
                System.out.println("\n");
                switch (count) {
                    case 1:
                        System.out.println("_______MENU CONTRATANTES_______");
                        udao.listarEmp();                                                               //Lista empresas existentes
                        break;
                    case 2:
                        int id = 0;
                        Empresa e = null;
                        udao.listarEmp();                                                                   //lista as empresas
                        System.out.println("Informe o ID da empresa que deseja ver o perfil: ");
                        id = ler.nextInt();                                                                 // le a id
                        ler.nextLine(); 
                        Usuario user = udao.pesquisarPeloID(id);                                            //pesquisa a id e recebe em user uma classe Empresa
                        if (user instanceof Empresa) {
                            System.out.println("\n" + user + "\n");                                         //printa perfil dela
                        } else {
                            System.out.println("Insira um ID valido!");                                     //caso o ID não exista
                        }
                        break;
                    case 0:                                                             
                        break;
                    default:
                        System.out.println("Opcao invalida!"); // tratamentos de erro (digito errado)
                }
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");            //caso de erro
            }
            String pausa = ler.nextLine();                                  //PAUSE 
        } while (count != 0);
    }

    private void devsMenu() {
        int count = 1;
        int id = 0;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("_______MENU DEVS_______");
            udao.listarDev();                                                                       //Lista devs existentes
            System.out.println("Escolha um Dev para abrir o perfil: ");         
            System.out.println("0.Voltar");                                     
            try {
                id = Integer.parseInt(ler.nextLine());
                if(id == 0)
                    count = 0;                                                                      //encerra do while
                else
                {
                    Usuario user = udao.pesquisarPeloID(id);                                        //pesquisa dev
                    if(user instanceof PessoaFisica){
                        System.out.println("\n" + user + "\n");                                     //printa dev na tela caso exista
                    }
                    else{
                        System.out.println("Insira um ID valido!");                                 //caso nao exista
                    }
                }
            }catch (InputMismatchException e) {
                System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");        //caso de erro
                ler.nextLine(); 
            }
            String pausa = ler.nextLine();
        } while (count != 0);
    }

    private boolean meuPerfilMenu() {
        int count = 1;
        Scanner ler = new Scanner(System.in);

        do {
            System.out.println("_______MEU PERFIL_______");
            System.out.println("0. Voltar");
            System.out.println("1. Ver Perfil");
            System.out.println("2. Editar Perfil");
            System.out.println("3. Excluir Perfil");
            System.out.println("Digite o codigo desejado: ");

            try {
                count = Integer.parseInt(ler.nextLine());
                System.out.println("\n");

                switch (count) {
                    case 1:
                        System.out.println(pf);                             //printa o perfil
                        pf.listarProjetos();                                //lista projetos da pessoa
                        break;
                    case 2:
                        int subcount = 0;
                        do {
                            System.out.println("_______EDITAR MEU PERFIL_______");
                            System.out.println("0. Voltar");
                            System.out.println("1. Editar nome");
                            System.out.println("2. Alterar senha");
                            System.out.println("3. Alterar nivel");
                            System.out.println("Digite o codigo desejado:");

                            try {
                                subcount = Integer.parseInt(ler.nextLine());
                                System.out.println("\n\n\n");

                                switch (subcount) {
                                    case 1:
                                        System.out.println("Insira o novo nome: ");
                                        pf.setNome(ler.nextLine());                     //le novo nome
                                        if (udao.editar(pf)) {
                                            System.out.println("Nome alterado com sucesso!");           //altera e executa se funcionar para confirmar
                                        } else {
                                            System.out.println("Erro na alteracao!");                   //caso de erro
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Insira a nova senha: ");
                                        pf.setSenha(ler.nextLine());                        //le nova senha
                                        if (udao.editar(pf)) {
                                            System.out.println("Senha alterada com sucesso!");  //altera e executa se funcionar para confirmar
                                        } else {
                                            System.out.println("Erro na alteracao!");           //caso de erro
                                        }
                                        break;
                                    case 3:
                                        int nivel = 0;
                                        do
                                        {
                                            System.out.println("Selecione seu nivel:\n1. Junior\n2. Pleno\n3. Senior");
                                            try {
                                                nivel = Integer.parseInt(ler.nextLine());                   // le o novo nivel
                                                Nivel exp = Nivel.JUNIOR;
                                                if (nivel == 1) {
                                                    exp = Nivel.JUNIOR;
                                                    pf.setNivel(exp);
                                                } else if (nivel == 2) {
                                                    exp = Nivel.PLENO;
                                                    pf.setNivel(exp);
                                                } else if (nivel == 3) {
                                                    exp = Nivel.SENIOR;
                                                    pf.setNivel(exp);
                                                }else{
                                                    nivel = 0;                                                  //caso seja diferente de 1 2 3 ele para a execução
                                                }  
                                            }catch (NumberFormatException e) {
                                                System.out.println("Por favor, digite um numero valido.");          //caso de erro
                                                continue;
                                            }
                                        }while (nivel == 0);
                                        if (udao.editar(pf)) {
                                            System.out.println("Nivel alterado com sucesso!");      //executa alteração e confirma
                                        } else {
                                            System.out.println("Erro na alteracao!");               //caso de erro
                                        }
                                        break;
                                    case 0:
                                        subcount = 0;                                               //termina execução
                                        break;
                                    default:
                                        System.out.println("Digito invalido!");                  //caso opção nao exista
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Opcao invalida. Por favor, digite um numero valido.\n");        //caso de erro
                            }
                        } while (subcount != 0);
                        break;
                    case 3:
                        System.out.println("Tem certeza de que deseja excluir o perfil? (S/N)");        //confirmação de exclusao
                        String confirm = ler.nextLine().toLowerCase();
                        if (confirm.equals("s")) {                                          //executa com a exclusão com s
                            udao.excluir(pf);
                            System.out.println("Perfil excluido com sucesso!");
                            return false;
                        }
                    case 0:
                        break;                                              //termina execução do menu
                    default:
                        System.out.println("Opcao invalida!");              //caso opção nao exista
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida. Por favor, digite um numero valido.");      //caso de erro
            }
            String pausa = ler.nextLine();                                      //pause
        } while (count != 0);
        return true;
    }

}


