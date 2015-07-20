package br.com.eblade.poc.foundation;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.eblade.poc.foundation.file.FileAccess;

// Source http://blog.caelum.com.br/manipulando-arquivos-com-recursos-do-java-8/
public class RunFileAccessJava8Example {

	private static final Pattern HORAS = Pattern.compile(".*([0-9]{2}(,[0-9])? HORAS SEMANAIS|DEDICACAO EXCLUSIVA).*");
	
	public static void main(String[] args) {

		// Path caminho = Paths.get(System.getProperty("user.home"),
		// "Downloads/20150131_Cadastro.csv");
		try {

			Stream<String> linhas = FileAccess
					.getLines(System.getProperty("user.home") + "/Downloads/20150131_Cadastro.csv");

			// Stream<String> linhas = Files.lines(caminho,
			// StandardCharsets.ISO_8859_1);
			// linhas.forEach(System.out::println);

			Stream<String> horasSemanais = linhas.map(linha -> {
				Matcher matcher = HORAS.matcher(linha);
				return matcher.matches() ? matcher.group(1) : "";
			});

			Stream<String> horasSemanaisNaoVazias = horasSemanais.filter(horaSemanal -> !horaSemanal.isEmpty());

			Stream<String> horasSemanaisOrdenadas = horasSemanaisNaoVazias.sorted();

			Map<String, Long> horasSemanaisAgrupadas = horasSemanaisOrdenadas
					.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

			horasSemanaisAgrupadas.forEach((k, v) -> System.out.println(v + "\t" + k));

			/*
			 * the same, but in less lines: the methos is 12x more fast than
			 * unix version Files.lines(caminho, StandardCharsets.ISO_8859_1)
			 * .map(linha -> { Matcher matcher = HORAS.matcher(linha); return
			 * matcher.matches() ? matcher.group(1) : ""; }) .filter(horaSemanal
			 * -> !((String) horaSemanal).isEmpty()) .sorted() .collect(
			 * Collectors.groupingBy(Function.identity(), LinkedHashMap::new,
			 * Collectors.counting())) .forEach((k, v) -> System.out.println(v +
			 * "\t" + k));
			 */

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
