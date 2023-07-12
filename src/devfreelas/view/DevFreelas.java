/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package devfreelas.view;

import devfreelas.model.Usuario;
import devfreelas.model.Empresa;
import devfreelas.model.UsuarioDAO;
import devfreelas.model.PessoaFisica;
import devfreelas.model.ProcessoSeletivo;
import devfreelas.model.Projeto;
import devfreelas.model.ProjetoDAO;
import java.util.Scanner;

public class DevFreelas {
    public static void main(String[] args) {
        int count = 0;
        Scanner ler = new Scanner(System.in);
        Usuario usuario = new Usuario();
        UsuarioDAO udao = new UsuarioDAO();
        ServicoLogin servicoLogin = new ServicoLogin();
        MenuEmpresa menuE = new MenuEmpresa();
        MenuFreelancer menuF = new MenuFreelancer();
        //Adicionando dados ao sistema
        addData();
        
        //Inicia loop do menu inicial
        do {                                                       
            System.out.println("Ja possui uma conta?");
            System.out.println("1. Sim");
            System.out.println("2. Nao");
            System.out.println("0. Sair");
            try {
                count = Integer.parseInt(ler.nextLine());
                if (count == 1) {   
                    boolean logou = false;
                    while (!logou) {
                        usuario = servicoLogin.logar();                //Chamada da funcao logar que vai checar os dados e retornar null caso nao correspondentes                 
                        if (usuario != null) {
                            if(usuario instanceof Empresa){
                                menuE.MenuPrincipal(usuario);       //abre o Menu para Empresas
                            }
                            else if(usuario instanceof PessoaFisica){
                                menuF.MenuPrincipal(usuario);        //abre o Menu para Dev's
                            }
                            logou = true;                               // Usuario logou e já pode usar o sistema
                        } else {
                            System.out.println("1. Tentar novamente");
                            System.out.println("2. Voltar");
                            int op = Integer.parseInt(ler.nextLine());
                            if (op != 1) {
                                break;                                  // Redireciona pro menu inicial
                            }
                        }
                    }
                } else if (count == 2) {
                    System.out.println("Que tipo de usuario deseja criar?");      // Menu de registro
                    System.out.println("0. Sair");
                    System.out.println("1. Pessoa Fisica");
                    System.out.println("2. Empresa");
                    int count2 = Integer.parseInt(ler.nextLine());

                    String username;
                    String password;
                    String nome;
                                                                                    
                    if (count2 == 1) {  // Coleta os dados especificos de PF e tenta o cadastro
                        System.out.println("Digite seu nome de usuario:");
                        username = ler.nextLine();
                        System.out.println("Digite a sua senha:");
                        password = ler.nextLine();
                        System.out.println("Digite seu nome completo:");
                        nome = ler.nextLine();
                        System.out.println("Digite seu CPF:");
                        String cpf = ler.nextLine();
                        System.out.println("Selecione seu nivel:\n1.Junior\n2.Pleno\n3.Senior");
                        int nivel = Integer.parseInt(ler.nextLine());
                        Nivel exp = Nivel.JUNIOR;
                        if (nivel == 1){
                            exp = Nivel.JUNIOR;
                        }
                        else if(nivel == 2){
                            exp = Nivel.PLENO;
                        }
                        else if(nivel == 3){
                            exp = Nivel.SENIOR;
                        }
                        PessoaFisica pFisica = new PessoaFisica(nome, username, password, cpf, exp);                    
                        if(udao.inserir(pFisica)){                                   // Caso a Pessoa fisica tenha sido cadastrada
                            System.out.println("Usuario Pessoa Fisica criado com sucesso!");
                            System.out.println(pFisica.getNivel());}
                        else                                                           // Caso tenha havido conflito de dados
                            System.out.println("Nome de usuario ja esta em uso!");
                    } else if (count2 == 2) {       // Coleta os dados especificos de Empresa e tenta o cadastro
                        System.out.println("Digite seu nome de usuario:");
                        username = ler.nextLine();
                        System.out.println("Digite a sua senha:");
                        password = ler.nextLine();
                        System.out.println("Digite a razao social da empresa:");
                        nome = ler.nextLine();
                        System.out.println("Digite seu CNPJ:");
                        String cnpj = ler.nextLine();
                        Empresa empresa = new Empresa(nome, username, password, cnpj);
                        if(udao.inserir(empresa))                                   // Caso a Pessoa fisica tenha sido cadastrada
                            System.out.println("Usuario Empresa criado com sucesso!");
                        else                                                           // Caso tenha havido conflito de dados
                            System.out.println("Nome de usuario ja esta em uso!");
                    }else if (count2 == 0){                                             //isola o 0 como digito valido para terminar a execução sem mensagem
                    }else{
                        System.out.println("Digito invalido!");  
                    }
                }else if(count == 0){
                    System.out.println("Terminando Execucao"); //fechando a execução do programa
                }else{
                    System.out.println("Opcao invalida!");
                }
            } 
            catch (NumberFormatException e) {
                System.out.println("Opcao invalida. Por favor, digite um numero valido.");
                count = 10;
            }
        } while (count != 0);
    }

public static void addData(){
        UsuarioDAO udao = new UsuarioDAO();
        
        //adicionando novos freelancers ao sistema
        PessoaFisica pessoa1 = new PessoaFisica("John Doe", "johndoe", "pass", "123456789", Nivel.JUNIOR);
        PessoaFisica pessoa2 = new PessoaFisica("Dick Lugger", "janesmith", "pass", "081978567", Nivel.SENIOR);
        PessoaFisica pessoa3 = new PessoaFisica("Jane Smith", "jsmith", "pass", "987485990", Nivel.PLENO);
        PessoaFisica pessoa4 = new PessoaFisica("Jorge Maidana", "jorgem", "pass", "892029394", Nivel.SENIOR);
        PessoaFisica pessoa5 = new PessoaFisica("Nix Pedrix", "nix", "pass", "111111111", Nivel.SENIOR);
        udao.inserir(pessoa1);
        udao.inserir(pessoa2);
        udao.inserir(pessoa3);
        udao.inserir(pessoa4);
        udao.inserir(pessoa5);
        
        //adicionando novas empresas ao sistema
        Empresa empresa1 = new Empresa("ACME Inc.", "acme", "password", "1234567890");
        Empresa empresa2 = new Empresa("Globex Corporation", "globex", "password", "9876543210");
        Empresa empresa3 = new Empresa("Rito", "rito", "pass", "9307499959");
        Empresa empresa4 = new Empresa("Glub", "glub", "pass", "8394573890");
        udao.inserir(empresa1);
        udao.inserir(empresa2);
        udao.inserir(empresa3);
        udao.inserir(empresa4);
        
        //adicionando novos projetos ao sistema
        ProjetoDAO pdao = new ProjetoDAO();
        Projeto projeto1 = new Projeto(empresa1, "Desenvolvimento do Site da Empresa", "Estamos procurando por um profissional especializado em desenvolvimento web para criar um site para nossa empresa.", Nivel.PLENO);
        Projeto projeto2 = new Projeto(empresa1, "Desenvolvimento de app de localizacao", "Estamos procurando por um profissional back-end para ajudar na criacao de um app que ajuda a localizar o papa-leguas.", Nivel.SENIOR);
        Projeto projeto3 = new Projeto(empresa2, "iList", "A empresa esta a procura de um tester para testar nossos sistemas", Nivel.JUNIOR);
        Projeto projeto4 = new Projeto(empresa3, "Desenvolvimento de jogos", "O projeto busca um profissional capacitado e com experienca na area de jogos", Nivel.PLENO);
        pdao.inserir(projeto1);
        pdao.inserir(projeto2);
        pdao.inserir(projeto3);
        pdao.inserir(projeto4);
        
        //criando processos seletivos para os projetos 
        ProcessoSeletivo psel1 = new ProcessoSeletivo(Nivel.PLENO, projeto1, 2500);
        ProcessoSeletivo psel2 = new ProcessoSeletivo(Nivel.SENIOR, projeto2, 4000);
        ProcessoSeletivo psel3 = new ProcessoSeletivo(Nivel.JUNIOR, projeto3, 7000);
        ProcessoSeletivo psel4 = new ProcessoSeletivo(Nivel.PLENO, projeto4, 7600);
        
        psel2.inscrever(pessoa5);
        psel2.inscrever(pessoa4);
        psel2.inscrever(pessoa2);
        psel3.inscrever(pessoa1);
        psel3.inscrever(pessoa4);
        psel3.inscrever(pessoa2);
        psel1.inscrever(pessoa5);
        psel1.inscrever(pessoa3);    
        
        psel1.setStatus(false);                 //Fecha o processo seletivo para inativar novas aplicacoes
    }
}