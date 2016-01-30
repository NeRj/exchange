package nerj.model.listeners.newuser;

import nerj.view.frames.NewUserFrame;
import nerj.view.frames.UsersFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class OKButtonListener implements ActionListener{
    public OKButtonListener(NewUserFrame frame, UsersFrame parent){
        this.frame = frame;
        this.parent = parent;
        this.conn = parent.getConn();
    }

    public void actionPerformed(ActionEvent event){
        if (frame.getFamField().getText().isEmpty() || frame.getNameField().getText().isEmpty() ||
                frame.getPositionField().getText().isEmpty() || frame.getLoginField().getText().isEmpty() ||
                frame.getPassField().getText().isEmpty())
            JOptionPane.showMessageDialog(null, "Заполните все поля!");
        else {
            try {
                PreparedStatement update1 = conn.prepareStatement("INSERT INTO users (login, pass) VALUES (?, ?)");
                PreparedStatement update2 = conn.prepareStatement("INSERT INTO operator (login, nameOperator, famOperator, positionOperator) VALUES (?, ?, ?, ?)");
                boolean isExist = false;
                for (Vector<String> el: parent.getLastData())
                    if (el.get(3).equals(frame.getLoginField().getText())){
                        isExist = true;
                        JOptionPane.showMessageDialog(null, "Логин занят! Пожалуйста введите другой");
                        break;
                    }
                if (!isExist) {
                    update1.setString(1, frame.getLoginField().getText());
                    update1.setString(2, frame.getPassField().getText());
                    update2.setString(1, frame.getLoginField().getText());
                    update2.setString(2, frame.getNameField().getText());
                    update2.setString(3, frame.getFamField().getText());
                    update2.setString(4, frame.getPositionField().getText());
                    update1.executeUpdate();
                    update2.executeUpdate();
                    parent.showUsers();
                }
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null,"Произошла ошибка при работе с базой данных\nДанные не были сохранены");
                logger.error("Произошла ошибка при работе с базой данных\nДанные не были сохранены");
            }
            frame.dispose();
        }
    }

    private NewUserFrame frame;
    private UsersFrame parent;
    Connection conn;

    private Logger logger = Logger.getLogger(this.getClass());
}
