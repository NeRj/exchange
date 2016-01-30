package nerj.model.listeners.users;

import nerj.view.frames.UsersFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DelUserButtonListener implements ActionListener{
    public DelUserButtonListener(UsersFrame frame){
        this.frame = frame;
        this.conn = frame.getConn();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (frame.getTable().getSelectedRow() != -1){
            try {
                PreparedStatement delete1 = conn.prepareStatement("DELETE FROM users WHERE login = ?");
                delete1.setString(1, frame.getTable().getValueAt(frame.getTable().getSelectedRow(), 3).toString());
                delete1.executeUpdate();
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null,"Произошла ошибка при работе с базой данных\nДанные не были сохранены");
                logger.error("Произошла ошибка при работе с базой данных\nДанные не были сохранены");
            }
            frame.showUsers();
        }
    }
    
    private UsersFrame frame;
    private Connection conn;

    private Logger logger = Logger.getLogger(this.getClass());
}
