import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Pagamento {
    private DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Cliente cliente;
    private LocalDateTime dataCompra;
    private List<Produto> produtos;
    private double valorTotalPagamento;

    public Pagamento(Cliente cliente, LocalDateTime dataCompra) {
        this.cliente = cliente;
        this.dataCompra = dataCompra;
        this.produtos = new ArrayList<Produto>();
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

    public double getValorTotalPagamento() {
        return valorTotalPagamento;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        produto.produtoVendido();
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        valorTotalPagamento = 0;
        produtos.forEach(product -> {
            Optional<BigDecimal> precoProduto = Optional.of(product.getPreco());
            precoProduto.ifPresent(p -> {valorTotalPagamento += precoProduto.get().doubleValue();});
        });
    }

    public void descricaoPagamento() {
        System.out.println("===== Pagamento =====");
        System.out.println("Cliente: " + cliente);
        System.out.println("Data do Pagamento: " + dataCompra.format(formatoData));
        System.out.println("* Produtos *");
        produtos.forEach(p -> {
            System.out.println("-> Nome: " + p.getNome() + " Preço: R$" + p.getPreco());
        });
    }

}
