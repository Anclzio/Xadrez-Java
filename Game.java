import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import Tabuleiro.*;
import Pieces.*;
import enums.Cor;


public class Game {

        private static final Color CASAS_CLARAS2 = new Color(235, 202, 165);
        private static final Color CASAS_ESCURAS2 = new Color(212, 164, 108);
        private static final Color CASAS_DESTAQUE = new Color(180, 204, 204, 127);
        private Tabuleiro tabuleiro = new Tabuleiro();
        private boolean turnoDasBrancas;
        private boolean jogadorAtualEmCheck;
        private List<Coordenada> movimentosValidosDestaque = new ArrayList<>();
        JFrame frame;

        

        public Game ()
        {
            Tabuleiro.populaTabuleiro(tabuleiro);
            turnoDasBrancas = true;
            jogadorAtualEmCheck = false;

            frame = new JFrame();

            /*
             * Desenha a GUI
             */
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
                            Coordenada coordenadaAtual = new Coordenada(linha + 1, coluna + 1);

                            if (color) 
                                g.setColor(CASAS_CLARAS2);
                            else
                                g.setColor(CASAS_ESCURAS2);
                            
                            g.fillRect(coluna * 64, linha * 64, 64, 64);

                            if (movimentosValidosDestaque != null && movimentosValidosDestaque.contains(coordenadaAtual)) 
                            {
                                g.setColor(CASAS_DESTAQUE);
                                g.fillRect(coluna * 64, linha * 64, 64, 64);
                            }
                            
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
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            frame.addMouseListener(new MouseAdapter() 
            {
                private Piece pecaSelecionada;

                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    Coordenada coordenada = new Coordenada((1 + e.getY() / 64), (1 + e.getX() / 64));

                    if (pecaSelecionada == null) 
                    {
                        Piece pecaAux = tabuleiro.getPeca(coordenada);
                        
                        if (pecaAux != null) 
                        {
                            //Verifica se o turno do jogador é da mesma cor da peça escolhida
                            if ((turnoDasBrancas && pecaAux.getCor() == Cor.BRANCO) || (!turnoDasBrancas && pecaAux.getCor() == Cor.PRETO)) 
                            {
                                pecaSelecionada = tabuleiro.getPeca(coordenada);
                                System.out.println(pecaSelecionada);

                                List<Movimentos> movimentosValidos = pecaSelecionada.movimentosValidos(tabuleiro);

                                //limpa os dstaques de movimento da tela quando eles existem
                                if (movimentosValidosDestaque != null)
                                    movimentosValidosDestaque.clear();

                                /*Faz uma verificação para saber se o jogador esta em xeque em 
                                  cada movimento valido para inserir o destaque de movimento
                                  na GUI
                                */ 
                                for (Movimentos movimento : movimentosValidos)
                                {
                                    Tabuleiro tabuleiroTemporario = new Tabuleiro(tabuleiro);
                                    Movimentos.moverPeca(movimento, tabuleiroTemporario);

                                    boolean jogadorEmCheck = tabuleiroTemporario.estaEmCheck(tabuleiroTemporario, turnoDasBrancas 
                                                                                            ? Cor.BRANCO : Cor.PRETO);

                                    if (!jogadorEmCheck)
                                        movimentosValidosDestaque.add(movimento.getDestino());
                                    
                                } 
                                
                                frame.repaint();
                            } 
                            else 
                                System.out.println("Não é a vez do jogador atual");
                            
                        }
                    } 
                    else //Existindo peça selecionada
                    {                                                                                           
                        Movimentos movimento = new Movimentos(pecaSelecionada.getCoordenada(), coordenada);

                        movimentosValidosDestaque.clear();

                        /*  Cria um tabuleiro temporario para verificar se 
                            o movimento deixa o jogador em xeque, em caso positivo
                            imprime uma msg no console, em caso negativo realiza o movimento
                            no tabuleiro orignial e verifica se o movimento deixou o adversario
                            em xeque.
                        */        
                        if (pecaSelecionada.movimentosValidos(tabuleiro).contains(movimento)) 
                        {   
                            /*
                             * Depois de muito esforço e sofrimento consegui identificar o motivo
                             * pelo qual não conseguia fazer roque
                             */
                            for(Movimentos mov : pecaSelecionada.movimentosValidos(tabuleiro))
                            {
                                if(mov.equals(movimento))
                                {   
                                    movimento.ehRoque = mov.ehRoque;
                                    movimento.ehRoqueGrande = mov.ehRoqueGrande;
                                    movimento.ehPromocao = mov.ehPromocao;
                                }
                                    
                            }
                            Tabuleiro tabuleiroTemporario = new Tabuleiro(tabuleiro);
                            Movimentos.moverPeca(movimento, tabuleiroTemporario);
                            boolean jogadorEmCheck = tabuleiroTemporario.estaEmCheck(tabuleiroTemporario, turnoDasBrancas 
                                                                                    ? Cor.BRANCO : Cor.PRETO);

                            if (!jogadorEmCheck) 
                            {
                                Movimentos.moverPeca(movimento, tabuleiro);
                                frame.repaint();
                                if(!verificarXequeMate(turnoDasBrancas ? Cor.PRETO : Cor.BRANCO))
                                {
                                    turnoDasBrancas = !turnoDasBrancas;
                                    jogadorAtualEmCheck = tabuleiro.estaEmCheck(tabuleiro,turnoDasBrancas ? Cor.BRANCO : Cor.PRETO);
                                    
                                    if (jogadorAtualEmCheck)
                                        System.out.println("O jogador atual está em cheque!");
                                }
                               
                               
                                pecaSelecionada = null;
                            } 
                            else 
                            {
                                System.out.println("Movimento inválido. Coloca o jogador em cheque.");
                                frame.repaint();
                                pecaSelecionada = null;
                            }
                        } 
                        else 
                        {
                            System.out.println("Movimento inválido");
                            frame.repaint();
                            pecaSelecionada = null;
                        }
                    }
                }
            });

        }

    public boolean verificarXequeMate(Cor corJogadorAtual) 
    {
        if (tabuleiro.estaEmCheck(tabuleiro, corJogadorAtual)) 
        {
            List<Piece> pecasDoJogador = tabuleiro.getPecasDoJogador(corJogadorAtual);
            
            for (Piece peca : pecasDoJogador) 
            {
                List<Movimentos> movimentosValidos = peca.movimentosValidos(tabuleiro);
                
                for (Movimentos movimento : movimentosValidos) 
                {
                    Tabuleiro tabuleiroTemporario = new Tabuleiro(tabuleiro);
                    Movimentos.moverPeca(movimento, tabuleiroTemporario);
                    
                    if (!tabuleiroTemporario.estaEmCheck(tabuleiroTemporario, corJogadorAtual))
                        return false;
                }
            }
            
            System.out.println("Xeque-mate! O jogador " + corJogadorAtual + " perdeu o jogo.");
            
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja reiniciar o jogo?", "Fim de jogo", JOptionPane.YES_NO_OPTION);
            
            if (resposta == JOptionPane.YES_OPTION) 
            {
                reiniciarJogo();
                return true;
            } 
            else 
            {
                frame.dispose();
                System.exit(0);
            }
        }
        return false;
    }
   public void reiniciarJogo() 
   {
  
        tabuleiro = new Tabuleiro();
        Tabuleiro.populaTabuleiro(tabuleiro);
        turnoDasBrancas = true;
        jogadorAtualEmCheck = false;
        movimentosValidosDestaque.clear();

        frame.repaint();
    }

  





    public static void main(String[] args) 
    {
        new Game();
    
    }

    /*
     * Recebe uma peca e retorna a imagem que representa aquela Peca
     */
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
