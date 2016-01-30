package nerj.model.listeners.login;

import nerj.view.frames.LoginFrame;
import nerj.view.frames.MainFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginButtonListener implements ActionListener{
    public LoginButtonListener(LoginFrame frame){
        this.frame = frame;
        this.conn = frame.getConn();
    }

    public void actionPerformed(ActionEvent e) {
        JLabel wrong = frame.getWrongLabel();
        if (frame.getLoginField().getText().isEmpty() || frame.getPassField().getPassword().length == 0)
            wrong.setVisible(true);
        else if (isUser()){
            MainFrame mainFrame = new MainFrame(conn, login, isAdmin);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
            frame.dispose();
        }
        else wrong.setVisible(true);
        frame.setWrongLabel(wrong);
    }

    private boolean isUser(){
        char[] pass = {};
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM users WHERE login=?");
            query.setString(1, frame.getLoginField().getText());
            ResultSet queryResult = query.executeQuery();
            while (queryResult.next()){
                pass = queryResult.getString("pass").toCharArray();
                if (queryResult.getInt("isAdmin") == 1)
                    isAdmin = true;
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Произошла ошибка при работе с базой данных\nДанные не были сохранены");
            logger.error("Произошла ошибка при работе с базой данных\nДанные не были сохранены");
        }
        if (Arrays.equals(pass, frame.getPassField().getPassword())){
            login = frame.getLoginField().getText();
            return true;
        }
        else return false;
    }

    private LoginFrame frame;
    private Connection conn;
    private boolean isAdmin;
    private String login;

    private Logger logger = Logger.getLogger(this.getClass());
}
