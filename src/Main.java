import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
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
        pagamentosEfetuados.forEach(pagamento -> {
            System.out.println("Valor Total do Pagamento: R$ " + pagamento.getValorTotalPagamento());
        });

        //Tarefa 4
        Stream<Double> valoresDosPagamentos = pagamentosEfetuados.stream().map(pagamento -> {
            return pagamento.getValorTotalPagamento();
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
        Map<String, List<Produto>> mapaClienteProdutos = new HashMap<>();
        pagamentosEfetuados.forEach(pagamento -> {
            mapaClienteProdutos.merge(pagamento.getCliente().getNome(), new ArrayList<>(pagamento.getProdutos()), (existente, novo) -> {
                existente.addAll(novo);
                return existente;
            });
        });
        //Tarefa 7
        Pagamento maior = pagamentosEfetuados.stream().max(Comparator.comparing(Pagamento::getValorTotalPagamento)).orElseThrow();
        System.out.println("Cliente que mais gastou: " + maior.getCliente()+" e o preço total de R$ " + String.format("%.2f",maior.getValorTotalPagamento()));

        //Tarefa 8
//        Map<Integer, BigDecimal> porMes = pagamentosEfetuados.stream()
//                .collect(Collectors.groupingBy(pagamento -> pagamento.getDataCompra().getMonthValue(),
//                        Collectors.mapping(Pagamento::getValorTotalPagamento, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        Map<Integer, Double> porMes = pagamentosEfetuados.stream()
                .filter(pagamento -> pagamento.getDataCompra() != null)
                .collect(Collectors.groupingBy(
                        pagamento -> pagamento.getDataCompra().getMonthValue(),
                        Collectors.summingDouble(pagamento -> Optional.ofNullable(pagamento.getValorTotalPagamento()).orElse(0.0))
                ));

        System.out.println("Faturamento por mês:");
        porMes.forEach((mes, total) -> System.out.println("Mês "+mes+": R$ " + String.format("%.2f",total)));
        //Tarefa 9
        Assinatura assinatura1 = new Assinatura(cliente1, BigDecimal.valueOf(99.98), LocalDate.of(2025, 1, 4));
        Assinatura assinatura2 = new Assinatura(cliente2, BigDecimal.valueOf(99.98), LocalDate.of(2023, 3, 4), LocalDate.of(2024, 7, 4));
        Assinatura assinatura3 = new Assinatura(cliente3, BigDecimal.valueOf(99.98), LocalDate.of(2024, 5, 4), LocalDate.of(2025, 2, 4));

        List<Assinatura> assinaturas = new ArrayList<Assinatura>();
        assinaturas.add(assinatura2);
        assinaturas.add(assinatura1);
        assinaturas.add(assinatura3);

        //Tarefa 10
        assinaturas.forEach(assinatura -> {
            Optional<LocalDate> assinaturaEncerrada = Optional.ofNullable(assinatura.getEnd());
            if (assinaturaEncerrada.isEmpty()) {
                System.out.println("Assinatura de " + assinatura.getCliente().getNome() +" ainda em andamento, contagem de tempo em meses até o momento: " + assinatura.getPeriodoEmMeses());
            }
        });

        //Tarefa 11
        assinaturas.forEach(assinatura -> {
            Optional<LocalDate> assinaturaEncerrada = Optional.ofNullable(assinatura.getEnd());
            assinaturaEncerrada.ifPresentOrElse(d -> {
                System.out.println("Assinatura de " + assinatura.getCliente().getNome() + " finalizada, tempo em meses da assinatura: " + assinatura.getPeriodoEmMeses());
            }, () -> {
                System.out.println("Assinatura de " + assinatura.getCliente().getNome() + " ainda em andamento, contagem de tempo em meses até o momento: " + assinatura.getPeriodoEmMeses());
            });
        });

        //Tarefa 12
        assinaturas.forEach(assinatura -> {
            System.out.println("Pagamento total dessa assinatura: R$ " + assinatura.getPagamentoTotal());
        });
    }
}