package br.com.autotrack.app;

import br.com.autotrack.model.Veiculo;
import br.com.autotrack.repository.RepositorioHash;
import br.com.autotrack.model.Carro;
import br.com.autotrack.model.Moto;
import br.com.autotrack.annotation.InfoAutorPrinter;
import br.com.autotrack.exception.NegocioException;
import br.com.autotrack.algorithm.AvlTree;
import br.com.autotrack.algorithm.MergeSort;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        try {
            
            RepositorioHash<Veiculo> repo = new RepositorioHash<>(Veiculo::getPlaca);
            AvlTree<String, Veiculo> avlTree = new AvlTree<>();
    
          
            Veiculo c1 = new Carro("Toyota", "Corolla", 2018, "ABC1234", 65000);
            Veiculo c2 = new Carro("Volkswagen", "Gol", 2020, "GHI9012", 42000);
    
            Veiculo m1 = new Moto("Honda", "Pop", 2023, "DEF5678", 5000);
            Veiculo m2 = new Moto("Yamaha", "Factor 150", 2022, "JKL3456", 12000);
    
            System.out.println('\n');
            System.out.println("Domínio com classe base + 2 subclasses (polimorfismo ativo).");
            NumberFormat brCurrency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            System.out.println(c1.tipo() + " IPVA: " + brCurrency.format(c1.calcularIpva(100)));
            System.out.println(m1.tipo() + " IPVA: " + brCurrency.format(m1.calcularIpva(100)));
    
            // Adicionar
            repo.cadastrar(c1);
            repo.cadastrar(c2);
            repo.cadastrar(m1);
            repo.cadastrar(m2);
    
            // adicionar também na árvore AVL para que remover funcione
            avlTree.inserir(c1.getPlaca(), c1);
            avlTree.inserir(c2.getPlaca(), c2);
            avlTree.inserir(m1.getPlaca(), m1);
            avlTree.inserir(m2.getPlaca(), m2);
    
            // Listar
            System.out.println("\nLista (repositório):");
            List<Veiculo> todos = repo.listar();
            for (Veiculo v : todos) System.out.println(v);
    
            // buscar
            System.out.println("\nBuscar placa GHI9012:");
            System.out.println(repo.buscar("GHI9012"));
    
            // remover
            System.out.println("\nRemover placa GHI9012:");
            repo.remover("GHI9012");
            avlTree.remover("GHI9012");
            System.out.println("Após remoção, buscar GHI9012: " + repo.buscar("GHI9012"));
    
            // atualizar lista após remoção
            todos = repo.listar();
    
            // imprimir em ordem (árvore)
            System.out.println("\nImprimir em ordem (árvore por placa):");
            TreeSet<Veiculo> arvore = new TreeSet<>(Comparator.comparing(Veiculo::getPlaca));
            arvore.addAll(todos);
            for (Veiculo v : arvore) System.out.println(v);
    
            // imprimir ordenado (algoritmo MergeSort por ano)
            System.out.println("\nOrdenado por ano (MergeSort):");
            List<Veiculo> copia = new ArrayList<>(todos);
            List<Veiculo> ordenadoPorAno = MergeSort.ordenarPorAno(copia);
            for (Veiculo v : ordenadoPorAno) System.out.println(v);
    
            // imprimir informações de autor (anotações)
            System.out.println("\nInformações de autor (anotações):");
            InfoAutorPrinter.printInfoAutor(Carro.class);
            InfoAutorPrinter.printInfoAutor(Moto.class);
    
        } catch (NegocioException e) {
            System.err.println("\nErro: " + e.getMessage());
        }

    }
}

