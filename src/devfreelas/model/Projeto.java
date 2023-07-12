/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devfreelas.model;

import devfreelas.view.Nivel;
import java.util.ArrayList;
import java.util.Objects;

public class Projeto{
    private String nome;
    private Empresa empresa;
    private String descricao;
    private ArrayList<PessoaFisica> selecionados = new ArrayList<>();           //array list que vai guardar os devs que foram contratados
    private ProcessoSeletivo processoSeletivo;                                  //variavel que vai guardar o processo seletivo do projeto
    private int id;
    private Nivel requisito;                                                    //Utiliza o ENUM como um requisito para inscricao na vaga
    
    public Projeto(Empresa empresa, String nome, String descricao, Nivel requisito){
        this.requisito = requisito;
        this.nome = nome;
        this.descricao = descricao;
        this.empresa = empresa;
        this.selecionados = new ArrayList<>();
    }
   
    public Projeto() {
        this.nome = null;
        this.descricao = null;
        this.empresa = null;
        this.selecionados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Nivel getRequisito() {
        return requisito;
    }

    public void setRequisito(Nivel requisito) {
        this.requisito = requisito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<PessoaFisica> getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(PessoaFisica selecionados) {                //funcao que adiciona o selecionado ao arrayList do projeto
        this.selecionados.add(selecionados);                              //e ao quadro de funcionarios da empresa
    }
    
    public int getNFunc() {                                                 //retorna o numero de funcionarios contratados para o projeto
       return selecionados.size();
    }   

    public ProcessoSeletivo getProcessoSeletivo() {
        return processoSeletivo;
    }

    public void setProcessoSeletivo(ProcessoSeletivo processoSeletivo) {
        this.processoSeletivo = processoSeletivo;
    }

    public void encerraProcessoSeletivo(){                                  //muda o status para falso(nao aceita novas inscricoes)
        this.processoSeletivo.setStatus(false);
    }
    
    public void reabreProcessoSeletivo(){                                   //muda o status para verdadeiro (aceita novas inscricoes)
        this.processoSeletivo.setStatus(true);
    }
    
    public void iniciarProcessoSeletivo(Nivel requisito, double salario){   //iniciar o processo seletivo para contratacao de um dev para o projeto
        this.requisito = requisito;
        this.processoSeletivo = new ProcessoSeletivo(requisito, this, salario);
        selecionados.add(processoSeletivo.contratar());                     //adiciona o selecionado ao arraylist de contratados
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.nome);
        hash = 13 * hash + Objects.hashCode(this.empresa);
        hash = 13 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Projeto other = (Projeto) obj;
        return this.id == other.id;
    }
    
    @Override                               //printando dados do projeto
    public String toString() {
        return nome + " na empresa " + empresa.getNome() + "\nDescricao: " + descricao + "\n" + "Numero de funcionarios: " + getNFunc() + "\n" ;
    }
}


