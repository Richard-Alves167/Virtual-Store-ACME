import java.util.Date;
import java.util.List;

public class Pagamento {
    private Cliente cliente;
    private Date dataCompra;
    private List<Produto> produtos;

    public Pagamento(Cliente cliente, Date dataCompra) {
        this.cliente = cliente;
        this.dataCompra = dataCompra;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }
}
