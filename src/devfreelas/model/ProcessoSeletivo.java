
package devfreelas.model;

import devfreelas.control.ControleValidacao;
import devfreelas.view.Nivel;
import java.util.ArrayList;
import java.util.Scanner;


public class ProcessoSeletivo {
    private Nivel requisito;
    private ArrayList<PessoaFisica> candidatos = new ArrayList<>();
    private Empresa empresa;
    private Projeto projeto;
    private double salario;
    private boolean status;
    
    public ProcessoSeletivo(Nivel requisito, Projeto projeto, double salario) {
        ControleValidacao controleValidacao = new ControleValidacao();
        if(!controleValidacao.validaProcessoSeletivo(projeto)){
            throw new IllegalStateException("Processo seletivo já existe para esse projeto");
        }
        this.salario = salario;
        this.requisito = requisito;
        this.empresa = projeto.getEmpresa();
        this.projeto = projeto;
        projeto.setProcessoSeletivo(this);
        this.status = true;                                                       //abre o processo
    }

    public ProcessoSeletivo() {
       this.salario = 00;
       this.empresa = null;
       this.empresa = null;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Nivel getRequisito() {
        return requisito;
    }

    public void setRequisito(Nivel requisito) {
        this.requisito = requisito;
    }
    
    public boolean isStatus() {                         //checa o status do processo seletivo
        return status;
    }

    public void setStatus(Boolean status) {             //altera estado do processo seletivo
        this.status = status;
    }
    
    public Projeto getProjeto() {
        return projeto;
    }

    //criar validação para ver se o processo já existe
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public void listarCandidatos(){                                             //listagem de candidatos do processo seletivo
        System.out.println("Lista de candidatos para vaga");
        if(this.status == true)
        {
            for (PessoaFisica pf : candidatos) {
                if (pf instanceof PessoaFisica) {
                    PessoaFisica p = (PessoaFisica) pf;
                    System.out.println(pf.getId() + " - " + pf.getNome());
                }
            }
        }
        else  
        {
            System.out.println("Processo seletivo fechado!");
        }
    }
    
    public Boolean inscrever(PessoaFisica inscrito){                        //para uso da PF se inscrever no processo seletivo
        if (status == true){
            if (!candidatos.contains(inscrito)){
                if (inscrito.getNivel().compareTo(requisito) >= 0){
                    candidatos.add(inscrito);
                    inscrito.adicionarInscricao(this);
                    System.out.println("Inscricao realizada com sucesso!");
                    return true;
                }
                else{
                    System.out.println("Desculpe. Voce nao possui os pre-requisitos para esta vaga!");
                    return false;
                }
            }
            else if(candidatos.contains(inscrito)){
                System.out.println("Voce ja aplicou para esta vaga!");
                return false;
            }
        }    
        System.out.println("Este processo seletivo esta fechado no momento");
        return false;
    }
    
    public boolean removerCandidato(PessoaFisica inscrito){
        if(candidatos.contains(inscrito)){
            candidatos.remove(inscrito);
            inscrito.removerInscricao(this);
            return true;
        }
        return false;
    }

    
    public PessoaFisica contratar(){                                      //para uso da empresa escolher um dos candidatos para o Processo seletivo
        Scanner ler = new Scanner(System.in);
        UsuarioDAO udao = new UsuarioDAO();
        PessoaFisica selecionado = new PessoaFisica();
        int id;
        
        if (candidatos.isEmpty()){
            System.out.println("Nenhum candidato no momento!");
            return null;
        }
        else{
            for(PessoaFisica pf : candidatos){
                System.out.println(pf);
                System.out.println("-------");
            }
            System.out.println("Selecione os contratados pelo ID: ");
            id = ler.nextInt();
            selecionado = (PessoaFisica) udao.pesquisarPeloID(id);
            if (selecionado != null) {
                empresa.setFuncionarios(selecionado);
                projeto.setSelecionados(selecionado);
                candidatos.remove(selecionado);
            } else {
                System.out.println("ID inválido!");
            }        
        }
    return selecionado;
    }

    
    
    @Override
    public String toString() {
        if(isStatus() == true){
            return "COD: " + projeto.getId() + " - Vaga de nivel " + requisito + " na empresa " + empresa.getNome() + " - Salario:  " + salario + "  --- STATUS: ABERTO" + "\n" + projeto;
        }else
            return "COD: " + projeto.getId() + " - Vaga de nivel " + requisito + " na empresa " + empresa.getNome() + " - Salario:  " + salario + "  --- STATUS: FECHADO" + "\n" + projeto;
    }
}
