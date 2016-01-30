package nerj;

import nerj.controller.connect.Connect;
import nerj.view.frames.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class ExchangeStart {
    public static void main(String[] args){
        final Connection conn = new Connect().getConnection();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginFrame loginFrame = new LoginFrame(conn);
                loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginFrame.setVisible(true);
            }
        });
    }
}
