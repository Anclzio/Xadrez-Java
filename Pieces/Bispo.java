package Pieces;

import java.util.ArrayList;
import java.util.List;

import Tabuleiro.Movimentos;
import Tabuleiro.Tabuleiro;
import enums.Cor;
import enums.Tipo;

public class Bispo extends Piece {

    public Bispo(Coordenada coordenada, Cor corDaPeca)
    {
        super(coordenada, corDaPeca);
        tipo = Tipo.BISPO;
    }

    /*
     * O bispo se movimenta em diagonal, logo basta checkar se a distancia entre o
     * x inicial e o x final é igual a distancia entre o y incial e o y final
     */
    @Override
    public boolean validaMovimento(int xFinal, int yFinal) 
    {
        int distanciaX = Math.abs(xFinal - this.coordenada.getLinha());
        int distanciaY = Math.abs(yFinal - this.coordenada.getColuna());

        return distanciaX == distanciaY;
    }
    
    @Override
    public List<Movimentos> movimentosValidos(Tabuleiro tabuleiro) 
    {
        List<Movimentos> movimentosValidos = new ArrayList<>();

        int linhaAtual = this.coordenada.getLinha();
        int colunaAtual = this.coordenada.getColuna();

        // Movimentos na diagonal superior direita
        for (int i = linhaAtual + 1, j = colunaAtual + 1; i <= 8 && j <= 8; i++, j++) 
        {
            Coordenada coordenadaDestino = new Coordenada(i, j);

            if (validaMovimento(i, j) && (tabuleiro.estaVazio(coordenadaDestino)))
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            else if(validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            }    
            else 
                break;

        }

        // Movimentos na diagonal superior esquerda
        for (int i = linhaAtual + 1, j = colunaAtual - 1; i <= 8 && j >= 1; i++, j--) 
        {
            Coordenada coordenadaDestino = new Coordenada(i, j);

            if (validaMovimento(i, j) && (tabuleiro.estaVazio(coordenadaDestino)))
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            else if(validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            }    
            else 
                break;
            
        }

        // Movimentos na diagonal inferior direita
        for (int i = linhaAtual - 1, j = colunaAtual + 1; i >= 1 && j <= 8; i--, j++)
        {
            Coordenada coordenadaDestino = new Coordenada(i, j);

            if (validaMovimento(i, j) && (tabuleiro.estaVazio(coordenadaDestino)))
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            else if(validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            }    
            else 
                break;
        
        }

        // Movimentos na diagonal inferior esquerda
        for (int i = linhaAtual - 1, j = colunaAtual - 1; i >= 1 && j >= 1; i--, j--) 
        {
            Coordenada coordenadaDestino = new Coordenada(i, j);

            if (validaMovimento(i, j) && (tabuleiro.estaVazio(coordenadaDestino)))
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            else if(validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            }    
            else 
                break;
            
        }

        return movimentosValidos;
    }

 
}