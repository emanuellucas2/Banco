
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nome;
    private List<Conta> contas;
    
    Banco(){
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta){
    
        this.contas.add(conta);

    }  
    
    public Conta obterContaPorNumero(int numero){
    
        for (Conta conta : contas) {
            if(conta.getNumero() == numero){
                return conta;
            }
        }
    
        return null;
    }
    
    public String getNome(){
        return this.nome;
    }    
    
    public void setNome(String nome){
        this.nome = nome;
    }  

}
