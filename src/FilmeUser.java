import model.Filme;
import service.FilmeDAOImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Utils.utils.gerarTabelaFilmes;

public class FilmeUser {
    public static void main(String[] args) {

        String[] opcoes = {"Consultar", "Sortear"};
        int escolha = JOptionPane.showOptionDialog(null, "Selecione uma opção", "Seleção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            consultar();
        } else if (escolha == 1) {
            sorteiaFilme();
        }

    }

    public static void consultar() {

        String titulo = JOptionPane.showInputDialog("Nome do filme:");
        FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
        List<Filme> filmes = filmeDAO.findByTitulo(titulo);

        if (filmes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum filme encontrado");
        } else {
            // filmes.stream().sorted().forEach(System.out::println);
            gerarTabelaFilmes(filmes);
        }
    }

    public static void sorteiaFilme() {
        String titulo = JOptionPane.showInputDialog("Nome do filme:");
        Double nota;
        String notaValidar = JOptionPane.showInputDialog("Notas maiores ou igual a:");

        if (notaValidar.isEmpty()) {
            nota = 0.0;
        } else {
            nota = Double.valueOf(notaValidar);
        }

        FilmeDAOImpl filmeDAO = new FilmeDAOImpl();
        List<Filme> filmes = filmeDAO.findBySortear(titulo, nota);

        if (filmes.size() > 0) {
            Random random = new Random();
            int indiceAleatorio = random.nextInt(filmes.size());
            Filme filmeSorteado = filmes.get(indiceAleatorio);

            List<Filme> filmeSorteadoLista = new ArrayList<>();
            filmeSorteadoLista.add(filmeSorteado);

            gerarTabelaFilmes(filmeSorteadoLista);
        } else {
            JOptionPane.showMessageDialog(null, "Filme não encontrado");
        }

        String[] opcoes = {"Repetir", "Sair"};
        int escolha = JOptionPane.showOptionDialog(null, "Deseja sortear outro filme ?", "Sorteio Filmes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha == 0) {
            sorteiaFilme();
        } else if (escolha == 1) {
            JOptionPane.showMessageDialog(null, "Sistema será fechado");
        }

    }
}
