# Sistema de Gestão de Veículos

Descrição
---------
Projeto acadêmico para disciplina de Lógica de Programação II. Sistema simples de gestão de veículos que demonstra uso de estruturas de dados (AVL, HashMap), algoritmos de ordenação (MergeSort), POO (herança, polimorfismo, generics) e reflection/annotations.

Como compilar e executar
------------------------
1. Abra o PowerShell na pasta raiz do projeto.
2. Compilar:
```
javac -d out -sourcepath src src/br/com/autotrack/app/Main.java
```
3. Executar:
```
java -cp out br.com.autotrack.app.Main
```

Alternativa (VS Code)
---------------------
- Abra o projeto no VS Code com extensões Java instaladas.
- Execute a classe `Main.java` diretamente (Run/Debug).

Regras de negócio (validações)
------------------------------
- Ano do veículo: valor plausível (validação no construtor).
- Placa: não vazia/única por repositório.
- Outros: validações implementadas lançam `NegocioException`.


