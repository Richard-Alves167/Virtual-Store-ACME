import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Tarefa 1
        Cliente cliente1 = new Cliente("Richard");
        Cliente cliente2 = new Cliente("Taylan");
        Cliente cliente3 = new Cliente("Pedro");

        Produto produto1 = new Produto("Ariana Grande Albúm Sweetener", Path.of("src","Images","Ariana_Grande_Dangerous_Woman.jpg"),BigDecimal.valueOf(29.99));
        Produto produto2 = new Produto("Ariana Grande Albúm Dangerous Woman", Path.of("src","Images","Ariana_Grande_Dangerous_Woman.jpg"),BigDecimal.valueOf(49.99));
        Produto produto3 = new Produto("Ariana Grande Albúm Eternal Sunshine", Path.of("src","Images","Ariana_Grande_Dangerous_Woman.jpg"),BigDecimal.valueOf(59.99));
        Produto produto4 = new Produto("Ariana Grande Albúm Positions", Path.of("src","Images","Ariana_Grande_Dangerous_Woman.jpg"),BigDecimal.valueOf(69.99));

        LocalDateTime dataHoje = LocalDateTime.now();
        LocalDateTime dataOntem = dataHoje.minusDays(1);
        LocalDateTime dataMesPassado = LocalDateTime.of(dataHoje.getYear(),dataHoje.getMonth().minus(1),dataHoje.getDayOfMonth(), dataHoje.getHour(), dataHoje.getMinute());

        List<Pagamento> pagamentosEfetuados = new ArrayList<>();

        Pagamento pagamento1 = new Pagamento(cliente1,dataHoje);
        pagamento1.adicionarProduto(produto1);
        pagamento1.adicionarProduto(produto4);
        pagamento1.adicionarProduto(produto3);
        Pagamento pagamento2 = new Pagamento(cliente2,dataOntem);
        pagamento2.adicionarProduto(produto2);
        pagamento2.adicionarProduto(produto2);
        Pagamento pagamento3 = new Pagamento(cliente3,dataMesPassado);
        pagamento3.adicionarProduto(produto4);
        pagamento3.adicionarProduto(produto2);
        pagamento3.adicionarProduto(produto3);

        pagamentosEfetuados.add(pagamento3);
        pagamentosEfetuados.add(pagamento1);
        pagamentosEfetuados.add(pagamento2);

        //Tarefa 2
        pagamentosEfetuados.sort(Comparator.comparing(Pagamento::getDataCompra));
        pagamentosEfetuados.forEach(Pagamento::descricaoPagamento);

        //Tarefa 3
        pagamentosEfetuados.forEach(p -> {
            System.out.println("Valor Total do Pagamento: " + p.getValorTotalPagamento());
        });
    }
}