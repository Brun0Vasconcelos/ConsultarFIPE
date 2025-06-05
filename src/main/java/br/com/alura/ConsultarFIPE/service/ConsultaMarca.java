package br.com.alura.ConsultarFIPE.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import br.com.alura.ConsultarFIPE.model.Marca;

import java.util.List;

public class ConsultaMarca {

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converte = new ConverteDados();

    public List<Marca> obterListaMarcas(String tipoVeiculo) {
        String endereco = "https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas";

        var json = consumo.obterDados(endereco);
        Marca[] marcasArray = converte.obterDados(json, Marca[].class);
        return List.of(marcasArray);

    }
}
