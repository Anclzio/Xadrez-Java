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
        moveu = false;
    }

    @Override
    public boolean validaMovimento(int xFinal, int yFinal) 
    {
        int distanciaX = Math.abs(xFinal - this.getCoordenada().getLinha());
        int distanciaY = Math.abs(yFinal - this.getCoordenada().getColuna());

        // O rei se move em qualquer direção uma única casa
        if (distanciaX <= 1 && distanciaY <= 1)
            return xFinal >= 1 && xFinal <= 8 && yFinal >= 1 && yFinal <= 8;
        // Roque
        if (distanciaX == 2 && distanciaY == 0)
            return xFinal >= 1 && xFinal <= 8 && yFinal >= 1 && yFinal <= 8;
        return false;


    }

    @Override
    public List<Movimentos> movimentosValidos(Tabuleiro tabuleiro) 
    {
        List<Movimentos> movimentosValidos = new ArrayList<>();

        Torre torreTemporariaGrande = null;
        Torre torreTemporariaPequena = null;

        if(corDaPeca == Cor.BRANCO)
        {
            torreTemporariaGrande = (Torre) tabuleiro.getTorre(new Coordenada(1, 1).clone());
            torreTemporariaPequena = (Torre) tabuleiro.getTorre(new Coordenada(1, 8).clone());
        }
        else
        {
            torreTemporariaGrande = (Torre) tabuleiro.getTorre(new Coordenada(8, 1).clone());
            torreTemporariaPequena = (Torre) tabuleiro.getTorre(new Coordenada(8, 8).clone());
        }
        

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


        if(!moveu && torreTemporariaGrande != null && !torreTemporariaGrande.getMoveu() &&
            !Tabuleiro.existePecaEntre(this, torreTemporariaGrande, tabuleiro))
        {   
            movimentosValidos.add(new Movimentos(coordenada, new Coordenada(coordenada.getLinha(), 3), true, true));
        }

        if(!moveu && torreTemporariaPequena != null && !torreTemporariaPequena.getMoveu() &&
            !Tabuleiro.existePecaEntre(this, torreTemporariaPequena, tabuleiro))
        {
            movimentosValidos.add(new Movimentos(coordenada, new Coordenada(coordenada.getLinha(), 7), true, false));
        }
        
        return movimentosValidos;
    }

}
