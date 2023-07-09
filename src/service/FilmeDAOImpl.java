package service;

import Error.FilmeException;
import model.Filme;
import repository.FilmeDAO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAOImpl implements FilmeDAO {

    private final static String urlBd = "jdbc:mysql://localhost:3306/impacta";
    private final static String user = "root";
    private final static String senha = "root";

    @Override
    public Filme save(Filme filme) {

        String query = "INSERT INTO filme VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = getConn()) {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, filme.getTitulo());
            preparedStatement.setString(2, filme.getDiretores());
            preparedStatement.setDouble(3, filme.getNota());
            preparedStatement.setInt(4, filme.getDuracao());
            preparedStatement.setInt(5, filme.getAno());
            preparedStatement.setString(6, filme.getGeneros());
            preparedStatement.setInt(7, filme.getNumDeVotos());
            preparedStatement.setString(8, filme.getUrl());

            preparedStatement.executeUpdate();

        } catch (SQLException s) {
            throw new FilmeException("Não foi possivel salvar no banco de dados: ", s);
        }
        return filme;
    }


    @Override
    public Filme update(Filme filme) {
        return null;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM filme WHERE idFilme = ? ";
        try (Connection c = getConn() ;
        PreparedStatement preparedStatement = getConn().prepareStatement(query)){

            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        }catch(SQLException s){
            String m = "Fala ao tentar excluir o id " + id;
            System.out.println(m + s);
        }
    }

    @Override
    public List<Filme> findAll() {
        List<Filme> filmes = new ArrayList<>();
        String query = "SELECT * FROM filme order by titulo asc";

        try (Connection connection = getConn();
             Statement statement = connection.createStatement()) {

            getResultSet(filmes, query, statement);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consulta os filmes" + e);
        }
        return filmes;
    }


    @Override
    public Filme findById(Long id) {
        return null;
    }

    @Override
    public List<Filme> findByTitulo(String titulo) {
        String query = "SELECT * FROM filme WHERE titulo LIKE '%" + titulo + "%' order by titulo asc";
        List<Filme> filmes = new ArrayList<>();

        if (titulo.isBlank()) {
            filmes.addAll(findAll());
        } else {

            try (Connection connection = getConn();
                 Statement statement = connection.createStatement()) {

                getResultSet(filmes, query, statement);


            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao consulta os filmes" + e);
            }

        }


        return filmes;
    }

    @Override
    public List<Filme> findBySortear(String titulo, double nota) {
        String query = "SELECT * FROM filme WHERE titulo LIKE '%" + titulo + "%' and nota >= '" + nota + "' order by titulo asc";
        List<Filme> filmes = new ArrayList<>();

        if (titulo.isBlank()) {
            filmes.addAll(findAll());
        } else {

            try (Connection connection = getConn();
                 Statement statement = connection.createStatement()) {

                getResultSet(filmes, query, statement);


            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao consulta os filmes" + e);
            }

        }
        return filmes;
    }

    @Override
    public List<Filme> findByGerenciar(String titulo, String genero, int anoInicial, int anoFinal) {
        String query = "SELECT * FROM filme WHERE Titulo LIKE '%" + titulo + "%' AND generos LIKE '%" + genero + "%' AND Ano BETWEEN " + anoInicial + " AND " + anoFinal +" order by titulo";
        List<Filme> filmes = new ArrayList<>();

                  try (Connection connection = getConn();
                 Statement statement = connection.createStatement()) {

                getResultSet(filmes, query, statement);


            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao consulta os filmes" + e);
            }
        return filmes;
    }

    public Connection getConn() {
        try {
            return DriverManager.getConnection(urlBd, user, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getResultSet(List<Filme> filmes, String query, Statement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Filme filme = new Filme();
                filme.setId(resultSet.getInt("idFilme"));
                filme.setTitulo(resultSet.getString("Titulo"));
                filme.setDiretores(resultSet.getString("Diretores"));
                filme.setNota(resultSet.getDouble("nota"));
                filme.setDuracao(resultSet.getInt("Duracao"));
                filme.setAno(resultSet.getInt("Ano"));
                filme.setGeneros(resultSet.getString("generos"));
                filme.setNumDeVotos(resultSet.getInt("num_votos"));
                filme.setUrl(resultSet.getString("url"));

                filmes.add(filme);

            }

        }
    }
}
