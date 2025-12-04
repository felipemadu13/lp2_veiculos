package br.com.autotrack.algorithm;

import br.com.autotrack.model.Veiculo;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class MergeSort implements Ordenacao<Veiculo> {

    public static void sortByAno(Veiculo[] arr) {
        sort(arr, Comparator.comparingInt(Veiculo::getAno));
    }

    public static void sortByModelo(Veiculo[] arr) {
        sort(arr, Comparator.comparing(Veiculo::getModelo));
    }

    public static void sortByQuilometragem(Veiculo[] arr) {
        sort(arr, Comparator.comparingDouble(Veiculo::getQuilometragem));
    }


    public static List<Veiculo> ordenarPorAno(List<Veiculo> list) {
        return ordenar(list, Comparator.comparingInt(Veiculo::getAno));
    }

  
    @Override
    public void ordenar(List<Veiculo> lista) {
        if (lista == null) return;
        List<Veiculo> ordenada = ordenar(lista, Comparator.comparingInt(Veiculo::getAno));
        lista.clear();
        lista.addAll(ordenada);
    }

    public static <T> List<T> ordenar(List<T> list, Comparator<T> cmp) {
        if (list == null) return new ArrayList<>();
        if (cmp == null) throw new NullPointerException("Comparator cannot be null");
        List<T> a = new ArrayList<>(list);
        int n = a.size();
        if (n < 2) return a;
        List<T> aux = new ArrayList<>(n);
        for (int i = 0; i < n; i++) aux.add(null);
        mergeSort(a, aux, 0, n - 1, cmp);
        return a;
    }

    private static <T> void mergeSort(List<T> a, List<T> aux, int l, int r, Comparator<T> cmp) {
        if (l >= r) return;
        int m = (l + r) >>> 1;
        mergeSort(a, aux, l, m, cmp);
        mergeSort(a, aux, m + 1, r, cmp);
        merge(a, aux, l, m, r, cmp);
    }

    private static <T> void merge(List<T> a, List<T> aux, int l, int m, int r, Comparator<T> cmp) {
        for (int i = l; i <= r; i++) aux.set(i, a.get(i));
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if (cmp.compare(aux.get(i), aux.get(j)) <= 0) a.set(k++, aux.get(i++));
            else a.set(k++, aux.get(j++));
        }
        while (i <= m) a.set(k++, aux.get(i++));
        while (j <= r) a.set(k++, aux.get(j++));
    }

    private static void sort(Veiculo[] a, Comparator<Veiculo> cmp) {
        if (a == null || a.length < 2) return;
        Veiculo[] aux = new Veiculo[a.length];
        mergeSort(a, aux, 0, a.length - 1, cmp);
    }

    private static void mergeSort(Veiculo[] a, Veiculo[] aux, int l, int r, Comparator<Veiculo> cmp) {
        if (l >= r) return;
        int m = (l + r) >>> 1;
        mergeSort(a, aux, l, m, cmp);
        mergeSort(a, aux, m + 1, r, cmp);
        merge(a, aux, l, m, r, cmp);
    }

    private static void merge(Veiculo[] a, Veiculo[] aux, int l, int m, int r, Comparator<Veiculo> cmp) {
        System.arraycopy(a, l, aux, l, r - l + 1);
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            if (cmp.compare(aux[i], aux[j]) <= 0) a[k++] = aux[i++];
            else a[k++] = aux[j++];
        }
        while (i <= m) a[k++] = aux[i++];
        while (j <= r) a[k++] = aux[j++];
    }

   


}
