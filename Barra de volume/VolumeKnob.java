import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VolumeKnob extends JPanel {
    private int width = 300;
    private int height = 300;
    private Point center = new Point(width / 2, height / 2);
    private int radius = 120;
    private double angle = 0; // Ângulo inicial
    private int volume = 0; // Volume inicial (0-100)

    private JLabel volumeLabel;

    public VolumeKnob() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE); // Fundo branco

        // Criando o label de volume com cor preta e posição ajustada
        volumeLabel = new JLabel("Volume: " + volume + "%");
        volumeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        volumeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        volumeLabel.setBounds(center.x - 70, center.y - 60, 140, 30); // Posição ajustada para ficar mais para cima
        volumeLabel.setForeground(Color.BLACK); // Cor do texto preta

        add(volumeLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                updateAngle(e.getPoint());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                updateAngle(e.getPoint());
            }
        });
    }

    private void updateAngle(Point p) {
        int dx = p.x - center.x;
        int dy = center.y - p.y;

        // Calcula o ângulo entre o centro e a posição do mouse
        double newAngle = Math.toDegrees(Math.atan2(dx, dy)) % 360;
        if (newAngle < 0) newAngle += 360; // Garantir ângulo positivo

        // Se o volume for menor que 100%, permite o aumento
        if (volume < 100) {
            angle = newAngle;
        }

        // Calcula o volume com base no ângulo
        volume = (int) ((angle / 360) * 100);

        // Se o volume atingir 100%, o ponteiro vai parar em 360º
        if (volume >= 100) {
            volume = 100;
            angle = 360; // O ponteiro fica fixo em 360 graus
        }

        // Restringe o ângulo para 0 - 359 (não pode ultrapassar 360)
        if (volume == 100 && angle >= 360) {
            angle = 360; // O ponteiro não pode passar de 360
        }

        // Permite a rotação anti-horária (diminuindo o volume)
        if (volume == 100 && newAngle < 360) {
            angle = newAngle;
            volume = (int) ((angle / 360) * 100);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Desenha o fundo branco (painel)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Desenha o círculo do knob em azul
        g2d.setColor(Color.BLUE); // Azul
        g2d.fillOval(10, 10, 280, 280);

        // Desenha a linha do indicador de volume em vermelho
        g2d.setColor(Color.RED); // Vermelho
        int endX = (int) (center.x + radius * Math.sin(Math.toRadians(angle)));
        int endY = (int) (center.y - radius * Math.cos(Math.toRadians(angle)));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(center.x, center.y, endX, endY);

        // Atualiza o texto do volume em preto
        volumeLabel.setText("Volume: " + volume + "%");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Controle de Volume Circular");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        VolumeKnob volumeKnob = new VolumeKnob();
        volumeKnob.setBounds(50, 50, 300, 300);
        frame.add(volumeKnob);

        frame.setVisible(true);
    }
}
