package Pieces;

import java.util.Objects;

public final class Coordenada implements Cloneable{
    private int linha;
    private int coluna;

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

    @Override
    public String toString()
    {   
        int aux = coluna + 96;
        
        return ((char)aux +""+ linha);
    }

    @Override
    public Coordenada clone() {
        try {
            Coordenada cloned = (Coordenada) super.clone();
            cloned.coluna = this.coluna;
            cloned.linha = this.linha;

            return cloned;
        } catch (CloneNotSupportedException e) {

            return null;
        }
    
    }
}
