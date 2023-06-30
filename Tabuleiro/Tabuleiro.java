package Tabuleiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import Pieces.*;
import enums.*;


public class Tabuleiro {
    private Map<Coordenada, Piece> pecas;

    public Map<Coordenada, Piece> getPecas() 
    {
        return pecas;
    }

    public Tabuleiro() 
    {
        pecas = new HashMap<Coordenada, Piece>();
    }

    public Tabuleiro(Tabuleiro tabuleiro) 
    {
        this.pecas = new HashMap<Coordenada,Piece>();
        for(Map.Entry<Coordenada, Piece> entry : tabuleiro.getPecas().entrySet())
        {
            this.pecas.put(entry.getKey(), tabuleiro.getPeca(entry.getKey()).clone());
        }
        
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

    public boolean estaVazio(int linha, int coluna) 
    {
        Coordenada coordenada = new Coordenada(linha, coluna);

        return estaVazio(coordenada);
    }

    public Piece getPeca(Coordenada coordenada)
    {
        return pecas.get(coordenada);
     
    }

    public Piece getPeca(int linha, int coluna)
    {
        Coordenada coordenada = new Coordenada(linha, coluna);

        return getPeca(coordenada);
    }

    public Piece getKing(Tabuleiro tabuleiro, Cor cor)
    {
        List<Piece> pecas = new ArrayList<>(this.pecas.values());
        for(Piece peca : pecas)
        {
            if(peca instanceof Rei && peca.getCor() == cor)
                return (Rei) peca;
        }

        return null;
    }

    public Piece getTorre(Coordenada coordenada) 
    {
        List<Piece> pecas = new ArrayList<>(this.pecas.values());
        for (Piece peca : pecas) 
        {
            if (peca instanceof Torre && peca.getCoordenada().equals(coordenada))  
                return peca;               
        }

        return null;
    }

    public List<Piece> getPecasInimigas(Cor corPeca) 
    {
        List<Piece> pecasInimigas = new ArrayList<>();

        for (Piece peca : pecas.values()) 
        {
            if (peca.getCor() != corPeca)
            {
                pecasInimigas.add(peca);
            }
        }

        return pecasInimigas;
    }

    public boolean existePecaInimiga(Coordenada coordenadaDestino, Cor cor) 
    {
        Piece peca = getPeca(coordenadaDestino);

        return peca!= null && peca.getCor() != cor;
    }

    public boolean estaEmCheck(Tabuleiro tabuleiro, Cor cor) 
    {   
        Piece king = getKing(this, cor);
        List<Piece> PecasInimigas = getPecasInimigas(king.getCor());

        for(Piece peca : PecasInimigas)
        {
            List<Movimentos> listaDeMovimentos = peca.movimentosValidos(tabuleiro);

            for(Movimentos movimento : listaDeMovimentos)
            {
                if(movimento.getDestino().equals(king.getCoordenada())) 
                    return true;                 
            }
        }
        return false;
    }
    
    /*
     * Inicializa o tabuleiro de xadrez inserindo todas as peças
       em suas posições iniciais
     */
    public static void populaTabuleiro(Tabuleiro tabuleiro)
    {
        Piece[] pecasBrancas = new Piece[]
        {

             new Bispo(new Coordenada(1, 3), Cor.BRANCO),
             new Bispo(new Coordenada(1, 6), Cor.BRANCO),
             new Cavalo(new Coordenada(1, 2), Cor.BRANCO),
             new Cavalo(new Coordenada(1, 7), Cor.BRANCO),
             new Rainha(new Coordenada(1, 4), Cor.BRANCO),
             new Peao(new Coordenada(2, 1), Cor.BRANCO),
             new Peao(new Coordenada(2, 3), Cor.BRANCO),
             new Peao(new Coordenada(2, 4), Cor.BRANCO),
             new Peao(new Coordenada(2, 5), Cor.BRANCO),
             new Peao(new Coordenada(2, 6), Cor.BRANCO),
             new Peao(new Coordenada(2, 7), Cor.BRANCO),
             new Peao(new Coordenada(2, 8), Cor.BRANCO),
             new Torre(new Coordenada(1, 1), Cor.BRANCO),
             new Torre(new Coordenada(1, 8), Cor.BRANCO),
   
            new Rei(new Coordenada(1, 5), Cor.BRANCO),
            new Peao(new Coordenada(2, 2), Cor.BRANCO),
             
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
            new Rainha(new Coordenada(8, 4), Cor.PRETO),
            new Peao(new Coordenada(7, 1), Cor.PRETO),
            new Peao(new Coordenada(7, 2), Cor.PRETO),
            new Peao(new Coordenada(7, 3), Cor.PRETO),
            new Peao(new Coordenada(7, 4), Cor.PRETO),
            new Peao(new Coordenada(7, 5), Cor.PRETO),
            new Peao(new Coordenada(7, 6), Cor.PRETO),
            new Peao(new Coordenada(7, 7), Cor.PRETO),
            new Torre(new Coordenada(8, 1), Cor.PRETO),
            new Torre(new Coordenada(8, 8), Cor.PRETO),
    
            new Rei(new Coordenada(8, 5), Cor.PRETO),
            new Peao(new Coordenada(7, 8), Cor.PRETO),     
        };

        for (Piece peca : pecasPretas) 
        {
            tabuleiro.adicionarPeca(peca);
        }


    }
    
    //Verifica se existe alguma peca na mesma linha entre duas pecas 
    public static boolean existePecaEntre(Piece peca1 , Piece peca2, Tabuleiro tabuleiro)
    {   
        int colunaPeca1 = peca1.getCoordenada().getColuna();
        int colunaPeca2 = peca2.getCoordenada().getColuna();
        int direcao = colunaPeca1 < colunaPeca2 ? 1 : -1;

        for (int coluna = colunaPeca1 + direcao; coluna != colunaPeca2; coluna += direcao) 
        {
            Coordenada coordenada = new Coordenada(peca1.getCoordenada().getLinha(), coluna);
            if (!tabuleiro.estaVazio(coordenada)) 
                return true; // Se achar alguma peça sai do metodo   
        } 
        return false;
    }
}
