package nerj.view.frames;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class AboutFrame extends JFrame{
    public AboutFrame(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setSize(350, 232);
        setLayout(new BorderLayout());
        setResizable(false);
        path = AboutFrame.class.getResource("/img/currency.png");
        setIconImage(new ImageIcon(path).getImage());
        setTitle("О программе");

        panel = new JPanel(){
            public void paint(Graphics g){
                path = AboutFrame.class.getResource("/img/exchange.jpg");
                Image background = Toolkit.getDefaultToolkit().getImage(path);
                g.drawImage(background, 0, 0, 350, 232, this);
                super.paint(g);
            }
        };
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        path = AboutFrame.class.getResource("/img/currency.png");
        imgLabel = new JLabel(new ImageIcon(path));
        nameLabel = new JLabel("<html><p align='center'><big>Обмен валют 0.1</big><br>2011 &reg; Developed by <i><b>Влад Липень</b></i> aka NeRj</p>", SwingConstants.CENTER);
        Box row1 = Box.createHorizontalBox();
        row1.add(Box.createHorizontalStrut(20));
        row1.add(imgLabel);
        Box row2 = Box.createHorizontalBox();
        row2.add(Box.createHorizontalStrut(5));
        row2.add(nameLabel);
        Box col = Box.createVerticalBox();
        col.add(row1);
        col.add(Box.createVerticalStrut(5));
        col.add(row2);
        
        panel.add(col);
        add(panel);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(AboutFrame.this);
        } catch (ClassNotFoundException clEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            logger.error("Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        } catch (InstantiationException instEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            logger.error("Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        } catch (IllegalAccessException accEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            logger.error("Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        } catch (UnsupportedLookAndFeelException lafEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            logger.error("Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        }
    }
    
    private JPanel panel;
    private JLabel imgLabel;
    private JLabel nameLabel;

    private URL path;

    private Logger logger = Logger.getLogger(this.getClass());
}
