package enums;

public enum Tipo {

    BISPO('B'), CAVALO('C'), RAINHA('D'), REI('R'), TORRE('T'), PEAO('P');
    
    private char notacao;
    
    Tipo(char notacao)
    {
        this.notacao = notacao;
    }
    


    public char getNotacao()
    {
        return notacao;
    }
}
