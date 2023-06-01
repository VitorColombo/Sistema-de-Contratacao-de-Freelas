
import java.util.ArrayList;
import java.util.Iterator;

public class UsuarioDAO implements DAO{
    
    static ArrayList<Usuario> cadastro = new ArrayList<>();
    private static int nextId = 1;
    
    @Override
    public boolean excluir(Object obj) {
        if (obj != null) {
            Usuario u = (Usuario) obj;
            cadastro.remove(u);
            return true;
        }
        return false;
    }

    @Override
    //Em obj está o critério de pesquisa
    //Em obj vai estar o nome da Usuario
    public Object pesquisar(Object obj) {
        if (obj !=null){
            Usuario u = (Usuario) obj;
            String nome = (String) u.getUser();
            for (int i =0; i< cadastro.size(); i++){
                Usuario aux = (Usuario) cadastro.get(i);
                if (nome.equals(aux.getUser())){
                    return aux;
                }
            }
        }
        return null;
    }

    @Override
    //Em obj está a Usuario com os dados novos exceto o nome
    public boolean editar(Object obj) {
        if (obj !=null){ 
           Usuario uNovo = (Usuario) obj;
           Usuario uVelho = (Usuario) pesquisar(uNovo);
           uNovo.setId(uVelho.getId());
           int index = cadastro.indexOf(uVelho);
           cadastro.set(index, uNovo);
           return true;
        }
        return false;
    }
        
    public void listar(){
        Iterator it = cadastro.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }   
    }
    
    @Override
    public boolean inserir(Object obj) {
        Iterator it = cadastro.iterator();
        Usuario u = (Usuario) obj;
        if (u != null) {
            while(it.hasNext()){
                Usuario testUser = (Usuario) it.next();
                if (testUser.getUser().equals(u.getUser()))
                    return false;
            }
            Usuario nUser = new Usuario(u.getUser(), u.getSenha());
            cadastro.add(nUser);
            nUser.setId(nextId++);
            return true;
        }
        return false;
    }
    
    public Usuario validar(String username, String senha) {
        for (Usuario usuario : cadastro) {
            if (usuario.getUser().equals(username) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null; // Retornar null se as credenciais forem inválidas
    }
    
    
}
