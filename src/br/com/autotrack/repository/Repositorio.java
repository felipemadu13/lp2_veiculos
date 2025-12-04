package br.com.autotrack.repository;

import java.util.List;

public interface Repositorio<T> {

    void cadastrar(T obj);

    T buscar(String placa);
    void remover(String placa);
    
    void atualizar(String placa, T obj);

    List<T> listar();
}
