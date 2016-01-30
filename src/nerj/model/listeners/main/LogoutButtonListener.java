package nerj.model.listeners.main;

import nerj.view.frames.LoginFrame;
import nerj.view.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LogoutButtonListener implements ActionListener{
    public LogoutButtonListener(MainFrame frame){
        this.frame = frame;
        this.conn = frame.getConn();
    }

    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        LoginFrame loginFrame = new LoginFrame(conn);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }

    private MainFrame frame;
    private Connection conn;
}
