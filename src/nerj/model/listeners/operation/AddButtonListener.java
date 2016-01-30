package nerj.model.listeners.operation;

import nerj.view.frames.MainFrame;
import nerj.view.frames.NewOpFrame;
import nerj.view.frames.OpReportFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddButtonListener implements ActionListener{
    public AddButtonListener(NewOpFrame frame, MainFrame parent){
        this.frame = frame;
        this.parent = parent;
        this.conn = frame.getConn();
    }

    public void actionPerformed(ActionEvent event){
        ArrayList<String> report = new ArrayList<String>();
        report.add(frame.getOperationTypeBox().getSelectedItem().toString());
        report.add(frame.getCurrencyBox().getSelectedItem().toString());
        report.add(frame.getFamField().getText() + " " + frame.getNameField().getText());
        report.add(frame.getPassportField().getText());
        report.add(frame.getSummField().getText());
        report.add(frame.getSummBYRField().getText());
        report.add(frame.getResponse());
        report.add(frame.getDatetimeLabel().getText());
        if (frame.getFamField().getText().isEmpty() || frame.getNameField().getText().isEmpty() ||
                frame.getPassportField().getText().isEmpty() || frame.getSummField().getText().isEmpty() ||
                frame.getSummBYRField().getText().isEmpty())
            JOptionPane.showMessageDialog(null, "Заполните все поля!");
        else {
            try {
                PreparedStatement update1 = conn.prepareStatement("INSERT INTO client (nameClient, famClient, passport) " +
                        "SELECT ?, ?, ? FROM client WHERE NOT EXISTS (SELECT 1 FROM client WHERE passport = ?) LIMIT 0,1");
                PreparedStatement update2 = conn.prepareStatement("INSERT INTO operation " +
                        "(date, idType, idCourse, summ, summBYR, idOperator, idClient) VALUES " +
                        "(?, (SELECT idType FROM operationtype WHERE typeOperation = ?), (SELECT idCourse " +
                        "FROM course WHERE idCurrency in (SELECT idCurrency FROM currency WHERE currencyCode = ?)), " +
                        "?, ?, (SELECT idOperator FROM operator WHERE login = ?), (SELECT idClient FROM client " +
                        "WHERE passport = ?))");
                update1.setString(1, frame.getNameField().getText());
                update1.setString(2, frame.getFamField().getText());
                update1.setString(3, frame.getPassportField().getText());
                update1.setString(4, frame.getPassportField().getText());
                update2.setString(1, frame.getDatetimeLabel().getText());
                update2.setString(2, frame.getOperationTypeBox().getSelectedItem().toString());
                update2.setString(3, frame.getCurrencyBox().getSelectedItem().toString());
                update2.setString(4, frame.getSummField().getText());
                update2.setString(5, frame.getSummBYRField().getText());
                update2.setString(6, parent.getLogin());
                update2.setString(7, frame.getPassportField().getText());
                update1.executeUpdate();
                update2.executeUpdate();
            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null,"Произошла ошибка при работе с базой данных\nДанные не были сохранены");
                logger.error("Произошла ошибка при работе с базой данных\nДанные не были сохранены");
            }
            frame.dispose();
            OpReportFrame opReportFrame = new OpReportFrame(report);
            opReportFrame.setVisible(true);
            parent.getAllButton().doClick();
        }
    }

    private NewOpFrame frame;
    private MainFrame parent;
    private Connection conn;

    private Logger logger = Logger.getLogger(this.getClass());
}
