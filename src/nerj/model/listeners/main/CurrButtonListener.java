package nerj.model.listeners.main;

import nerj.view.frames.CurrFrame;
import nerj.view.frames.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CurrButtonListener implements ActionListener{
    public CurrButtonListener(MainFrame frame){
        this.conn = frame.getConn();
        this.isAdmin = frame.isAdmin();
    }

    public void actionPerformed(ActionEvent e) {
        CurrFrame currFrame = new CurrFrame(conn, isAdmin);
        currFrame.setVisible(true);
    }

    private Connection conn;
    private boolean isAdmin;
}
