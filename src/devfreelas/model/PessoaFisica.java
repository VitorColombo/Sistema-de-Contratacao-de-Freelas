/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devfreelas.model;

import devfreelas.view.Nivel;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class PessoaFisica extends Usuario{
    private String cpf;
    private String nome;
    private Nivel nivel;
    private ArrayList<ProcessoSeletivo> processosInscritos = new ArrayList<>();
    private ArrayList<Projeto> projetosAtivos = new ArrayList<>();


    public PessoaFisica(String nome, String user, String senha, String cpf, Nivel nivel) {
        this.setUser(user);
        this.setSenha(senha);
        this.cpf = cpf;
        this.nome = nome;
        this.nivel = nivel;
    }
    
    public PessoaFisica(String nome) {
    }

    public PessoaFisica() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    public ArrayList<ProcessoSeletivo> getProcessosInscritos() {
        return processosInscritos;
    }

    public void setProcessosInscritos(ArrayList<ProcessoSeletivo> processosInscritos) {
        this.processosInscritos = processosInscritos;
    }
    
    public void adicionarInscricao(ProcessoSeletivo processo) {                 //funcao que adiciona uma inscricao no array de processos seletivos
        processosInscritos.add(processo);
    }
    
    public void removerInscricao(ProcessoSeletivo processo) {                   //funcao que remove inscricao no array de processos seletivos
        processosInscritos.remove(processo);
    }
    
    public void listarInscricoes() {                                            //funcao que lista os processos seletivos em que a pf esta inscrito
        System.out.println("Processos Seletivos em que voce esta inscrito:");
        for (ProcessoSeletivo processo : processosInscritos) {
            System.out.println(processo);
        }
    }

    public void listarProjetos() {                                              //funcao que lista os projetos em que a pf foi selecionada
        if(projetosAtivos.isEmpty()){
            System.out.println("Voce nao possui projetos no momento\n");
        }
        else{
            System.out.println("Projetos em que voce esta trabalhando:");
            for (Projeto proj : projetosAtivos) {
                System.out.println(proj.getNome());
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.cpf);
        hash = 89 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PessoaFisica other = (PessoaFisica) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }
    
    
    @Override
    public String toString() {
        return "ID: " +  getId() + "\nNome: " + nome + "\nCPF: " + cpf + "\nNivel: " + nivel;
    }
    
}

