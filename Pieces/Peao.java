package Pieces;

import java.util.ArrayList;
import java.util.List;

import Tabuleiro.Movimentos;
import Tabuleiro.Tabuleiro;
import enums.Cor;
import enums.Tipo;

public class Peao extends Piece {

    public Peao(Coordenada coordenada, Cor corDaPeca) 
    {
        super(coordenada, corDaPeca);
        tipo = Tipo.PEAO;
    }

    @Override
    public boolean validaMovimento(int xFinal, int yFinal) 
    {
        int distanciaX = Math.abs(xFinal - this.getCoordenada().getLinha());
        int distanciaY = Math.abs(yFinal - this.getCoordenada().getColuna());

        // Verifica se o movimento é para frente
        if (distanciaX == 1 && distanciaY == 0)
            return true;

        // Verifica se o movimento é para capturar uma peça diagonalmente
        if (distanciaX == 1 && distanciaY == 1)
            return true;

        // Verifica se o movimento é para mover duas casas no primeiro movimento
        if (distanciaX == 2 && distanciaY == 0 && this.isPrimeiroMovimento())
            return true;

        return false;
    }

    private boolean isPrimeiroMovimento() 
    {
        int linha = this.getCoordenada().getLinha();
        return (linha == 2 && this.getCor() == Cor.BRANCO) || (linha == 7 && this.getCor() == Cor.PRETO);
    }

    @Override
    public List<Movimentos> movimentosValidos(Tabuleiro tabuleiro) 
    {
        List<Movimentos> movimentosValidos = new ArrayList<>();

        int linhaAtual = this.coordenada.getLinha();
        int colunaAtual = this.coordenada.getColuna();
        int i , j;
        Coordenada coordenadaDestino = null;

        
        if(corDaPeca == Cor.BRANCO)
        {   
            // Movimento de captura para direita
            i = linhaAtual + 1; j = colunaAtual + 1;
            coordenadaDestino = new Coordenada(i, j);
            if (validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                if(i == 8)
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino,tabuleiro.getPeca(coordenadaDestino) ,true));
                else
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino,tabuleiro.getPeca(coordenadaDestino)));

            }
            // Movimento de captura para esquerda
            i = linhaAtual + 1; j = colunaAtual - 1;
            coordenadaDestino = new Coordenada(i, j);
            if (validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                if(i == 8)
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino,tabuleiro.getPeca(coordenadaDestino) ,true));
                else
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino,tabuleiro.getPeca(coordenadaDestino)));
            }
            

            // Movimento simples para frente
            i = linhaAtual + 1; j = colunaAtual;
            coordenadaDestino = new Coordenada(i, j);
            if (validaMovimento(i, j) && tabuleiro.estaVazio(coordenadaDestino))
            {
                if(i == 8)
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, true)); 
                else
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));                
            }
            
            
            // Movimento duplo para frente
            i = linhaAtual + 2; j = colunaAtual;
            coordenadaDestino = new Coordenada(i, j);
            Coordenada coordenadaIntermediaria = new Coordenada(linhaAtual + 1, colunaAtual);
            if (validaMovimento(i, j) && tabuleiro.estaVazio(coordenadaDestino) && isPrimeiroMovimento() && tabuleiro.estaVazio(coordenadaIntermediaria))
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            
        }

         if(corDaPeca == Cor.PRETO)
        {   
            // Movimento de captura para direita
            i = linhaAtual - 1; j = colunaAtual + 1;
            coordenadaDestino = new Coordenada(i, j);
            if (validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
             {
                if(i == 1)
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino),true));
                else
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino,tabuleiro.getPeca(coordenadaDestino)));

            }

            // Movimento de captura para esquerda
            i = linhaAtual - 1; j = colunaAtual - 1;
            coordenadaDestino = new Coordenada(i, j);
            if (validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca))
            {
                if(i == 1)
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino),true));
                else
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino,tabuleiro.getPeca(coordenadaDestino)));

            }
            

            // Movimento simples para frente
            i = linhaAtual - 1; j = colunaAtual;
            coordenadaDestino = new Coordenada(i, j);
            if (validaMovimento(i, j) && tabuleiro.estaVazio(coordenadaDestino))
            {
                if(i == 1)
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, true));
                else
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));

            }
            
            
            // Movimento duplo para frente
            i = linhaAtual - 2; j = colunaAtual;
            coordenadaDestino = new Coordenada(i, j);
            if (validaMovimento(i, j) && tabuleiro.estaVazio(coordenadaDestino) && isPrimeiroMovimento())
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            
        }
       
        return movimentosValidos;
    }   
}
