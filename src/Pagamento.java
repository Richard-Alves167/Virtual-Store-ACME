import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pagamento {
    private DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Cliente cliente;
    private LocalDateTime dataCompra;
    private List<Produto> produtos;

    public Pagamento(Cliente cliente, LocalDateTime dataCompra) {
        this.cliente = cliente;
        this.dataCompra = dataCompra;
        this.produtos = new ArrayList<Produto>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void descricaoPagamento() {
        System.out.println("===== Pagamento =====");
        System.out.println("Cliente: " + cliente);
        System.out.println("Data do Pagamento: " + dataCompra.format(formatoData));
        System.out.println("* Produtos *");
        produtos.forEach(pd -> {
            System.out.println("-> Nome: " + pd.getNome() + " Preço: " + pd.getPreco());
        });
    }

}
