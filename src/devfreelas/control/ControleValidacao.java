/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devfreelas.control;

import devfreelas.model.ProcessoSeletivo;
import devfreelas.model.Projeto;
import devfreelas.model.Usuario;
import devfreelas.model.UsuarioDAO;
        
public class ControleValidacao {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Usuario usuario = new Usuario();
    
                                                                    //funcao para a validacao do login, confere se o usuario e a senha estao corretos
    public Usuario validarLogin(String username, String senha) {
        usuario = (Usuario) usuarioDAO.pesquisar(username);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;                                                // retorna nulo se os dados forem invalidos
    }
                                                                    //funcao para validar criacao de processo seletivo
    public Boolean validaProcessoSeletivo(Projeto projeto) {    
        return projeto.getProcessoSeletivo() == null;               //retorna falso se o projeto ja possuir um processo seletivo e true se ainda nao possuir
    }
    
    public Boolean statusProcesso(Projeto projeto){                 //funcao que informa se o processo esta aberto ou fechado no momento
        ProcessoSeletivo processo = projeto.getProcessoSeletivo();
        return processo.isStatus();
    }
   
}
