package Tabuleiro;

import java.util.ArrayList;
import java.util.List;

import Pieces.*;
import enums.Cor;

public class Movimentos {
    private Coordenada origem;
    private Coordenada destino; 
    private Piece pecaCapturada = null;
    public boolean ehRoque = false;
    public boolean ehRoqueGrande = false;
    public boolean ehPromocao = false;

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

    public Movimentos(Coordenada origem, Coordenada destino, boolean ehPromocao) 
    {
        this.origem = origem;
        this.destino = destino;
        this.ehPromocao = ehPromocao;
    }

    public Movimentos(Coordenada origem, Coordenada destino, Piece pecaCapturada, boolean ehPromocao) 
    {
        this.origem = origem;
        this.destino = destino;
        this.pecaCapturada = pecaCapturada;
        this.ehPromocao = ehPromocao;
    }

    public Movimentos(Coordenada origem, Coordenada destino, boolean ehRoque, boolean ehRoqueGrande) 
    {
        this.origem = origem;
        this.destino = destino;
        this.ehRoque = ehRoque;
        this.ehRoqueGrande = ehRoqueGrande;
    }




   /*
    * Recebe um movimento, que ja vem da lista de movimentos validos e portanto ja é validado,
      e um tabuleiro.
    * Confere se o movimento é um Roque, ou promoção e realiza o movimento
    */
    public static void moverPeca(Movimentos movimentoValido, Tabuleiro tabuleiro)
    {   
        Piece peca = tabuleiro.getPeca(movimentoValido.origem);

        if(movimentoValido.ehRoque == true && peca != null)
        {
            Torre torre = null;

            if(movimentoValido.ehRoqueGrande == true)
            {
                List<Piece> pecas = new ArrayList<>(tabuleiro.getPecas().values());
                for (Piece npeca : pecas) 
                {
                    if (npeca instanceof Torre && npeca.getCoordenada().getColuna() == 1 && npeca.getCor() == peca.getCor())
                        torre = (Torre) npeca;        
                }
            }
            else
            {
                List<Piece> pecas = new ArrayList<>(tabuleiro.getPecas().values());
                for (Piece npeca : pecas) 
                {
                    if (npeca instanceof Torre && npeca.getCoordenada().getColuna() == 8 && npeca.getCor() == peca.getCor()) 
                        torre = (Torre) npeca;                 
                }
            }

            roque(tabuleiro, (Rei)peca, torre);
        }
        else if(movimentoValido.ehPromocao == true && peca != null)
        {
            Cor cor =  tabuleiro.getPeca(movimentoValido.origem).getCor();
            System.out.println("promoção");
            tabuleiro.removePeca(movimentoValido.origem);
            tabuleiro.adicionarPeca(new Rainha(movimentoValido.destino, cor));
        }
        else if(peca != null)
        {   
            peca.setCoordenada(movimentoValido.destino);
            tabuleiro.adicionarPeca(peca);
            tabuleiro.removePeca(movimentoValido.origem);
            if(peca.getMoveu() != null)
                peca.setMoveu(true);
        }
          
    }

    /*
     * Como o proprio nome diz desfaz o movimento enviado e recria a peça
       capturada se necessario
     */
    public static void desfazerMovimento(Movimentos movimento, Tabuleiro tabuleiro) 
    {
        Piece pecaMovida = tabuleiro.getPeca(movimento.getDestino());
        Piece pecaCapturada = movimento.getPecaCapturada();

        moverPeca(movimento, tabuleiro);
        if(pecaMovida != null)
        {
            pecaMovida.setCoordenada(movimento.getOrigem());
            pecaMovida.setMoveu(false);
        }
        

        if (pecaCapturada != null) 
        {   
            pecaCapturada.setCoordenada(movimento.getDestino());
            tabuleiro.adicionarPeca(pecaCapturada);
        }
    }

    /*
     * Recebe um tabuleiro um rei e uma torre e realiza o Roque se possivel
     */
    public static void roque(Tabuleiro tabuleiro, Rei rei, Torre torre) 
    {
        if (!rei.getMoveu() && !torre.getMoveu() && !Tabuleiro.existePecaEntre(rei, torre, tabuleiro)) 
        {   
            int colunaRei = rei.getCoordenada().getColuna();
            int colunaTorre = torre.getCoordenada().getColuna();
            int direcao = colunaRei < colunaTorre ? 1 : -1;

            Coordenada destinoRei = new Coordenada(rei.getCoordenada().getLinha(), colunaRei + 2 * direcao);
            Coordenada destinoTorre = new Coordenada(torre.getCoordenada().getLinha(), colunaRei + direcao);

            // Verifica se as casas de destino estão vazias e não são atacadas
            if (tabuleiro.estaVazio(destinoRei) && tabuleiro.estaVazio(destinoTorre) &&
                !tabuleiro.estaEmCheck(tabuleiro, rei.getCor()) &&
                !tabuleiro.existePecaInimiga(destinoRei, rei.getCor()) &&
                !tabuleiro.existePecaInimiga(destinoTorre, rei.getCor())) 
            {

                Coordenada origemRei = rei.getCoordenada();
                Coordenada origemTorre = torre.getCoordenada();

                Movimentos movimentoRei = new Movimentos(origemRei, destinoRei);
                Movimentos movimentoTorre = new Movimentos(origemTorre, destinoTorre);

                Movimentos.moverPeca(movimentoTorre, tabuleiro);
                Movimentos.moverPeca(movimentoRei, tabuleiro);


            }
            
        }
       
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

    
    @Override
    public String toString()
    {   int aux = destino.getColuna() + 96;

        if(this.pecaCapturada != null)
            return ("x" + (char)aux + destino.getLinha());

        if(this.ehRoque)
        {   
            if(this.ehRoqueGrande)
                return("RoqueGrande "+(char) aux + destino.getLinha());
            return("RoquePequeno "+(char) aux + destino.getLinha());
        }
            
            
        return (""+(char) aux + destino.getLinha()); 
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
