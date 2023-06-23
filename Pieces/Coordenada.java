package Pieces;

import java.util.Objects;

public final class Coordenada {
    private final int linha;
    private final int coluna;

    public Coordenada(int linha, int coluna) 
    {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() 
    {
        return linha;
    }

    public int getColuna() 
    {
        return coluna;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coordenada outraCoordenada = (Coordenada) obj;
        return linha == outraCoordenada.linha && coluna == outraCoordenada.coluna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(linha, coluna);
    }

}
