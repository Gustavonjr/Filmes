package test;

import model.Filme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.FilmeDAOImpl;

import java.sql.Connection;
import java.util.List;

public class FilmeDAOTest {

    private final FilmeDAOImpl filmeDAO = new FilmeDAOImpl();

    @Test
    public void testarConexão(){
        Connection con = filmeDAO.getConn();
    }

    @Test
    public void testFindAll() {
        List<Filme> filmes = filmeDAO.findAll();
        Assertions.assertNotNull(filmes);
        Assertions.assertFalse(filmes.isEmpty());
        for (Filme filme : filmes) {
            System.out.println(filme);
        }
    }

    @Test
    public void testSave() {
        Filme novoFilme = new Filme("Filme de Teste","Diretor de Teste",7.5,120,2021,"Ação, Aventura",10,"https://exemplo.com/filme-de-teste");
        Filme filmeSalvo = filmeDAO.save(novoFilme);

        Assertions.assertNotNull(filmeSalvo);
        Assertions.assertNotNull(filmeSalvo.getId());

        System.out.println("Novo filme salvo: " + filmeSalvo);
    }

    @Test
    public void testFindByTitulo() {
        String titulo = "Teste";
        List<Filme> filmesEncontrados = filmeDAO.findByTitulo(titulo);

        Assertions.assertNotNull(filmesEncontrados);
        for (Filme filme : filmesEncontrados) {
            Assertions.assertTrue(filme.getTitulo().contains(titulo));
            System.out.println(filme);
        }
    }
}
