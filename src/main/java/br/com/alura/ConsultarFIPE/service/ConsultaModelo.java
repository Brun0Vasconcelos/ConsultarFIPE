package br.com.alura.ConsultarFIPE.service;

import br.com.alura.ConsultarFIPE.model.ListaModelos;
import br.com.alura.ConsultarFIPE.model.ModeloVeiculo;

import java.util.List;

public class ConsultaModelo {

    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    public List<ModeloVeiculo> obterModelos(String tipoVeiculo, String escolheCodigoMarca) {
        String endereco = "https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo + "/marcas/" + escolheCodigoMarca + "/modelos";

        String json = consumo.obterDados(endereco);
        ListaModelos lista = conversor.obterDados(json, ListaModelos.class);

        return lista.modelos();
    }

}
