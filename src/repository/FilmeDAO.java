package repository;

import model.Filme;

import java.util.List;

public interface FilmeDAO {

    public Filme save(Filme filme);
    public Filme update(Filme filme);
    public void delete(Long id);
    public List<Filme> findAll();
    public Filme findById(Long id);

    List<Filme> findByTitulo(String titulo);

    List<Filme> findBySortear(String titulo, double nota);
    List<Filme> findByGerenciar(String titulo, String genero, int anoInicial, int anoFinal);
}
