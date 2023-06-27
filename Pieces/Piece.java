package Pieces;
import java.util.List;

import enums.Cor;
import enums.Tipo;
import Tabuleiro.*;

public abstract class Piece {
    
    protected Coordenada coordenada;
    protected Cor corDaPeca;
    protected Tipo tipo;
    


    public Piece(Coordenada coordenada, Cor corDaPeca)
    {
        this.coordenada = coordenada;
        this.corDaPeca = corDaPeca;
    }


    public abstract boolean validaMovimento(int xFinal, int yFinal);

    public abstract List<Movimentos> movimentosValidos(final Tabuleiro tabuleiro);


    public Coordenada getCoordenada() 
    {
        return this.coordenada;
    }

    public Cor getCor()
    {
        return this.corDaPeca;
    }

    public Tipo geTipo()
    {
        return this.tipo;
    }

    public void setCoordenada(Coordenada coordenada)
    {
        this.coordenada = coordenada;
    }

    @Override
    public String toString()
    {
        return (tipo.getNotacao() + "" + coordenada);
    }

    
}
