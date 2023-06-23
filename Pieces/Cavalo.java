package Pieces;

import java.util.ArrayList;
import java.util.List;

import Tabuleiro.Movimentos;
import Tabuleiro.Tabuleiro;
import enums.Cor;
import enums.Tipo;

public class Cavalo extends Piece {

    public Cavalo(Coordenada coordenada, Cor corDaPeca)
    {
        super(coordenada, corDaPeca);
        tipo = Tipo.CAVALO;
    }

    /*
     * Cavalos se movimentam em L, logo para validar o movimento é necessario que
     * a distancia entre um dos pontos seja 1 e a outra distancia seja 2
     */

    @Override
    public boolean validaMovimento(int xFinal, int yFinal) 
    {
        
        int distanciaX = Math.abs(xFinal - this.coordenada.getLinha());
        int distanciaY = Math.abs(yFinal - this.coordenada.getColuna());

        if((distanciaX == 1 && distanciaY == 2) || (distanciaX == 2 && distanciaY == 1))
            return true;
        return false;
    }

    @Override
    public List<Movimentos> movimentosValidos(Tabuleiro tabuleiro) 
    {
        List<Movimentos> movimentosValidos = new ArrayList<>();

        int linhaAtual = this.coordenada.getLinha();
        int colunaAtual = this.coordenada.getColuna();

        for (int i = linhaAtual - 2; i <= linhaAtual + 2; i++) 
        {
            for (int j = colunaAtual - 2; j <= colunaAtual + 2; j++) 
            {
                Coordenada coordenadaDestino = new Coordenada(i, j);
                if (validaMovimento(i, j) && (tabuleiro.estaVazio(coordenadaDestino) || tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)))
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
            }
        }

        return movimentosValidos;
    }



}
