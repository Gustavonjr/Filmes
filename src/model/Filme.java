package model;

import java.util.Objects;

public class Filme implements Comparable{

    private long id;
    private String titulo;
    private String diretores;
    private double nota;
    private int duracao;
    private int ano;
    private String generos;
    private int numDeVotos;
    private String url;


    public Filme() {

    }

    public Filme(String titulo, String diretores, double nota, int duracao, int ano, String generos, int numDeVotos, String url) {
        this.titulo = titulo;
        this.diretores = diretores;
        this.nota = nota;
        this.duracao = duracao;
        this.ano = ano;
        this.generos = generos;
        this.numDeVotos = numDeVotos;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretores() {
        return diretores;
    }

    public void setDiretores(String diretores) {
        this.diretores = diretores;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public int getNumDeVotos() {
        return numDeVotos;
    }

    public void setNumDeVotos(int numDeVotos) {
        this.numDeVotos = numDeVotos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "\n  id=" + id +
                "\n  titulo='" + titulo + '\'' +
                "\n  diretores='" + diretores + '\'' +
                "\n  nota=" + nota +
                "\n  duracao=" + duracao +
                "\n  ano=" + ano +
                "\n  generos='" + generos + '\'' +
                "\n  numDeVotos=" + numDeVotos +
                "\n  url='" + url + '\'' +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filme)) return false;
        Filme filme = (Filme) o;
        return id == filme.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Object o) {
        Filme f = (Filme) o;
        return titulo.compareTo((String) f.getTitulo());
    }
}
