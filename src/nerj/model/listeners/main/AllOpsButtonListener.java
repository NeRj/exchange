package nerj.model.listeners.main;


import nerj.view.frames.MainFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class AllOpsButtonListener implements ActionListener{
    public AllOpsButtonListener(MainFrame frame){
        this.frame = frame;
        this.conn = frame.getConn();
        this.searchOption = null;
        this.searchStr = null;
    }

    public AllOpsButtonListener(MainFrame frame, String searchOption, String searchStr){
        this.frame = frame;
        this.conn = frame.getConn();
        this.searchOption = searchOption;
        this.searchStr = searchStr;
    }

    public void actionPerformed(ActionEvent e) {
        int i = 0;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT DISTINCT operation.idOperation, operation.date, " +
                    "operationtype.typeOperation, currency.currencyName, operation.summ, operation.summBYR, " +
                    "client.famClient, client.passport, operator.famOperator, client.nameClient, " +
                    "operator.nameOperator, operator.positionOperator, currency.currencyCode, course.sell, course.buy " +
                    "FROM client, course, currency, operation, operationtype, operator " +
                    "WHERE operation.idCourse = course.idCourse AND course.idCurrency = currency.idCurrency AND " +
                    "operation.idOperator = operator.idOperator AND client.idClient = operation.idClient AND " +
                    "operation.idType = operationtype.idType AND " +
                    "operationtype.typeOperation LIKE ? AND operation.date LIKE ? AND " +
                    "currency.currencyName LIKE ? AND client.famClient LIKE ? AND " +
                    "client.passport LIKE ? AND operator.famOperator LIKE ? ORDER BY operation.idOperation");
            if (searchOption == null && searchStr ==null){
                query.setString(1, "%");
                query.setString(2, "%");
                query.setString(3, "%");
                query.setString(4, "%");
                query.setString(5, "%");
                query.setString(6, "%");
            } else {
                if (searchOption.equals("type")){
                    query.setString(1, "%" + searchStr + "%");
                    query.setString(2, "%");
                    query.setString(3, "%");
                    query.setString(4, "%");
                    query.setString(5, "%");
                    query.setString(6, "%");
                }
                if (searchOption.equals("date")){
                    query.setString(1, "%");
                    query.setString(2, "%" + searchStr + "%");
                    query.setString(3, "%");
                    query.setString(4, "%");
                    query.setString(5, "%");
                    query.setString(6, "%");
                }
                if (searchOption.equals("curr")){
                    query.setString(1, "%");
                    query.setString(2, "%");
                    query.setString(3, "%" + searchStr + "%");
                    query.setString(4, "%");
                    query.setString(5, "%");
                    query.setString(6, "%");
                }
                if (searchOption.equals("famc")){
                    query.setString(1, "%");
                    query.setString(2, "%");
                    query.setString(3, "%");
                    query.setString(4, "%" + searchStr + "%");
                    query.setString(5, "%");
                    query.setString(6, "%");
                }
                if (searchOption.equals("pasc")){
                    query.setString(1, "%");
                    query.setString(2, "%");
                    query.setString(3, "%");
                    query.setString(4, "%");
                    query.setString(5, "%" + searchStr + "%");
                    query.setString(6, "%");
                }
                if (searchOption.equals("famo")){
                    query.setString(1, "%");
                    query.setString(2, "%");
                    query.setString(3, "%");
                    query.setString(4, "%");
                    query.setString(5, "%");
                    query.setString(6, "%" + searchStr + "%");
                }
            }
            ResultSet queryResult = query.executeQuery();
            DefaultTableModel model = new DefaultTableModel(fullHeadings,0);
            while (queryResult.next()){
                Vector<String> data = new Vector<String>();
                for (int j = 0; j < fullHeadings.length; j++){
                    if (j == 6)
                        data.add(queryResult.getString(j+1) +  " " + queryResult.getString("nameClient"));
                    else if (j == 8)
                        data.add(queryResult.getString(j+1) + " " + queryResult.getString("nameOperator") + " (" +
                            queryResult.getString("positionOperator") + ")");
                    else
                    data.add(queryResult.getString(j+1));
                }
                if (queryResult.getString("typeOperation").equals("Продажа"))
                    data.add(4, queryResult.getString("sell"));
                else data.add(4, queryResult.getString("buy"));
                data.setElementAt(data.get(1).substring(0, data.get(1).length() - 2), 1);
                model.insertRow(i, data);
                i++;
            }
            frame.getTable().setModel(model);
            frame.setupTable();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Произошла ошибка при работе с базой данных\nДанные не были сохранены");
            logger.error("Произошла ошибка при работе с базой данных\nДанные не были сохранены");
        }
    }

    private MainFrame frame;
    private Connection conn;
    private final String[] fullHeadings = {"№", "Дата", "Тип операции", "Валюта", "Курс",
            "Сумма в валюте", "Сумма в BYR", "Клиент", "Номер паспорта", "Оператор"};
    private String searchOption;
    private String searchStr;

    private Logger logger = Logger.getLogger(this.getClass());
}
