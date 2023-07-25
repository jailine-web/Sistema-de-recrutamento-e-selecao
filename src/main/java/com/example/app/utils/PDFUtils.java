package com.example.app.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PDFUtils {
	
	public static byte[] lerArquivoPDF(Path caminhoDoArquivo) throws IOException{
		return Files.readAllBytes(caminhoDoArquivo);
	}
}
