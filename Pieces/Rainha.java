package Pieces;

import java.util.ArrayList;
import java.util.List;

import Tabuleiro.Movimentos;
import Tabuleiro.Tabuleiro;
import enums.Cor;
import enums.Tipo;

public class Rainha extends Piece{

    public Rainha(Coordenada coordenada, Cor corDaPeca) 
    {
        super(coordenada, corDaPeca);
        tipo = Tipo.RAINHA;
    }

    /*
     * O movimento da rainha combina o movimento de um bispo e de uma torre
     */
   @Override
    public boolean validaMovimento(int xFinal, int yFinal) 
    {
        int distanciaX = Math.abs(xFinal - this.coordenada.getLinha());
        int distanciaY = Math.abs(yFinal - this.coordenada.getColuna());

        if (xFinal == this.coordenada.getLinha() || yFinal == this.coordenada.getColuna() || distanciaX == distanciaY) {
            // Verificar se as coordenadas de destino estão dentro dos limites do tabuleiro
            return xFinal >= 1 && xFinal <= 8 && yFinal >= 1 && yFinal <= 8;
        }

        return false;
    }




    @Override
    public List<Movimentos> movimentosValidos(Tabuleiro tabuleiro) 
    {
        List<Movimentos> movimentosValidos = new ArrayList<>();

        int linhaAtual = this.coordenada.getLinha();
        int colunaAtual = this.coordenada.getColuna();

        // Movimento para cima
        for (int i = linhaAtual + 1; i <= 8; i++) {
            Coordenada coordenadaDestino = new Coordenada(i, colunaAtual);

            if (validaMovimento(i, colunaAtual) && tabuleiro.estaVazio(coordenadaDestino)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            } else if (validaMovimento(i, colunaAtual) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            } else {
                break;
            }
        }

        // Movimento para baixo
        for (int i = linhaAtual - 1; i >= 1; i--) {
            Coordenada coordenadaDestino = new Coordenada(i, colunaAtual);

            if (validaMovimento(i, colunaAtual) && tabuleiro.estaVazio(coordenadaDestino)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            } else if (validaMovimento(i, colunaAtual) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            } else {
                break;
            }
        }

        // Movimento para esquerda
        for (int j = colunaAtual - 1; j >= 1; j--) {
            Coordenada coordenadaDestino = new Coordenada(linhaAtual, j);

            if (validaMovimento(linhaAtual, j) && tabuleiro.estaVazio(coordenadaDestino)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            } else if (validaMovimento(linhaAtual, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            } else {
                break;
            }
        }

        // Movimento para direita
        for (int j = colunaAtual + 1; j <= 8; j++) {
            Coordenada coordenadaDestino = new Coordenada(linhaAtual, j);

            if (validaMovimento(linhaAtual, j) && tabuleiro.estaVazio(coordenadaDestino)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            } else if (validaMovimento(linhaAtual, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            } else {
                break;
            }
        }

        // Movimentos na diagonal superior direita
        for (int i = linhaAtual + 1, j = colunaAtual + 1; i <= 8 && j <= 8; i++, j++) {
            Coordenada coordenadaDestino = new Coordenada(i, j);

            if (validaMovimento(i, j) && tabuleiro.estaVazio(coordenadaDestino)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            } else if (validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            } else {
                break;
            }
        }

        // Movimentos na diagonal superior esquerda
        for (int i = linhaAtual + 1, j = colunaAtual - 1; i <= 8 && j >= 1; i++, j--) {
            Coordenada coordenadaDestino = new Coordenada(i, j);

            if (validaMovimento(i, j) && tabuleiro.estaVazio(coordenadaDestino)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            } else if (validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            } else {
                break;
            }
        }

        // Movimentos na diagonal inferior direita
        for (int i = linhaAtual - 1, j = colunaAtual + 1; i >= 1 && j <= 8; i--, j++) {
            Coordenada coordenadaDestino = new Coordenada(i, j);

            if (validaMovimento(i, j) && tabuleiro.estaVazio(coordenadaDestino)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            } else if (validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            } else {
                break;
            }
        }

        // Movimentos na diagonal inferior esquerda
        for (int i = linhaAtual - 1, j = colunaAtual - 1; i >= 1 && j >= 1; i--, j--) {
            Coordenada coordenadaDestino = new Coordenada(i, j);

            if (validaMovimento(i, j) && tabuleiro.estaVazio(coordenadaDestino)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino));
            } else if (validaMovimento(i, j) && tabuleiro.existePecaInimiga(coordenadaDestino, corDaPeca)) {
                movimentosValidos.add(new Movimentos(this.coordenada, coordenadaDestino, tabuleiro.getPeca(coordenadaDestino)));
                break;
            } else {
                break;
            }
        }

        return movimentosValidos;
    }

}

    

