import java.util.Scanner;

public class ServicoLogin {
    private Scanner scanner;
    
    public ServicoLogin() {
        scanner = new Scanner(System.in);
    }
    
    public Usuario logar() {
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = null;
        boolean logou = false;
        
        while(!logou){
        System.out.print("Nome de usuario: ");
        String username = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        u = udao.validar(username, senha);            
        if ( u != null) {
            System.out.println("Voce Logou!");
            logou = true;
        } 
        else
            System.out.println("Usuario ou senha incorretos.");
    }
    return u;
    }
    
    
}