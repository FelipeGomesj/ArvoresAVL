import java.math.BigDecimal;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        // Criando nossa árvore
        ArvoreAvl<Long, Produto> products = new ArvoreAvl<>();

        // Inserindo produtos
        products.put(100L, new Produto(
                100L,
                "Cimento",
                new BigDecimal("35.90")
        ));

        products.put(200L, new Produto(
                200L,
                "Tinta",
                new BigDecimal("189.90")
        ));

        products.put(300L, new Produto(
                300L,
                "Torneira",
                new BigDecimal("249.90")
        ));

        products.put(400L, new Produto(
                400L,
                "Piso",
                new BigDecimal("89.90")
        ));

        products.put(500L, new Produto(
                500L,
                "Chuveiro",
                new BigDecimal("159.90")
        ));

        // PESQUISA
        System.out.println("=== PESQUISA ===");

        Produto product = products.get(300L);

        System.out.println(product);

        // REMOÇÃO
        System.out.println("\n=== REMOÇÃO ===");

        boolean removed = products.remove(300L);

        System.out.println("Produto removido: " + removed);

        // PESQUISANDO NOVAMENTE
        System.out.println("\n=== NOVA PESQUISA ===");

        Produto searchAgain = products.get(300L);

        System.out.println("Resultado: " + searchAgain);

        // INSERINDO NOVAMENTE
        System.out.println("\n=== NOVA INSERÇÃO ===");

        products.put(300L, new Produto(
                300L,
                "Torneira Gourmet",
                new BigDecimal("299.90")
        ));

        System.out.println(products.get(300L));
    }
}