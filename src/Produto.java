import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

public class Produto {
    private String nome;
    private Path file;
    private BigDecimal preco;
    private int quantidadeVendido;

    public Produto(String nome, Path file, BigDecimal preco) {
        this.nome = nome;
        this.file = file;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidadeVendido() {
        return quantidadeVendido;
    }

    public void produtoVendido() {
        quantidadeVendido++;
    }

    public void quantidadeVendidaDoProduto() {
        System.out.println("-> " + nome + " : " + quantidadeVendido);
    }

}
