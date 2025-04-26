import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //Tarefa 1
        Cliente cliente1 = new Cliente("Richard");
        Cliente cliente2 = new Cliente("Taylan");
        Cliente cliente3 = new Cliente("Pedro");

        Produto produto1 = new Produto("Ariana Grande Albúm Sweetener", Path.of("src", "Images", "Ariana_Grande_Dangerous_Woman.jpg"), BigDecimal.valueOf(29.99));
        Produto produto2 = new Produto("Ariana Grande Albúm Dangerous Woman", Path.of("src", "Images", "Ariana_Grande_Dangerous_Woman.jpg"), BigDecimal.valueOf(49.99));
        Produto produto3 = new Produto("Ariana Grande Albúm Eternal Sunshine", Path.of("src", "Images", "Ariana_Grande_Dangerous_Woman.jpg"), BigDecimal.valueOf(59.99));
        Produto produto4 = new Produto("Ariana Grande Albúm Positions", Path.of("src", "Images", "Ariana_Grande_Dangerous_Woman.jpg"), BigDecimal.valueOf(69.99));

        LocalDateTime dataHoje = LocalDateTime.now();
        LocalDateTime dataOntem = dataHoje.minusDays(1);
        LocalDateTime dataMesPassado = LocalDateTime.of(dataHoje.getYear(), dataHoje.getMonth().minus(1), dataHoje.getDayOfMonth(), dataHoje.getHour(), dataHoje.getMinute());

        List<Pagamento> pagamentosEfetuados = new ArrayList<>();

        Pagamento pagamento1 = new Pagamento(cliente1, dataHoje);
        pagamento1.adicionarProduto(produto1);
        pagamento1.adicionarProduto(produto4);
        pagamento1.adicionarProduto(produto3);
        Pagamento pagamento2 = new Pagamento(cliente2, dataOntem);
        pagamento2.adicionarProduto(produto2);
        pagamento2.adicionarProduto(produto2);
        Pagamento pagamento3 = new Pagamento(cliente3, dataMesPassado);
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
            System.out.println("Valor Total do Pagamento: R$ " + p.getValorTotalPagamento());
        });

        //Tarefa 4
        Stream<Double> valoresDosPagamentos = pagamentosEfetuados.stream().map(p -> {
            return p.getValorTotalPagamento();
        });
        double valorSomadoDosPagamentos = valoresDosPagamentos.reduce(0.0, (c1, c2) -> {
            return c1 + c2;
        });
        System.out.println("Valor total arrecadado pela Loja: R$ " + Math.floor(valorSomadoDosPagamentos * 100) / 100.0);

        //Tarefa 5
        System.out.println("Quantidade vendida de cada produto");
        produto1.quantidadeVendidaDoProduto();
        produto2.quantidadeVendidaDoProduto();
        produto3.quantidadeVendidaDoProduto();
        produto4.quantidadeVendidaDoProduto();

        //Tarefa 6

        //Tarefa 7

        //Tarefa 8

        //Tarefa 9
        Assinatura assinatura1 = new Assinatura(cliente1, BigDecimal.valueOf(99.98), LocalDate.of(2025, 1, 4));
        Assinatura assinatura2 = new Assinatura(cliente2, BigDecimal.valueOf(99.98), LocalDate.of(2023, 3, 4), LocalDate.of(2024, 7, 4));
        Assinatura assinatura3 = new Assinatura(cliente3, BigDecimal.valueOf(99.98), LocalDate.of(2024, 5, 4), LocalDate.of(2025, 2, 4));

        List<Assinatura> assinaturas = new ArrayList<Assinatura>();
        assinaturas.add(assinatura2);
        assinaturas.add(assinatura1);
        assinaturas.add(assinatura3);

        //Tarefa 10
        assinaturas.forEach(a -> {
            Optional<LocalDate> assinaturaEncerrada = Optional.ofNullable(a.getEnd());
            if (assinaturaEncerrada.isEmpty()) {
                System.out.println("Assinatura ainda em andamento, contagem de tempo em meses até o momento: " + a.getPeriodoEmMeses());
            }
        });

        //Tarefa 11
        assinaturas.forEach(a -> {
            Optional<LocalDate> assinaturaEncerrada = Optional.ofNullable(a.getEnd());
            assinaturaEncerrada.ifPresentOrElse(d -> {
                System.out.println("Assinatura finalizada, tempo em meses da assinatura: " + a.getPeriodoEmMeses());
            }, () -> {
                System.out.println("Assinatura ainda em andamento, contagem de tempo em meses até o momento: " + a.getPeriodoEmMeses());
            });
        });
    }
}