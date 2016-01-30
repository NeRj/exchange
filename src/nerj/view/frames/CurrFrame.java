package nerj.view.frames;

import nerj.model.listeners.currencies.InsertButtonListener;
import nerj.model.listeners.currencies.SaveButtonListener;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class CurrFrame extends JFrame{
    public CurrFrame(Connection conn, boolean isAdmin){
        this.conn = conn;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        path = CurrFrame.class.getResource("/img/currency.png");
        setIconImage(new ImageIcon(path).getImage());
        setTitle("Курсы валют");

        lastData = new ArrayList<Vector<String>>();
        currBox = new JComboBox<String>(getAllCurrencies());
        table = new JTable();
        showCurrencies();
        scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(10,10,10,10));

        Box row = Box.createHorizontalBox();
        if (isAdmin){
            path = CurrFrame.class.getResource("/img/insert.png");
            addButton = new JButton(new ImageIcon(path));
            addButton.setToolTipText("Вставить строку в таблицу");
            addButton.addActionListener(new InsertButtonListener(this));
            path = CurrFrame.class.getResource("/img/save.png");
            saveButton = new JButton(new ImageIcon(path));
            saveButton.setToolTipText("Сохранить все изменения");
            saveButton.addActionListener(new SaveButtonListener(this));

            row.add(Box.createHorizontalStrut(10));
            row.add(addButton);
            row.add(Box.createHorizontalGlue());
            row.add(saveButton);
            row.add(Box.createHorizontalStrut(10));
        }

        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(row, BorderLayout.SOUTH);
        pack();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(CurrFrame.this);
        } catch (ClassNotFoundException clEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            logger.error("Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        } catch (InstantiationException instEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            logger.error("Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        } catch (IllegalAccessException accEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            logger.error("Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        } catch (UnsupportedLookAndFeelException lafEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            logger.error("Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        }
    }
  
    private Vector<String> getAllCurrencies(){
        Vector<String> currencies = new Vector<String>();
        try {
            Statement query = conn.createStatement();
            ResultSet qResult = query.executeQuery("SELECT currencyName FROM currency ORDER BY currencyName");
            while (qResult.next()){
                currencies.add(qResult.getString(1));
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Произошла ошибка при работе с базой данных.\nДанные не были сохранены");
            logger.error("Произошла ошибка при работе с базой данных.\nДанные не были сохранены");
        }
        Arrays.sort(currencies.toArray());  
        return currencies;
    }

    private void showCurrencies(){
        int rows = 0;
        try {
            Statement query = conn.createStatement();
            ResultSet queryResult = query.executeQuery("SELECT currency.currencyName, course.sell, course.buy " +
                    "FROM course, currency WHERE course.idCurrency = currency.idCurrency ORDER BY currency.currencyName");
            model = new DefaultTableModel(headers,0);
            while (queryResult.next()){
                Vector<String> data = new Vector<String>();
                for (int j = 0; j < headers.length; j++){
                    data.add(queryResult.getString(j+1));
                    lastData.add(data);
                }
                model.insertRow(rows, data);
                rows++;
            }
            table.setModel(model);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Произошла ошибка при работе с базой данных.\nДанные не были сохранены");
            logger.error("Произошла ошибка при работе с базой данных.\nДанные не были сохранены");
        }
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(currBox));
        table.getTableHeader().setReorderingAllowed(false);
    }

    public ArrayList<Vector<String>> getLastData() {
        return lastData;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public Connection getConn() {
        return conn;
    }

    private ArrayList<Vector<String>> lastData;
    private Connection conn;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton addButton;
    private JButton delButton;
    private JButton saveButton;
    private JComboBox<String> currBox;
    private DefaultTableModel model;
    private final String[] headers = {"Валюта", "Продажа", "Покупка"};
    
    private URL path;

    private Logger logger = Logger.getLogger(this.getClass());
}
