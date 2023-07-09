package Utils;

import model.Filme;
import service.FilmeDAOImpl;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class utils {


    public static <T> T validarEntrada(String mensagem) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(mensagem);
                if (input.isEmpty()) {
                    throw new NumberFormatException();
                }

                if (input.contains(".")) {
                    return (T) Double.valueOf(input);
                } else {
                    return (T) Integer.valueOf(input);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static void gerarTabelaFilmes(List<Filme> filmes) {

        String[] columnNames = {"idFilme", "Titulo", "Diretores", "Nota", "Duração", "Ano", "Gêneros", "NumDeVotos", "URL"};


        String[][] data = new String[filmes.size()][columnNames.length];


        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            data[i][0] = String.valueOf(filme.getId());
            data[i][1] = filme.getTitulo();
            data[i][2] = filme.getDiretores();
            data[i][3] = String.valueOf(filme.getNota());
            data[i][4] = String.valueOf(filme.getDuracao());
            data[i][5] = String.valueOf(filme.getAno());
            data[i][6] = filme.getGeneros();
            data[i][7] = String.valueOf(filme.getNumDeVotos());
            data[i][8] = filme.getUrl();
        }


        JTable table = new JTable(data, columnNames);


        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);
        table.getColumnModel().getColumn(8).setPreferredWidth(300);

        //abre a tabela em um JOptionPane dentro de um JScrollPane
        JOptionPane.showMessageDialog(null, new JScrollPane(table), "Dados", JOptionPane.PLAIN_MESSAGE);
    }

    public static void importarDados() {
        JFileChooser fileChooser = new JFileChooser();
        int busca = fileChooser.showOpenDialog(null);

//        FileNameExtensionFilter filtroDoArquivo = new FileNameExtensionFilter("Selecione o arquivo","csv");
//        fileChooser.setFileFilter(filtroDoArquivo);

        if (busca == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();

            String nomeArquivo = arquivo.getName();
            String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1);
            if (!extensao.equals("csv")) {
                JOptionPane.showMessageDialog(null, "Formato de arquivo inválido. Selecione um arquivo CSV.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                importarFilmes(arquivo);
                JOptionPane.showMessageDialog(null, "Importação concluída com sucesso!");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + arquivo.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao processar o arquivo: " + e.getMessage());
            }
        }
    }

    public static void importarFilmes(File arquivo) throws IOException {
        try (Scanner scanner = new Scanner(arquivo)) {

            FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
            int filmesImportados = 0;
            List<Filme> filmesL = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] celulas = linha.split(";");


                try {
                    String titulo = celulas[0];
                    String diretores = celulas[1];
                    double nota = Double.parseDouble(celulas[2]);
                    int duracao = Integer.parseInt(celulas[3]);
                    int ano = Integer.parseInt(celulas[4]);
                    String generos = celulas[5];
                    int numDeVotos = Integer.parseInt(celulas[6]);
                    String url = celulas[7];

                    Filme filme = new Filme(titulo, diretores, nota, duracao, ano, generos, numDeVotos, url);
                    filmeDAO.save(filme);
                    filmesL.add(filme);
                    filmesImportados++;
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }

            if (filmesImportados > 0) {
                JOptionPane.showMessageDialog(null, "Importação concluída com sucesso! Filmes importados: " + filmesImportados);
                gerarTabelaFilmes(filmesL);
                gerarTabelaFilmes(filmeDAO.findAll());
                /*for (Filme filme : filmesL) {
                    System.out.println(filme.toString());
                }*/
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum filme válido foi encontrado no arquivo.");
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + arquivo.getAbsolutePath());
        }
    }

}
