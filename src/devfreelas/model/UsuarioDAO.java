package devfreelas.model;

import java.util.ArrayList;

public class UsuarioDAO implements DAO {

    private static ArrayList<Usuario> cadastro = new ArrayList<>();
    private static int nextId = 1;

    @Override
    public boolean excluir(Object obj) {
        if (obj != null && obj instanceof Usuario) {
            Usuario u = (Usuario) obj;
            return cadastro.remove(u);
        }
        return false;
    }

    @Override
    public Object pesquisar(Object obj) {
        if (obj != null && obj instanceof String) {
            String nome = (String) obj;
            for (Usuario usuario : cadastro) {
                if (nome.equals(usuario.getUser())) {
                    return usuario;
                }
            }
        } else if (obj != null && obj instanceof Usuario) {
            for (Usuario usuario : cadastro) {
                if (obj.equals(usuario)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    public Usuario pesquisarPeloID(int id) {
        if (id > 0) {
            for (Usuario usuario : cadastro) {
                if (usuario.getId() == id) {
                    return usuario;
                }
            }
        }
        return null;
    }

    @Override
    public boolean editar(Object obj) {
        if (obj != null && obj instanceof Usuario) {
            Usuario uNovo = (Usuario) obj;
            Usuario uVelho = pesquisarPeloID(uNovo.getId());
            if (uVelho != null) {
                uNovo.setId(uVelho.getId());
                int index = cadastro.indexOf(uVelho);
                cadastro.set(index, uNovo);
                return true;
            }
        }
        return false;
    }

    public void listar() {
        for (Usuario usuario : cadastro) {
            System.out.println(usuario);
        }
    }

    public void listarDev() {
        for (Usuario usuario : cadastro) {
            if (usuario instanceof PessoaFisica) {
                PessoaFisica pf = (PessoaFisica) usuario;
                System.out.println("ID: " + pf.getId() + " Nome: " + pf.getNome());
            }
        }
    }

    public void listarEmp() {
        for (Usuario usuario : cadastro) {
            if (usuario instanceof Empresa) {
                Empresa e = (Empresa) usuario;
                System.out.println("ID: " + e.getId() + " Nome: " + e.getNome());
            }
        }
    }

    @Override
    public boolean inserir(Object obj) {
        if (obj instanceof Usuario) {
            Usuario nUser = (Usuario) obj;
            if (usuarioDisponivel(nUser.getUser())) {
                nUser.setId(nextId++);
                return cadastro.add(nUser);
            }
        }
        return false;
    }

    private boolean usuarioDisponivel(String username) {
        for (Usuario usuario : cadastro) {
            if (usuario.getUser().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
