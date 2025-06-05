package br.com.alura.ConsultarFIPE;

import br.com.alura.ConsultarFIPE.model.AnoModelo;
import br.com.alura.ConsultarFIPE.model.DadosVeiculo;
import br.com.alura.ConsultarFIPE.model.Marca;
import br.com.alura.ConsultarFIPE.model.ModeloVeiculo;
import br.com.alura.ConsultarFIPE.principal.Menu;
import br.com.alura.ConsultarFIPE.service.ConsultaAno;
import br.com.alura.ConsultarFIPE.service.ConsultaMarca;
import br.com.alura.ConsultarFIPE.service.ConsultaModelo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ConsultarFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultarFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Menu menu = new Menu();
		String tipoVeiculo = menu.escolheTipoVeiculo();
		System.out.println("Tipo de veiculo escolhido: " + tipoVeiculo);


		ConsultaMarca consultaMarca = new ConsultaMarca();
		List<Marca> marcas = consultaMarca.obterListaMarcas(tipoVeiculo);
		System.out.println("Marcas disponíveis: ");
		marcas.forEach(m -> System.out.println(m.codigo() + " - " + m.nome()));

		String codigoMarca = menu.escolheCodigoMarca(marcas);

		ConsultaModelo consultaModelo = new ConsultaModelo();
		List<ModeloVeiculo> modelos = consultaModelo.obterModelos(tipoVeiculo, codigoMarca);
		System.out.println("\n Modelos disponiveis para a marca selecionada: ");
		modelos.forEach(j -> System.out.println(j.codigo() + " - " + j.nome()));

		String trechoModelo = menu.escolheTrechoModelo();

		List<ModeloVeiculo> modelosFiltrados = modelos.stream()
				.filter(m -> m.nome().toLowerCase().contains(trechoModelo.toLowerCase()))
				.toList();

		System.out.println("\n Modelos encontrados: ");
		modelosFiltrados.forEach(m -> System.out.println(m.codigo() + " - " + m.nome()));

		String codigoModelo = menu.escolheCodigoModelo();
		ConsultaAno consultaAno = new ConsultaAno();
		List<AnoModelo> anos = consultaAno.obterAnos(tipoVeiculo, codigoMarca, codigoModelo);

		System.out.println("\nAnos disponíveis para o modelo escolhido:");
		anos.forEach(a -> System.out.println(a.codigo() + " - " + a.nome()));


		ConsultaAno.ConsultaValor consultaValor = new ConsultaAno.ConsultaValor();

		System.out.println("\nTodos os " + trechoModelo + " filtrados por ano:");
		for (AnoModelo ano : anos) {
			DadosVeiculo dados = consultaValor.obterDados(tipoVeiculo, codigoMarca, codigoModelo, ano.codigo());
			System.out.printf(" %s | Ano: %s | Valor: %s | Combustível: %s\n",
					dados.modelo(), ano.nome(), dados.valor(), dados.combustivel());
		}
		System.exit(0);
	}
}
