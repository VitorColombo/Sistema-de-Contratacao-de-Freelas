package devfreelas.model;


/**
 *
 * @author Vitor Nunes
 */
public interface DAO {//classe que define as funcoes para a manipulacao de dados
    
    public boolean inserir (Object obj);
    
    public boolean excluir (Object obj);
    
    public Object pesquisar (Object obj);
    
    public boolean editar (Object obj);
    
}
