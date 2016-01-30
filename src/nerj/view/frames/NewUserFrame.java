package nerj.view.frames;

import nerj.model.listeners.newuser.AbortButtonListener;
import nerj.model.listeners.newuser.OKButtonListener;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class NewUserFrame extends JFrame{
    public NewUserFrame(UsersFrame parent){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        URL path = NewUserFrame.class.getResource("/img/currency.png");
        setIconImage(new ImageIcon(path).getImage());
        setTitle("Добавление оператора");

        famLabel = new JLabel("Фамилия: ");
        nameLabel = new JLabel("Имя: ");
        positionLabel = new JLabel("Занимаемая должность: ");
        loginLabel = new JLabel("Логин: ");
        passLabel = new JLabel("Пароль: ");
        famField = new JTextField();
        nameField = new JTextField();
        positionField = new JTextField(20);
        loginField = new JTextField();
        passField = new JTextField();
        okButton = new JButton("     OK     ");
        okButton.addActionListener(new OKButtonListener(this, parent));
        abortButton = new JButton("Отмена");
        abortButton.addActionListener(new AbortButtonListener(this));


        setLayout(new BorderLayout(10, 10));
        add(setupUI(), BorderLayout.CENTER);
        pack();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(NewUserFrame.this);
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

    private Box setupUI(){
        Box row1 = Box.createHorizontalBox();
        row1.add(Box.createHorizontalStrut(10));
        row1.add(famLabel);
        row1.add(Box.createHorizontalStrut(10));
        row1.add(famField);
        row1.add(Box.createHorizontalStrut(30));
        row1.add(nameLabel);
        row1.add(Box.createHorizontalStrut(10));
        row1.add(nameField);
        row1.add(Box.createHorizontalStrut(10));
        Box row2 = Box.createHorizontalBox();
        row2.add(Box.createHorizontalStrut(10));
        row2.add(loginLabel);
        row2.add(Box.createHorizontalStrut(10));
        row2.add(loginField);
        row2.add(Box.createHorizontalStrut(30));
        row2.add(passLabel);
        row2.add(Box.createHorizontalStrut(10));
        row2.add(passField);
        row2.add(Box.createHorizontalStrut(10));
        Box row3 = Box.createHorizontalBox();
        row3.add(okButton);
        row3.add(Box.createHorizontalStrut(10));
        row3.add(abortButton);
        Box row4 = Box.createHorizontalBox();
        row4.add(Box.createHorizontalStrut(10));
        row4.add(positionLabel);
        row4.add(Box.createHorizontalStrut(10));
        row4.add(positionField);
        row4.add(Box.createHorizontalStrut(10));
        Box col = Box.createVerticalBox();
        col.add(Box.createVerticalStrut(10));
        col.add(row1);
        col.add(Box.createVerticalStrut(10));
        col.add(row4);
        col.add(Box.createVerticalStrut(10));
        col.add(row2);
        col.add(Box.createVerticalStrut(20));
        col.add(row3);
        col.add(Box.createVerticalStrut(10));
        return col;
    }

    public JTextField getFamField() {
        return famField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPositionField() {
        return positionField;
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JTextField getPassField() {
        return passField;
    }

    private JLabel famLabel;
    private JLabel nameLabel;
    private JLabel positionLabel;
    private JLabel loginLabel;
    private JLabel passLabel;
    private JTextField famField;
    private JTextField nameField;
    private JTextField positionField;
    private JTextField loginField;
    private JTextField passField;
    private JButton okButton;
    private JButton abortButton;
    
    Logger logger = Logger.getLogger(this.getClass());
}
