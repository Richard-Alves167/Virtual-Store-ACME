import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Assinatura {
    private Cliente cliente;
    private BigDecimal mensalidade;
    private LocalDate begin;
    private LocalDate end;
    private long periodoEmMeses;
    private double pagamentoTotal;

    public Assinatura(Cliente cliente, BigDecimal mensalidade, LocalDate begin) {
        this.cliente = cliente;
        this.mensalidade = mensalidade;
        this.begin = begin;
    }

    public Assinatura(Cliente cliente, BigDecimal mensalidade, LocalDate begin, LocalDate end) {
        this.cliente = cliente;
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.end = end;
    }

    public Cliente getCliente() {return cliente;}


    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public long getPeriodoEmMeses() {
        tempoDeAssinatura();
        return periodoEmMeses;
    }

    public double getPagamentoTotal() {
        calcularValorPagoTotal();
        return pagamentoTotal;
    }

    private void tempoDeAssinatura() {
        Optional<LocalDate> dataEncerrada = Optional.ofNullable(end);
        dataEncerrada.ifPresentOrElse(a -> {
            periodoEmMeses = ChronoUnit.MONTHS.between(begin, end);
        }, () -> {
            periodoEmMeses = ChronoUnit.MONTHS.between(begin, LocalDate.now());
        });
    }

    private void calcularValorPagoTotal() {
        pagamentoTotal = mensalidade.doubleValue() * periodoEmMeses;
    }
}
