package br.com.autotrack.model;

import br.com.autotrack.annotation.InfoAutor;

@InfoAutor(nome = "Felipe Madureira", data = "02/12/2025")
public class Moto extends Veiculo {
    public Moto(String marca, String modelo, int ano, String placa, double km) {
        super(marca, modelo, ano, placa, km);
    }

    @Override
    public String tipo() {
        return "Moto";
    }

    @Override
    public double calcularIpva(double valorVenal) {
        return valorVenal * 0.02; 
    }
}
