package br.com.autotrack.model;

import br.com.autotrack.exception.NegocioException;

public abstract class Veiculo {
    private String marca;
    private String modelo;
    private int ano;
    private String placa;
    private double quilometragem;

    public Veiculo(String marca, String modelo, int ano, String placa, double quilometragem) {
        validarAno(ano);
        validarQuilometragem(quilometragem);
        validarPlaca(placa);

        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.quilometragem = quilometragem;
    }

    private void validarAno(int ano) {
        if (ano < 1960) {
            throw new NegocioException("Ano deve ser maior ou igual a 1960.");
        }
    }

    private void validarQuilometragem(double km) {
        if (km < 0) {
            throw new NegocioException("A quilometragem não pode ser negativa.");
        }
    }

    private void validarPlaca(String placa) {
        if (!placa.matches("^[A-Z]{3}[0-9]{4}$")) {
            throw new NegocioException("Padrão de placa inválido.");
        }
    }

    public double calcularIpva(double valorVenal) {
        return 0;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public abstract String tipo();

    @Override
    public String toString() {
        return String.format("%s: %s %s (%d) - Placa: %s - KM: %.2f", tipo(), marca, modelo, ano, placa, quilometragem);
    }
}
