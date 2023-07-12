/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devfreelas.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Empresa extends Usuario{
    private String cnpj;
    private String nome;
    private ArrayList<PessoaFisica> funcionarios = new ArrayList<>();
    private ArrayList<Projeto> projetosEmp = new ArrayList<>();
    
    
    public Empresa(String nome, String user, String senha, String cnpj) {
        this.setUser(user);
        this.setSenha(senha);
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<PessoaFisica> getFuncionarios() {
        return funcionarios;
    }
    
    public void setFuncionarios(PessoaFisica func) {
        funcionarios.add(func);
    }

    public ArrayList<Projeto> getProjetosEmp() {
        return projetosEmp;
    }

    public void setProjetos(Projeto projeto) {
        projetosEmp.add(projeto);
    }
    
    public void removeProjeto(Projeto projeto) {
        projetosEmp.removeIf(p -> p.getId() == projeto.getId());
    }

    
    public int getNProj() {                                     //funcao que retorna o numero de projetos que a empresa possui
       return projetosEmp.size();
    }

    public int getNFunc() {                                     //funcao que retorna o numero de funcionarios que a empresa possui
       return funcionarios.size();
    }    
    
    public void listarFuncionarios() {                          //funcao que lista os funcionarios da empresa
        System.out.println("Funcionarios da empresa:");
        for (PessoaFisica pf : funcionarios) {
            System.out.println(pf.getId() + " - " + pf.getNome());
        }
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.cnpj);
        hash = 59 * hash + Objects.hashCode(this.nome);
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
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }
    
    @Override
    public String toString() {
        return "ID: " + getId() + "\nNome: " + nome + "\nCNPJ: " + cnpj + "\nEsta empresa possui " + getNFunc() + " funcionarios e " + getNProj() + " projetos.\n"; 
    }
    
}
