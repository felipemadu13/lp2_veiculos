package br.com.autotrack.algorithm;

import java.util.List;
import java.util.ArrayList;

public class AvlTree<K extends Comparable<K>, V> implements Arvore<K, V> {

    private static final class Node<K, V> {
        K chave;
        V valor;
        int height;
        Node<K, V> left, right;

        Node(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
            this.height = 1;
        }
    }

    private Node<K, V> root;

    @Override
    public void inserir(K chave, V valor) {
        root = insert(root, chave, valor);
    }

    public void insert(K chave, V valor) { inserir(chave, valor); }
    public V get(K chave) { return buscar(chave); }
    public void remove(K chave) { remover(chave); }
    public boolean contains(K chave) { return buscar(chave) != null; }
    public List<V> values() { return emOrdem(); }

    private Node<K, V> insert(Node<K, V> node, K chave, V valor) {
        if (node == null) {
            return new Node<>(chave, valor);
        }

        int cmp = chave.compareTo(node.chave);
        if (cmp < 0) {
            node.left = insert(node.left, chave, valor);
        } else if (cmp > 0) {
            node.right = insert(node.right, chave, valor);
        } else {
            node.valor = valor;
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    @Override
    public V buscar(K chave) {
        Node<K, V> cur = root;
        while (cur != null) {
            int cmp = chave.compareTo(cur.chave);
            if (cmp == 0) return cur.valor;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return null;
    }

    @Override
    public void remover(K chave) {
        Holder<V> removed = new Holder<>();
        root = remove(root, chave, removed);
    }

    private static final class Holder<V> { V valor; }

    private Node<K, V> remove(Node<K, V> node, K chave, Holder<V> removed) {
        if (node == null) return null;

        int cmp = chave.compareTo(node.chave);
        if (cmp < 0) {
            node.left = remove(node.left, chave, removed);
        } else if (cmp > 0) {
            node.right = remove(node.right, chave, removed);
        } else {

            removed.valor = node.valor;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node<K, V> succ = minNode(node.right);
            node.chave = succ.chave;
            node.valor = succ.valor;
            node.right = remove(node.right, succ.chave, new Holder<>()); 
        }

        updateHeight(node);
        return balance(node);
    }

    private Node<K, V> minNode(Node<K, V> n) {
        while (n.left != null) n = n.left;
        return n;
    }

    private int height(Node<K, V> n) {
        return n == null ? 0 : n.height;
    }

    private void updateHeight(Node<K, V> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int balanceFactor(Node<K, V> n) {
        return height(n.left) - height(n.right);
    }

    private Node<K, V> rotateRight(Node<K, V> y) {
        Node<K, V> x = y.left;
        Node<K, V> T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<K, V> rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.right;
        Node<K, V> T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private Node<K, V> balance(Node<K, V> node) {
        int bf = balanceFactor(node);

        if (bf > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (bf < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    @Override
    public List<V> emOrdem() {
        List<V> out = new ArrayList<>();
        inOrder(root, out);
        return out;
    }

    private void inOrder(Node<K, V> node, List<V> out) {
        if (node == null) return;
        inOrder(node.left, out);
        out.add(node.valor);
        inOrder(node.right, out);
    }
}