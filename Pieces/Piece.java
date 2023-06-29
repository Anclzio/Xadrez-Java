package Pieces;
import java.util.List;

import enums.Cor;
import enums.Tipo;
import Tabuleiro.*;

public abstract class Piece implements Cloneable{
    
    protected Coordenada coordenada;
    protected Cor corDaPeca;
    protected Tipo tipo;
    protected Boolean moveu = null;

    public Piece(Coordenada coordenada, Cor corDaPeca)
    {
        this.coordenada = coordenada;
        this.corDaPeca = corDaPeca;
    }

    public Piece(Piece peca)
    {
        this.coordenada = peca.coordenada;
        this.corDaPeca = peca.corDaPeca;
        this.tipo = peca.tipo;
        this.moveu = peca.moveu;
    }


    public abstract boolean validaMovimento(int xFinal, int yFinal);

    public abstract List<Movimentos> movimentosValidos(final Tabuleiro tabuleiro);

    public Boolean getMoveu() 
    {
        return moveu;
    }

    public Coordenada getCoordenada() 
    {
        return coordenada;
    }

    public Cor getCor()
    {
        return corDaPeca;
    }

    public Tipo geTipo()
    {
        return tipo;
    }

    public void setMoveu(Boolean moveu) 
    {
        this.moveu = moveu;
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
    @Override
    public Piece clone() 
    {
        try {
            Piece cloned = (Piece) super.clone();
            cloned.coordenada = this.coordenada.clone();
            cloned.corDaPeca = this.getCor();
            cloned.moveu = this.getMoveu();

            return cloned;
        } catch (CloneNotSupportedException e) {
            // Lidar com exceção, se necessário
            return null;
        }
    

    
    }
}
