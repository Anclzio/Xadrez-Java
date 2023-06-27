package Pieces;

import java.util.ArrayList;
import java.util.List;

import Tabuleiro.Movimentos;
import Tabuleiro.Tabuleiro;
import enums.Cor;
import enums.Tipo;

public class Torre extends Piece {
    public boolean moveu = false;

    public Torre(Coordenada coordenada, Cor corDaPeca) 
    {
        super(coordenada, corDaPeca);
        tipo = Tipo.TORRE;
    }

    /*
     * As torres se movimentam em linha reta, por tanto para validar o movimento
     * é necessario apenas que a torre esteja na mesma coluna ou linha inicial
     */
    @Override
    public boolean validaMovimento(int xFinal, int yFinal) 
    {
        if(xFinal == this.coordenada.getLinha() || yFinal == this.coordenada.getColuna())
            return xFinal >= 1 && xFinal <= 8 && yFinal >= 1 && yFinal <= 8;
        return false;
    }

    @Override
    public List<Movimentos> movimentosValidos(Tabuleiro tabuleiro) 
    {
        List<Movimentos> movimentosValidos = new ArrayList<>();

        int linhaAtual = this.coordenada.getLinha();
        int colunaAtual = this.coordenada.getColuna();

        // Movimento para cima
        for(int i = linhaAtual + 1; i <= 8; i++)
        {
            Coordenada coordenadaDestino = new Coordenada(i, colunaAtual);

            if (validaMovimento(i, colunaAtual) && (tabuleiro.estaVazio(coordenadaDestino)))
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            else if(validaMovimento(i, colunaAtual) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            }    
            else 
                break;
        }

        // Movimento para baixo
        for(int i = linhaAtual - 1; i >= 0; i--)
        {
            Coordenada coordenadaDestino = new Coordenada(i, colunaAtual);

            if (validaMovimento(i, colunaAtual) && (tabuleiro.estaVazio(coordenadaDestino)))
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            else if(validaMovimento(i, colunaAtual) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            }    
            else 
                break;
        }

        // Movimento para esquerda
        for(int j = colunaAtual - 1; j >= 0; j--)
        {
            Coordenada coordenadaDestino = new Coordenada(linhaAtual, j);

            if (validaMovimento(linhaAtual, j) && (tabuleiro.estaVazio(coordenadaDestino)))
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            else if(validaMovimento(linhaAtual, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            }    
            else 
                break;
        }

        // Movimento para direita
        for(int j = colunaAtual + 1; j <= 8; j++)
        {
            Coordenada coordenadaDestino = new Coordenada(linhaAtual, j);

            if (validaMovimento(linhaAtual, j) && (tabuleiro.estaVazio(coordenadaDestino)))
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            else if(validaMovimento(linhaAtual, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
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
