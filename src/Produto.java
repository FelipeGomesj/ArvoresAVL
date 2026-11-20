import java.math.BigDecimal;
public record Produto(
        Long code,
        String name,
        BigDecimal price
) {}