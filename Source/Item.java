package Source;

public class Item {
    public String jogos, categoria;
    public double avaliacao;
    public Item(String jogos, String categoria, double avaliacao) {
        this.jogos = jogos;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
    }
    public String getJogos() {
        return jogos;
    }
    public void setJogos(String jogos) {
        this.jogos = jogos;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public double getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    @Override
    public String toString() {
        return "Jogo: " + jogos + "da categoria: " + categoria + ", com a avaliação= " + avaliacao;
    }

    
}
