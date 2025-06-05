package br.com.alura.ConsultarFIPE.principal;

import br.com.alura.ConsultarFIPE.model.Marca;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner teclado = new Scanner(System.in);

    public String escolheTipoVeiculo() {
        int opcao = 0;
        String tipoVeiculo = "";

        while (true) {
            System.out.println("""
                    Digite o número da opção desejada:
                    1 - Carro
                    2 - Moto
                    3 - Caminhão
                    """);
            try {
                String entrada = teclado.nextLine();

                opcao = Integer.parseInt(entrada);

                if (opcao == 1) {
                    tipoVeiculo = "carros";
                    break;
                } else if (opcao == 2) {
                    tipoVeiculo = "motos";
                    break;
                } else if (opcao == 3) {
                    tipoVeiculo = "caminhoes";
                    break;
                } else {
                    System.out.println("opção invalida. Digite um numero entre 1 e 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }

        return tipoVeiculo;
    }

    public String escolheCodigoMarca(List<Marca> marcas) {
        String codigo = "";
        while (true) {
            System.out.print("\nDigite o código da marca desejada: ");
            codigo = teclado.nextLine().trim();
            String finalCodigo = codigo;

            boolean existe = marcas.stream()
                    .anyMatch(m -> m.codigo().equals(finalCodigo));
            if (existe) {
                break;
            } else {
                System.out.println("Código inválido. Digite um código numérico da lista.");
            }
        }
        return codigo;
    }

    public String escolheTrechoModelo() {
        System.out.println("\n Digite parte do nome do modelo desejado: ");
        return teclado.nextLine().trim();
    }

    public String escolheCodigoModelo() {
        System.out.print("\n Digite o código do modelo desejado: ");
        return teclado.nextLine().trim();
    }
}
