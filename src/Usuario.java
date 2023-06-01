
import java.util.Objects;

public class Usuario {
    private String user;
    private String senha;
    private int id;
    
    public Usuario(String user, String senha) {
        this.user = user;
        this.senha = senha;
    }
    
    public Usuario(){
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getUser() {
        return user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario { ID: "+ id + "nome de usuario: " + user + ", Senha : " + senha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.user);
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
        final Usuario other = (Usuario) obj;
        
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        return Objects.equals(this.user, other.user);
    }
    
//    public Usuario login(String user, String senha) {;
//        for (Usuario u : cadastro) {
//            if (u.jExists(username, password)) {
//                System.out.println(u.getUser());
//                return u;
//            }
//        }
//        return null;
//    }    
    
}
