package nerj.model.listeners.operation;

import nerj.view.frames.NewOpFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SummationButtonListener implements ActionListener{
    public SummationButtonListener(NewOpFrame frame){
        this.frame = frame;
        this.conn = frame.getConn();
    }

    public void actionPerformed(ActionEvent event){
        int index = frame.getOperationTypeBox().getSelectedIndex();
        JTextField summField = frame.getSummField();
        JTextField summBYRField = frame.getSummBYRField();
        int summBYR = 0, summ = 0;
        boolean isBYR = false, isCurr = false;
        if (index == 0){
            try {
                summBYR = Integer.parseInt(summBYRField.getText());
            } catch (NumberFormatException numEx){
                JOptionPane.showMessageDialog(null, "Некорректное значение!");
                return;
            }
            isBYR = true;
        } else if (index == 1){
            try {
                summ = Integer.parseInt(summField.getText());
            } catch (NumberFormatException numEx){
                JOptionPane.showMessageDialog(null, "Некорректное значение!");
                return;
            }
            isCurr = true;
        }
        String type = frame.getOperationTypeBox().getSelectedItem().toString();
        String currency = frame.getCurrencyBox().getSelectedItem().toString();
        int course = 0;
        try {
            PreparedStatement query1 = conn.prepareStatement("SELECT sell FROM course WHERE idCurrency in (SELECT idCurrency FROM currency WHERE currencyCode = ?)");
            PreparedStatement query2 = conn.prepareStatement("SELECT buy FROM course WHERE idCurrency in (SELECT idCurrency FROM currency WHERE currencyCode = ?)");
            if (type.equals("Продажа")){
                query1.setString(1, currency);
                ResultSet qResult = query1.executeQuery();
                while (qResult.next()){
                    course = qResult.getInt(1);
                }
            }
            else {
                query2.setString(1, currency);
                ResultSet qResult = query2.executeQuery();
                while (qResult.next()){
                    course = qResult.getInt(1);
                }
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Произошла ошибка при работе с базой данных\nДанные не были сохранены");
            logger.error("Произошла ошибка при работе с базой данных\nДанные не были сохранены");
        }
        if (isBYR){
            summField.setText(String.format("%d", Math.round(summBYR / course - 0.5)));
            summBYRField.setText(String.format("%d", Math.round(summBYR / course - 0.5) * course));
            frame.setResponse(String.format("%d", summBYR - Math.round(summBYR / course - 0.5) * course));
            JOptionPane.showMessageDialog(null, "Сдача: " + (summBYR - Math.round(summBYR / course - 0.5) * course) + "руб.");
        } else if (isCurr){
            summBYRField.setText(String.format("%d", summ * course));
            frame.setResponse(String.format("%d", 0));
        }
        frame.setSummField(summField);
        frame.setSummBYRField(summBYRField);
    }
    
    private NewOpFrame frame;
    private Connection conn;

    private Logger logger = Logger.getLogger(this.getClass());
}
