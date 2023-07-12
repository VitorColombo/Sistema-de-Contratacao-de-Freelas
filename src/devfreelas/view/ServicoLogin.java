package devfreelas.view;

    import devfreelas.control.ControleValidacao;
    import devfreelas.model.Usuario;
    import devfreelas.model.UsuarioDAO;
import javax.swing.JPasswordField;

    public class ServicoLogin {
        private static Console console;
        ControleValidacao valida = new ControleValidacao();
            private JPasswordField passwordField;


        public ServicoLogin() {
            console = Console.getInstance();
            passwordField = new JPasswordField();

        }

        public Usuario logar() {                                                    //Esta funcao vai obter e checar se os dados fornecidos 
            UsuarioDAO udao = new UsuarioDAO();                                     //batem com algum dos dados cadastrados em algum usuario
            Usuario u = null;                                                        
            boolean logou = false;

            while (!logou) {                                                        //loop que repetira enquanto o usuario nao conseguir logar
                String username = console.lerDados("Nome de usuário: ");     //leitura de usuario e senha com a classe console
                String senha =  console.lerDados("Senha: ");
                passwordField.setText("");
                
                u = valida.validarLogin(username, senha);                           //chama a funcao validar login da classe de validacao  
                if (u != null) {                                                    //caso os dados batam sai do loop e retorna o usuario correspondente
                    console.mostrarDados("Você logou como: " + username);           //usa a classe console para dar feedback do processo
                    logou = true;
                } else {                                                             //caso nao esteja presente retorna nulo
                    console.mostrarDados("Usuário ou senha incorretos!");
                    return null;
                }
            }
        return u;
        }
    }
