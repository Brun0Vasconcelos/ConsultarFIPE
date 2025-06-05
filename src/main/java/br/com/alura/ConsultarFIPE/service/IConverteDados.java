package br.com.alura.ConsultarFIPE.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}

