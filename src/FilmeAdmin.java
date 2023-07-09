import model.Filme;
import service.FilmeDAOImpl;

import javax.swing.*;
import java.util.List;

import static Utils.utils.*;

public class FilmeAdmin {
    public static void main(String[] args) {

        String[] opcoes = {"Gerenciamento", "Lista Filmes", "Cadastrar", "Importar"};
        int escolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            gerenciar();
        } else if (escolha == 1) {
            consultar();
        } else if (escolha == 2) {
            saveFilme();
        } else if (escolha == 3) {
            importarDados();
        }

    }

    private static void gerenciar() {

        String titulo = JOptionPane.showInputDialog("Titulo:");
        String genero = JOptionPane.showInputDialog("Genero: ");
        int anoInicial = validarEntrada("Ano inicial:");
        int anoFinal = validarEntrada("Ano final:");

        FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
        List<Filme> filmes = filmeDAO.findByGerenciar(titulo, genero, anoInicial, anoFinal);

        if (filmes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum filme encontrado");
        } else {
            // filmes.stream().sorted().forEach(System.out::println);
            gerarTabelaFilmes(filmes);
        }

        String[] opcoes = {"Sim", "Não"};
        int escolha = JOptionPane.showOptionDialog(null, "Deseja excluir um filme ?", "Seleção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            Long idfilme = Long.valueOf(JOptionPane.showInputDialog("Digite o id a ser excluido"));
            filmeDAO.delete(idfilme);

            filmes = filmeDAO.findByGerenciar(titulo, genero, anoInicial, anoFinal);
            gerarTabelaFilmes(filmes);

        } else {
            main(null);
        }
    }

    private static void saveFilme() {
        String titulo = JOptionPane.showInputDialog("Digite o título:");
        String diretores = JOptionPane.showInputDialog("Digite o(s) diretor(es):");
        Double nota = validarEntrada("Digite a nota:");
        int duracao = validarEntrada("Digite a duração (em minutos):");
        int ano = validarEntrada("Digite o ano:");
        String generos = JOptionPane.showInputDialog("Digite o(s) gênero(s):");
        int numDeVotos = validarEntrada("Digite o número de votos:");
        String url = JOptionPane.showInputDialog("Digite a URL:");

        Filme filme = new Filme(titulo, diretores, nota, duracao, ano, generos, numDeVotos, url);

        System.out.println(filme.toString());

        FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
        filmeDAO.save(filme);
    }

    public static void consultar() {

        FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
        List<Filme> filmes = filmeDAO.findByTitulo("");

        if (filmes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum filme encontrado");
        } else {
            // filmes.stream().sorted().forEach(System.out::println);
            gerarTabelaFilmes(filmes);
        }
    }
}