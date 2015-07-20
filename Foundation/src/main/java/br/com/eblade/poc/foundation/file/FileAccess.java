package br.com.eblade.poc.foundation.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class FileAccess {

	private static final Charset defaultCharset = StandardCharsets.ISO_8859_1;
	
	public static Stream<String> getLines(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Stream<String> linhas = Files.lines(path, defaultCharset);
		return linhas;
	}
	
	public static Stream<String> getLines(String filePath, Charset fileCharset) throws IOException {
		Path path = Paths.get(filePath);
		Stream<String> linhas = Files.lines(path, fileCharset);
		return linhas;
	}
	
}
