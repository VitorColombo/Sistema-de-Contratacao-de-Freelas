package devfreelas.view;

import javax.swing.JOptionPane;

public class Console {
    private static Console instance;

    private Console() {
    }

    public static Console getInstance() {           // método estático que retorna uma instância única da classe console    
        if (instance == null) {
            instance = new Console();
        }
        return instance;
    }

    public String lerDados(String mensagem) {
        String s = null;
        do {
            s = JOptionPane.showInputDialog(null, mensagem);
            if (s == null)
                return null;                        //para que o cancel e o fechar nao abortem a execucao
        } while (s.isEmpty());
        return s;
    }

    public void mostrarDados(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public static void initialize() {               //É um método estático que inicializa a instância única da classe 
        instance = getInstance();
    }
}

