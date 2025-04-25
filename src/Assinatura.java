import java.math.BigDecimal;
import java.nio.file.Path;

public class Assinatura {
    private BigDecimal mensalidade;
    private BigDecimal begin;
    private BigDecimal end;

    public Assinatura(BigDecimal mensalidade, BigDecimal begin) {
        this.mensalidade = mensalidade;
        this.begin = begin;
    }

    public Assinatura(BigDecimal mensalidade, BigDecimal begin, BigDecimal end) {
        this.mensalidade = mensalidade;
        this.begin = begin;
        this.end = end;
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public BigDecimal getBegin() {
        return begin;
    }

    public BigDecimal getEnd() {
        return end;
    }
}
