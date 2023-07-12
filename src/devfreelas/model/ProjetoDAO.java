 package devfreelas.model;

import java.util.ArrayList;
import devfreelas.model.ProcessoSeletivo;

 public class ProjetoDAO implements DAO{
    
    static ArrayList<Projeto> projetos = new ArrayList<>();
    private static int nextId = 1;
    
@Override
public boolean excluir(Object obj) {
    if (obj != null && obj instanceof Projeto) {
        Projeto projeto = (Projeto) obj;
        Empresa empresa = projeto.getEmpresa();

        if (projetos.remove(projeto)) {
            empresa.removeProjeto(projeto);
            return true;
        }
    }
    return false;
}
    @Override
    public Object pesquisar(Object obj) {               //pesquisar um projeto
       if (obj != null && obj instanceof String) {
           String nomeProjeto = (String) obj; 
           for (int i = 0; i < projetos.size(); i++) {
               Projeto aux = projetos.get(i);
               if (nomeProjeto.equals(aux.getNome())) {
                   return aux;
               }
           }
       }
       return null;
   }

    public Projeto pesquisarPeloID(int id) {            //pesquisar projeto pelo ID
        if (id > 0) {
            for (Projeto projeto : projetos) {
                if (projeto instanceof Projeto && projeto.getId() == id) {
                    return projeto;
                }
            }
        }
        Projeto projeto = null;
        return projeto;
    }

    @Override
    public boolean editar(Object obj) {                 //editar projeto
        if (obj !=null){ 
           Projeto pNovo = (Projeto) obj;
           Projeto pVelho = (Projeto) pesquisar(pNovo);
           pNovo.setId(pVelho.getId());
           int index = projetos.indexOf(pVelho);
           projetos.set(index, pNovo);
           return true;
        }
        return false;
    }
    
    public void listar(){                               //listar projetos
        for (Projeto projeto : projetos) {
            if (projeto instanceof Projeto) {
                Projeto p = (Projeto) projeto;
                System.out.println(projeto);
            }
        }
    }
    
    public void listarProjeto(Usuario u){              //listar projetos por empresa
        for (Projeto projeto : projetos) {
            if (projeto instanceof Projeto) {
                Projeto p = (Projeto) projeto;
                if(p.getEmpresa().getId() == u.getId())
                {
                    System.out.println(p.getId() + "-" + projeto);
                }    
            }
        }
    }
    
    public void listarPseletivo() {                     //listar processo seletivo
        for (Projeto projeto : projetos) {
            if (projeto.getProcessoSeletivo() != null) {
                ProcessoSeletivo pSel = projeto.getProcessoSeletivo();
                System.out.println(pSel + "\n");
            }
        }
    }
    
    
        @Override
    public boolean inserir(Object obj) {                //insere novo projeto
        if (obj instanceof Projeto) {
            Projeto nProj = (Projeto) obj;
            nProj.setId(nextId++);
            projetos.add(nProj);
            Empresa emp = nProj.getEmpresa();
            emp.setProjetos(nProj);
            return true;
        }
        return false;
    }

}
