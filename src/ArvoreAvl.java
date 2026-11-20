
import java.util.Objects;

public class ArvoreAvl<K extends Comparable<K>, V> {

    private class No {
        K key;
        V value;
        int height;
        No left;
        No right;

        No(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private No root;

    // Retorna a altura do nó
    private int height(No node) {
        return node == null ? 0 : node.height;
    }

    // Calcula o fator de balanceamento
    private int fatorBalanceado(No node) {
        return node == null
                ? 0
                : height(node.left) - height(node.right);
    }

    // Atualiza a altura do nó
    private void atualizaAltura(No node) {
        node.height = 1 + Math.max(
                height(node.left),
                height(node.right)
        );
    }

    // Rotação à direita
    private No rotacaoDireita(No y) {
        No x = y.left;
        No temp = x.right;

        x.right = y;
        y.left = temp;

        atualizaAltura(y);
        atualizaAltura(x);

        return x;
    }

    // Rotação à esquerda
    private No rotacaoEsquerda(No x) {
        No y = x.right;
        No temp = y.left;

        y.left = x;
        x.right = temp;

        atualizaAltura(x);
        atualizaAltura(y);

        return y;
    }

    // Balanceamento automático
    private No balanceamento(No node) {

        atualizaAltura(node);

        int balance = fatorBalanceado(node);

        // Casos LL e LR
        if (balance > 1) {

            if (fatorBalanceado(node.left) < 0) {
                node.left = rotacaoEsquerda(node.left);
            }

            return rotacaoDireita(node);
        }

        // Casos RR e RL
        if (balance < -1) {

            if (fatorBalanceado(node.right) > 0) {
                node.right = rotacaoDireita(node.right);
            }

            return rotacaoEsquerda(node);
        }

        return node;
    }

    // INSERÇÃO
    public void put(K key, V value) {

        Objects.requireNonNull(key, "Key não pode ser null");
        Objects.requireNonNull(value, "Value não pode ser null");

        root = insert(root, key, value);
    }

    private No insert(No node, K key, V value) {

        if (node == null) {
            return new No(key, value);
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {
            node.left = insert(node.left, key, value);

        } else if (comparison > 0) {
            node.right = insert(node.right, key, value);

        } else {
            // Chave existente: atualiza o valor
            node.value = value;
            return node;
        }

        return balanceamento(node);
    }

    // PESQUISA
    public V get(K key) {

        Objects.requireNonNull(key, "Key não pode ser null");

        No current = root;

        while (current != null) {

            int comparison = key.compareTo(current.key);

            if (comparison == 0) {
                return current.value;
            }

            current = comparison < 0
                    ? current.left
                    : current.right;
        }

        return null;
    }

    // REMOÇÃO PÚBLICA
    public boolean remove(K key) {

        Objects.requireNonNull(key, "Key não pode ser null");

        if (get(key) == null) {
            return false;
        }

        root = delete(root, key);

        return true;
    }

    // LÓGICA INTERNA DE REMOÇÃO
    private No delete(No node, K key) {

        if (node == null) {
            return null;
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {

            node.left = delete(node.left, key);

        } else if (comparison > 0) {

            node.right = delete(node.right, key);

        } else {

            // Caso 1: nó sem filho à esquerda
            if (node.left == null) {
                return node.right;
            }

            // Caso 2: nó sem filho à direita
            if (node.right == null) {
                return node.left;
            }

            // Caso 3: nó possui dois filhos

            // Encontra o menor valor da subárvore direita
            No successor = minValueNode(node.right);

            // Substitui os dados do nó removido
            node.key = successor.key;
            node.value = successor.value;

            // Remove o sucessor da posição original
            node.right = delete(
                    node.right,
                    successor.key
            );
        }

        // Rebalanceia após a remoção
        return balanceamento(node);
    }

    // Encontra o menor nó de uma subárvore
    private No minValueNode(No node) {

        No current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }
}
