public abstract class Conta  implements IConta{

    protected  static final int AGENCIA_PADRAO = 0001;
    protected static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    
    public Conta(Cliente cliente){

        this.cliente = cliente;
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.saldo = 0.0;

    }


    @Override
    public void sacar(double valor) {

        this.saldo -= valor;

    }

    @Override
    public void depositar(double valor) {

        this.saldo += valor;

    }

    @Override
    public void transferir(double valor, Conta contaDestino) {

       this.sacar(valor);
       contaDestino.depositar(valor);

    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Cliente: %s", this.cliente.getNome()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Número: %d", this.numero));
        System.out.println(String.format("Saldo: %f", this.saldo));
    }

    public int getNumero(){
        return this.numero;
    } 

    public Cliente getCliente(){
        return this.cliente;
    }

}
