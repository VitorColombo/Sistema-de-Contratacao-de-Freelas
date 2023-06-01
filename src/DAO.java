
/**
 *
 * @author Vitor Nunes
 */
public interface DAO {
    
    public boolean inserir (Object obj);
    
    public boolean excluir (Object obj);
    
    public Object pesquisar (Object obj);
    
    public boolean editar (Object obj);
    
}
