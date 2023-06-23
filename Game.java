import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import Tabuleiro.*;
import Pieces.*;
import enums.Cor;

public class Game{
    
    public static void main(String[] args) {

        System.out.println("começou");
        
        Color CASAS_CLARAS2 = new Color(236, 201, 163);
        Color CASAS_ESCURAS2 = new Color(211, 162, 107);

        Tabuleiro tabuleiro = new Tabuleiro();
        Tabuleiro.populaTabuleiro(tabuleiro);

        final boolean[] turnoDasBrancas = {true};
        final boolean[] jogadorAtualEmCheck = {false};


        JFrame frame = new JFrame();
        JPanel panel = new JPanel()
        {
            @Override
            public void paint(Graphics g) 
            {
                boolean color = true;   
                for (int linha = 0; linha < 8; linha++) 
                {
                    for (int coluna = 0; coluna < 8; coluna++) 
                    {   
                        if (color) 
                            g.setColor(CASAS_CLARAS2);
                        else
                            g.setColor(CASAS_ESCURAS2);
                        
                        g.fillRect(coluna * 64, linha * 64, 64, 64);
                        
                        Piece peca = tabuleiro.getPeca(new Coordenada(linha + 1, coluna + 1));
                        
                        if (peca != null) 
                        {
                            Image imagem = getImagemPeca(peca);
                            g.drawImage(imagem, coluna * 64, linha * 64, 64, 64, null);
                        }
                        
                        color = !color;
                    }
                    
                    color = !color;
                }
            }
        };


        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        frame.add(panel);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        frame.addMouseListener(new MouseListener() 
        {
            Piece pecaSelecionada = null;
            @Override
            public void mouseClicked(MouseEvent e) 
            {   
            
                Coordenada coordenada = new Coordenada((1 + e.getY()/64), (1 + e.getX()/64));
                

                if(pecaSelecionada == null)
                {   
                    Piece pecaAux = tabuleiro.getPeca(coordenada);

                    if(pecaAux != null)
                    {
                        if(turnoDasBrancas[0])
                        {
                            if(pecaAux.getCor() == Cor.BRANCO)
                            {
                                pecaSelecionada = tabuleiro.getPeca(coordenada);
                                System.out.println("Selecionei a peca " + pecaSelecionada);
                                System.out.println(pecaSelecionada.movimentosValidos(tabuleiro));
                            }
                            else
                                System.out.println("Turno das brancas");
                        }
                        else
                        {
                            if(pecaAux.getCor() == Cor.PRETO)
                            {
                                pecaSelecionada = tabuleiro.getPeca(coordenada);
                                System.out.println("Selecionei a peca " + pecaSelecionada);
                                System.out.println(pecaSelecionada.movimentosValidos(tabuleiro));
                            }
                            else
                                System.out.println("Turno das pretas");
                        }
                    }
                    
                   
                }   
                else
                {
                        Movimentos movimento = new Movimentos(pecaSelecionada.getCoordenada(), coordenada);
                        System.out.println();
                        System.out.println(movimento);
    
                        if(pecaSelecionada.movimentosValidos(tabuleiro).contains(movimento))
                        {
                            movimento.moverPeca(movimento, tabuleiro);
                            System.out.println("sucesso");
                            turnoDasBrancas[0] = !turnoDasBrancas[0];
                            jogadorAtualEmCheck[0] = tabuleiro.estaEmCheck(tabuleiro, turnoDasBrancas[0] ? Cor.BRANCO : Cor.PRETO);
                            if (jogadorAtualEmCheck[0]) 
                            {
                                System.out.println("O jogador atual está em cheque!");
                            }
    
                            frame.repaint();
                            pecaSelecionada = null;
                        }
                        else 
                        {
                            System.out.println("fracasso");
                            frame.repaint();
                            pecaSelecionada = null;
                        }
                    }
                    
                
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    
    }   

 

    private static Image getImagemPeca(Piece peca) 
    {
    String imagePath = null;

    if (peca instanceof Peao && peca.getCor() == Cor.BRANCO)
        imagePath = "/Images/peao.png";
    if (peca instanceof Bispo && peca.getCor() == Cor.BRANCO)
        imagePath = "/Images/bispo.png";
    if (peca instanceof Torre && peca.getCor() == Cor.BRANCO)
        imagePath = "/Images/torre.png";
    if (peca instanceof Rainha && peca.getCor() == Cor.BRANCO)
        imagePath = "/Images/rainha.png";
    if (peca instanceof Rei && peca.getCor() == Cor.BRANCO)
        imagePath = "/Images/rei.png";
    if (peca instanceof Cavalo && peca.getCor() == Cor.BRANCO)
        imagePath = "/Images/cavalo.png";

    if (peca instanceof Peao && peca.getCor() == Cor.PRETO)
        imagePath = "/Images/peao2.png";
    if (peca instanceof Bispo && peca.getCor() == Cor.PRETO)
        imagePath = "/Images/bispo2.png";
    if (peca instanceof Torre && peca.getCor() == Cor.PRETO)
        imagePath = "/Images/torre2.png";
    if (peca instanceof Rainha && peca.getCor() == Cor.PRETO)
        imagePath = "/Images/rainha2.png";
    if (peca instanceof Rei && peca.getCor() == Cor.PRETO)
        imagePath = "/Images/rei2.png";
    if (peca instanceof Cavalo && peca.getCor() == Cor.PRETO)
        imagePath = "/Images/cavalo2.png";

    if (imagePath != null) 
    {
        Image image = ImageLoader.loadImage(imagePath).getImage();
        if (image != null) 
            return image;
        else
            System.out.println("Não foi possível carregar a imagem: " + imagePath);

    } 
    else
        System.out.println("Imagem não encontrada para a peça: " + peca);
    

    return null;
}



    public class ImageLoader 
    {
        public static ImageIcon loadImage(String path) 
        {
            ImageIcon icon = new ImageIcon(ImageLoader.class.getResource(path));
            return icon;
        }
    }


}
