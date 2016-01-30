package nerj.view.frames;

import nerj.model.listeners.login.CancelButtonListener;
import nerj.model.listeners.login.EnterKeyListener;
import nerj.model.listeners.login.LoginButtonListener;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;

public class LoginFrame extends JFrame {
    public LoginFrame(final Connection conn){
        this.conn = conn;
        setResizable(false);
        URL path = LoginFrame.class.getResource("/img/currency.png");
        setIconImage(new ImageIcon(path).getImage());
        setTitle("Авторизация");

        panel = new JPanel(new GridLayout(4,0,5,5));
        loginLabel = new JLabel("Логин:   ");
        passLabel = new JLabel("Пароль: ");
        loginField = new JTextField(20);
        passField = new JPasswordField(20);
        enterButton = new JButton("Вход");
        cancelButton = new JButton("Отмена");
        wrongLabel = new JLabel("<html><div style=\"color:red; font-style:italic\">" +
                "&nbsp;Вы ввели неверный логин и/или пароль. Попробуйте еще раз...</div>");
        wrongLabel.setVisible(false);

        EnterKeyListener enterListener = new EnterKeyListener(this);
        loginField.addKeyListener(enterListener);
        passField.addKeyListener(enterListener);
        enterButton.addActionListener(new LoginButtonListener(this));
        cancelButton.addActionListener(new CancelButtonListener());

        Box row1 = Box.createHorizontalBox();
        row1.add(loginLabel);
        row1.add(Box.createHorizontalStrut(11));
        row1.add(loginField);
        Box row2 = Box.createHorizontalBox();
        row2.add(passLabel);
        row2.add(Box.createHorizontalStrut(10));
        row2.add(passField);
        Box row3 = Box.createHorizontalBox();
        row3.add(enterButton);
        row3.add(cancelButton);

        panel.setBorder(new EmptyBorder(10,10,10,10));
        panel.add(row1);
        panel.add(row2);
        panel.add(wrongLabel);
        panel.add(row3);
        add(panel);
        pack();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation(screenWidth / 2 - getWidth() / 2, screenHeight / 2 - getHeight() / 2);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(LoginFrame.this);
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

    public JButton getEnterButton() {
        return enterButton;
    }

    public void setWrongLabel(JLabel wrongLabel) {
        this.wrongLabel = wrongLabel;
    }

    public JLabel getWrongLabel() {
        return wrongLabel;
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JPasswordField getPassField() {
        return passField;
    }

    public Connection getConn() {
        return conn;
    }

    private Connection conn;
    private JPanel panel;
    private JLabel loginLabel;
    private JLabel passLabel;
    private JLabel wrongLabel;
    private JTextField loginField;
    private JPasswordField passField;
    private JButton enterButton;
    private JButton cancelButton;

    private Logger logger = Logger.getLogger(this.getClass());
}
