package br.com.alura.ConsultarFIPE.service;

import br.com.alura.ConsultarFIPE.model.AnoModelo;
import br.com.alura.ConsultarFIPE.model.DadosVeiculo;

import java.util.List;

public class ConsultaAno {
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public List<AnoModelo> obterAnos(String tipoVeiculo, String codigoMarca, String codigoModelo) {
        String endereco = "https://parallelum.com.br/fipe/api/v1/" + tipoVeiculo +
                "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos";

        String json = consumo.obterDados(endereco);
        AnoModelo[] anosArray = conversor.obterDados(json, AnoModelo[].class);
        return List.of(anosArray);
    }

    public static class ConsultaValor {

        private final ConsumoApi consumo = new ConsumoApi();
        private final ConverteDados conversor = new ConverteDados();

        public DadosVeiculo obterDados(String tipoVeiculo, String codigoMarca, String codigoModelo, String codigoAno) {
            String endereco = "https://parallelum.com.br/fipe/api/v1/"
                    + tipoVeiculo + "/marcas/" + codigoMarca
                    + "/modelos/" + codigoModelo + "/anos/" + codigoAno;

            String json = consumo.obterDados(endereco);
            return conversor.obterDados(json, DadosVeiculo.class);
        }
    }
}
