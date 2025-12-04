package br.com.autotrack.repository;

import br.com.autotrack.algorithm.AvlTree;
import br.com.autotrack.annotation.InfoAutor;
import java.util.function.Function;
import java.util.List;

@InfoAutor(nome = "Felipe Madureira", data = "03/12/2025")
public class RepositorioHash<T> implements Repositorio<T> {

    private final AvlTree<String, T> arvore = new AvlTree<>();
    private final Function<T, String> idExtractor;

    public RepositorioHash(Function<T, String> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void cadastrar(T obj) {
        String id = idExtractor.apply(obj);
        if (arvore.contains(id)) {
      
            return;
        }
        arvore.insert(id, obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T buscar(String placa) {
        T res = arvore.get(placa);
        if (res == null) {
            return (T) "placa não encontrada";
        }
        return res;
    }

    @Override
    public void remover(String placa) {
        if (!arvore.contains(placa)) {
            return;
        }
        arvore.remove(placa);
    }

    @Override
    public void atualizar(String placa, T obj) {
        if (!arvore.contains(placa)) {
            return;
        }
        String novoId = idExtractor.apply(obj);
        if (placa.equals(novoId)) {
        
            arvore.insert(novoId, obj);
            return;
        }
        if (arvore.contains(novoId)) {
            return;
        }
        arvore.remove(placa);
        arvore.insert(novoId, obj);
    }

    @Override
    public List<T> listar() {
        return arvore.values();
    }
}