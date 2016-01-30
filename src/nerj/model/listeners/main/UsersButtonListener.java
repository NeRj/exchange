package nerj.model.listeners.main;

import nerj.view.frames.MainFrame;
import nerj.view.frames.UsersFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class UsersButtonListener implements ActionListener{
    public UsersButtonListener(MainFrame frame){
        this.conn = frame.getConn();
    }

    public void actionPerformed(ActionEvent e) {
        UsersFrame usersFrame = new UsersFrame(conn);
        usersFrame.setVisible(true);
    }

    private Connection conn;
}
