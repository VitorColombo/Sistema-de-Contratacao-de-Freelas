
public class Principal {

  public static void main(String[] args) {
        UsuarioDAO udao = new UsuarioDAO();
        ServicoLogin dadosLogin = new ServicoLogin();
        
        //teste cadastrar
        PessoaFisica pf1 = new PessoaFisica("Maria", "choco");
        udao.inserir(pf1);
        Usuario u2 = new Usuario("Pedro", "late");
        udao.inserir(u2);
        udao.inserir(new Usuario("Luiz", "cao"));
        Empresa emp1 = new Empresa ("Orcorp", "ricos");
        udao.inserir(emp1);
        System.out.println("--------TESTE DE INSERCAO----------");          

        udao.listar();

        //funçao excluir
        udao.excluir(u2);//excluindo pedro
        System.out.println("------TESTE DE EXCLUSAO----------");
        udao.listar();
        
        //adding user ja existente
        boolean cadastrou = udao.inserir(pf1);
        System.out.println("--------TESTE DE INSERCAO DE USER JA EXISTENTE--------");  
        System.out.println("foi cadastrado: " + cadastrou);
        udao.listar();
        
        
        //teste de edicao usuarios apenas poderao mudar suas senhas
        pf1.setSenha("marica123");
        udao.editar(pf1);
        System.out.println("--------TESTE DE EDICAO--------");  
        udao.listar();
        

        //teste de pesquisa pelo nome  
        System.out.println("--------TESTE DE PESQUISA--------");  
        udao.listar();
        Usuario u = (Usuario) udao.pesquisar(u2);
        System.out.println("Achou = " + u);

        //teste login
        Usuario logado = dadosLogin.logar();
        System.out.println(logado);

  }
}
