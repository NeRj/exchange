package nerj.model.listeners.currencies;

import nerj.view.frames.CurrFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class SaveButtonListener implements ActionListener{
    public SaveButtonListener(CurrFrame frame){
        this.frame = frame;
        this.conn = frame.getConn();
    }

    public void actionPerformed(ActionEvent event){
        DefaultTableModel model = frame.getModel();
        for (int i = 0; i < model.getRowCount(); i++){
            try {
                Integer.parseInt(model.getValueAt(i, 1).toString());
                Integer.parseInt(model.getValueAt(i, 2).toString());
            } catch (NumberFormatException numEx){
                JOptionPane.showMessageDialog(null, "Введены некорректные значения!");
                return;
            }
        }
        for (int i = model.getRowCount() - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
                if (model.getValueAt(i, 0).equals(model.getValueAt(j, 0))){
                    model.removeRow(j);
                    i--;
                }
        frame.setModel(model);
        int rows = model.getRowCount();
        try {
            PreparedStatement insert = conn.prepareStatement("INSERT INTO course (idCurrency, sell, buy) " +
                    "VALUES ((SELECT idCurrency FROM currency WHERE currencyName = ?), ?, ?)");
            PreparedStatement update = conn.prepareStatement("UPDATE course SET sell = ?, buy = ? WHERE idCurrency in (SELECT idCurrency FROM currency WHERE currencyName = ?)");
            for (int i = 0; i < rows; i++){
                boolean isUpdated = false;
                for (Vector<String> el: frame.getLastData()){
                    if (el.get(0).equals(model.getValueAt(i, 0))){
                        update.setString(1, model.getValueAt(i, 1).toString());
                        update.setString(2, model.getValueAt(i, 2).toString());
                        update.setString(3, model.getValueAt(i, 0).toString());
                        update.executeUpdate();
                        isUpdated = true;
                        break;
                    }
                }
                if (!isUpdated){
                    insert.setString(1, model.getValueAt(i, 0).toString());
                    insert.setString(2, model.getValueAt(i, 1).toString());
                    insert.setString(3, model.getValueAt(i, 2).toString());
                    insert.executeUpdate();
                }
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Произошла ошибка при работе с базой данных\nДанные не были сохранены");
            logger.error("Произошла ошибка при работе с базой данных\nДанные не были сохранены");
        }
    }

    private CurrFrame frame;
    private Connection conn;

    private Logger logger = Logger.getLogger(this.getClass());
}
