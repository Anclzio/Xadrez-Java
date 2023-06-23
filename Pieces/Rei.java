package Pieces;

import java.util.ArrayList;
import java.util.List;

import Tabuleiro.Movimentos;
import Tabuleiro.Tabuleiro;
import enums.Cor;
import enums.Tipo;

public class Rei extends Piece{

    public Rei(Coordenada coordenada, Cor corDaPeca) 
    {
        super(coordenada, corDaPeca);
        tipo = Tipo.REI;
    }

    @Override
    public boolean validaMovimento(int xFinal, int yFinal) 
    {
        int distanciaX = Math.abs(xFinal - this.getCoordenada().getLinha());
        int distanciaY = Math.abs(yFinal - this.getCoordenada().getColuna());

        //O rei se move em qualquer direção uma unica casa
        if (distanciaX <= 1 && distanciaY <= 1)
            return true;
        return false;
    }

    @Override
    public List<Movimentos> movimentosValidos(Tabuleiro tabuleiro) 
    {
        List<Movimentos> movimentosValidos = new ArrayList<>();

        int linhaAtual = this.coordenada.getLinha();
        int colunaAtual = this.coordenada.getColuna();

        for (int i = linhaAtual - 1; i <= linhaAtual + 1; i++) 
        {
            for (int j = colunaAtual - 1; j <= colunaAtual + 1; j++) 
            {
                Coordenada coordenadaDestino = new Coordenada(i, j);
                if (validaMovimento(i, j) && (tabuleiro.estaVazio(coordenadaDestino) || tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)))
                    movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
            }
        }

        return movimentosValidos;
    }
    
    

    
}
