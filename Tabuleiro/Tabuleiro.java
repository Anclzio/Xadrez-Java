package Tabuleiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Pieces.*;
import enums.*;


public class Tabuleiro {
    private Map<Coordenada, Piece> pecas;

    public Tabuleiro() 
    {
        pecas = new HashMap<>();
    }

    public void adicionarPeca(Piece peca) 
    {
        pecas.put(peca.getCoordenada(), peca);
    }

    public void removePeca(Coordenada coordenada)
    {
        pecas.remove(coordenada);
    }

    public boolean estaVazio(Coordenada coordenada) 
    {
        return !pecas.containsKey(coordenada);
    }


    public Piece getPeca(Coordenada coordenada)
    {
        return pecas.get(coordenada);
     
    }


    public Piece getKing(Tabuleiro tabuleiro, Cor cor)
    {
        List<Piece> pecas = new ArrayList<>(tabuleiro.pecas.values());
        for(Piece peca : pecas)
        {
            if(peca.geTipo() == Tipo.REI && peca.getCor() == cor)
                return peca;
        }

        return null;
    }

    public List<Piece> getPecasInimigas(Cor corPeca) {
    List<Piece> pecasInimigas = new ArrayList<>();

    for (int linha = 0; linha < 8; linha++) {
        for (int coluna = 0; coluna < 8; coluna++) {
            Piece peca = getPeca(new Coordenada(linha, coluna));
            if (peca != null && peca.getCor() != corPeca) {
                pecasInimigas.add(peca);
            }
        }
    }

    return pecasInimigas;
}


    public static void populaTabuleiro(Tabuleiro tabuleiro)
    {
        Piece[] pecasBrancas = new Piece[]
        {
            new Bispo(new Coordenada(1, 3), Cor.BRANCO),
            new Bispo(new Coordenada(1, 6), Cor.BRANCO),
            new Cavalo(new Coordenada(1, 2), Cor.BRANCO),
            new Cavalo(new Coordenada(1, 7), Cor.BRANCO),
            new Torre(new Coordenada(1, 1), Cor.BRANCO),
            new Torre(new Coordenada(1, 8), Cor.BRANCO),
            new Rei(new Coordenada(1, 5), Cor.BRANCO),
            new Rainha(new Coordenada(1, 4), Cor.BRANCO),
            new Peao(new Coordenada(2, 1), Cor.BRANCO),
            new Peao(new Coordenada(2, 2), Cor.BRANCO),
            new Peao(new Coordenada(2, 3), Cor.BRANCO),
            new Peao(new Coordenada(2, 4), Cor.BRANCO),
            new Peao(new Coordenada(2, 5), Cor.BRANCO),
            new Peao(new Coordenada(2, 6), Cor.BRANCO),
            new Peao(new Coordenada(2, 7), Cor.BRANCO),
            new Peao(new Coordenada(2, 8), Cor.BRANCO),
            
                
        };

        for (Piece peca : pecasBrancas) 
        {
            tabuleiro.adicionarPeca(peca);
        }

        Piece[] pecasPretas = new Piece[]
        {
            new Bispo(new Coordenada(8, 3), Cor.PRETO),
            new Bispo(new Coordenada(8, 6), Cor.PRETO),
            new Cavalo(new Coordenada(8, 2), Cor.PRETO),
            new Cavalo(new Coordenada(8, 7), Cor.PRETO),
            new Torre(new Coordenada(8, 1), Cor.PRETO),
            new Torre(new Coordenada(8, 8), Cor.PRETO),
            new Rei(new Coordenada(8, 5), Cor.PRETO),
            new Rainha(new Coordenada(8, 4), Cor.PRETO),
            new Peao(new Coordenada(7, 1), Cor.PRETO),
            new Peao(new Coordenada(7, 2), Cor.PRETO),
            new Peao(new Coordenada(7, 3), Cor.PRETO),
            new Peao(new Coordenada(7, 4), Cor.PRETO),
            new Peao(new Coordenada(7, 5), Cor.PRETO),
            new Peao(new Coordenada(7, 6), Cor.PRETO),
            new Peao(new Coordenada(7, 7), Cor.PRETO),
            new Peao(new Coordenada(7, 8), Cor.PRETO),
            
                
        };

        for (Piece peca : pecasPretas) 
        {
            tabuleiro.adicionarPeca(peca);
        }


    }

    public boolean existePecaInimiga(Coordenada coordenadaDestino, Cor cor) 
    {
        Piece peca = getPeca(coordenadaDestino);

        if (peca != null) 
        {
            if (cor == Cor.BRANCO) 
            {
                if (peca.getCor() == Cor.PRETO)
                    return true;
            } 
            else if (peca.getCor() == Cor.BRANCO)
                    return true;   
        }
        return false;
    }

    public boolean estaEmCheck(Tabuleiro tabuleiro, Cor cor) 
    {   
        Piece king = getKing(tabuleiro, cor);
        List<Piece> PecasInimigas = this.getPecasInimigas(king.getCor());

        for(Piece piece : PecasInimigas)
        {
            List<Movimentos> listaDeMovimentos = piece.movimentosValidos(this);

            for(Movimentos movimento : listaDeMovimentos)
            {
                if(movimento.getDestino().equals(king.getCoordenada()))
                {
                    System.out.println("Check!!!!");
                    return true;
                }                   
            }
        }
        return false;
    }

        
}
