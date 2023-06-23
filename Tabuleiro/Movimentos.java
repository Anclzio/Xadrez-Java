package Tabuleiro;

import Pieces.Coordenada;
import Pieces.Piece;

public class Movimentos {
    private Coordenada origem;
    private Coordenada destino;
    private Piece pecaCapturada = null;

    public Movimentos(Coordenada origem, Coordenada destino) 
    {
        this.origem = origem;
        this.destino = destino;
    }
    public Movimentos(Coordenada origem, Coordenada destino, Piece pecaCapturada) 
    {
        this.origem = origem;
        this.destino = destino;
        this.pecaCapturada = pecaCapturada;
    }

    public Coordenada getOrigem() 
    {
        return origem;
    }

    public Piece getPecaCapturada()
    {
        return pecaCapturada;
    }

    public Coordenada getDestino() 
    {
        return destino;
    }

   
    public void moverPeca(Movimentos movimentoValido, Tabuleiro tabuleiro)
    {
        Piece peca = tabuleiro.getPeca(movimentoValido.origem);
        if(peca != null)
        {   
            peca.setCoordenada(movimentoValido.destino);
            tabuleiro.adicionarPeca(peca);
            tabuleiro.removePeca(origem);
        }  
    }

    public void desfazerMovimento(Movimentos movimento, Tabuleiro tabuleiro) 
    {
        Piece pecaMovida = tabuleiro.getPeca(movimento.getDestino());
        Piece pecaCapturada = movimento.getPecaCapturada();

        moverPeca(movimento, tabuleiro);
        if(pecaMovida != null)
        {
            pecaMovida.setCoordenada(movimento.getOrigem());
        }
        

        if (pecaCapturada != null) 
        {   
            pecaCapturada.setCoordenada(movimento.getDestino());
            tabuleiro.adicionarPeca(pecaCapturada);
        }
    }

        
        
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
    
        Movimentos other = (Movimentos) obj;
    
        return this.origem.equals(other.origem) && this.destino.equals(other.destino);
    }

        
}
