package nerj.view.frames;

import nerj.model.listeners.operation.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.*;
import java.util.Vector;

public class NewOpFrame extends JFrame{
    public NewOpFrame(MainFrame parent) {
        this.conn = parent.getConn();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationByPlatform(true);
        URL path = NewOpFrame.class.getResource("/img/currency.png");
        setIconImage(new ImageIcon(path).getImage());
        setTitle("Новая операция");

        Vector<String> currencies = new Vector<String>();
        String[] types = new String[2];
        try {
            Statement query1 = conn.createStatement();
            Statement query2 = conn.createStatement();
            ResultSet qResult1 = query1.executeQuery("SELECT currencyCode FROM currency WHERE idCurrency IN " +
                    "(SELECT idCurrency FROM course) ORDER BY currencyCode");
            ResultSet qResult2 = query2.executeQuery("SELECT typeOperation FROM operationtype");
            while (qResult1.next())
                currencies.add(qResult1.getString(1));
            int i = 0;
            while (qResult2.next()){
                types[i] = qResult2.getString(1);
                i++;
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Произошла ошибка при работе с базой данных\nДанные не были сохранены");
            logger.error("Произошла ошибка при работе с базой данных\nДанные не были сохранены");
        }
        
        panel = new JPanel(new GridLayout());
        datetimeLabel = new JLabel();
        typeLabel = new JLabel("Тип операции: ");
        operationTypeBox = new JComboBox<String>(types);
        operationTypeBox.addActionListener(new TypeChangeListener(this));
        currencyLabel = new JLabel("Валюта: ");
        currencyBox = new JComboBox<String>(currencies);
        summLabel = new JLabel("Сумма в валюте: ");
        summField = new JTextField();
        summField.setEditable(false);
        summBYRLabel = new JLabel("Сумма в бел. руб.: ");
        summBYRField = new JTextField();
        path = NewOpFrame.class.getResource("/img/calc.png");
        getSummButton = new JButton(new ImageIcon(path));
        getSummButton.setToolTipText("Посчитать сумму операции");
        getSummButton.addActionListener(new SummationButtonListener(this));
        nameLabel = new JLabel("Имя: ");
        nameField = new JTextField();
        famLabel = new JLabel("Фамилия: ");
        famField = new JTextField();
        passportLabel = new JLabel("Серия и номер паспорта: ");
        passportField = new JTextField();
        path = NewOpFrame.class.getResource("/img/checkmark.png");
        addButton = new JButton(new ImageIcon(path));
        addButton.setToolTipText("Оформить операцию по обмену");
        addButton.addActionListener(new AddButtonListener(this, parent));
        path = NewOpFrame.class.getResource("/img/clear.png");
        clearButton = new JButton(new ImageIcon(path));
        clearButton.setToolTipText("Очистить поля формы");
        clearButton.addActionListener(new ClearButtonListener(this));
        
        ActionListener updateTime = new TimeUpdater(this);
        Timer t = new Timer(1000, updateTime);
        t.start();

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(setupUI());
        add(panel);
        pack();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(NewOpFrame.this);
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
    
    private Box setupUI(){
        Box row1 = Box.createHorizontalBox();
        row1.add(typeLabel);
        row1.add(Box.createHorizontalStrut(5));
        row1.add(operationTypeBox);
        row1.add(Box.createHorizontalStrut(30));
        row1.add(currencyLabel);
        row1.add(Box.createHorizontalStrut(5));
        row1.add(currencyBox);
        Box row2 = Box.createHorizontalBox();
        row2.add(famLabel);
        row2.add(Box.createHorizontalStrut(5));
        row2.add(famField);
        row2.add(Box.createHorizontalStrut(20));
        row2.add(nameLabel);
        row2.add(Box.createHorizontalStrut(5));
        row2.add(nameField);
        Box row3 = Box.createHorizontalBox();
        row3.add(passportLabel);
        row3.add(Box.createHorizontalStrut(5));
        row3.add(passportField);
        row3.add(Box.createHorizontalStrut(50));
        Box col1 = Box.createVerticalBox();
        col1.add(summLabel);
        col1.add(Box.createVerticalStrut(10));
        col1.add(summBYRLabel);
        Box col2 = Box.createVerticalBox();
        col2.add(summField);
        col2.add(Box.createVerticalStrut(5));
        col2.add(summBYRField);
        Box row4 = Box.createHorizontalBox();
        row4.add(col1);
        row4.add(col2);
        row4.add(Box.createHorizontalStrut(30));
        row4.add(getSummButton);
        Box row5 = Box.createHorizontalBox();
        row5.add(clearButton);
        row5.add(Box.createHorizontalStrut(10));
        row5.add(addButton);
        Box col3 = Box.createVerticalBox();
        col3.add(datetimeLabel, BorderLayout.WEST);
        col3.add(Box.createVerticalStrut(10));
        col3.add(row1);
        col3.add(Box.createVerticalStrut(10));
        col3.add(row2);
        col3.add(Box.createVerticalStrut(10));
        col3.add(row3);
        col3.add(Box.createVerticalStrut(10));
        col3.add(row4);
        col3.add(Box.createVerticalStrut(10));
        col3.add(row5);
        col3.add(Box.createVerticalStrut(15));
        return col3;
    }

    public JLabel getDatetimeLabel() {
        return datetimeLabel;
    }

    public void setDatetimeLabel(JLabel datetimeLabel) {
        this.datetimeLabel = datetimeLabel;
    }

    public JComboBox<String> getOperationTypeBox() {
        return operationTypeBox;
    }

    public JTextField getSummField() {
        return summField;
    }

    public void setSummField(JTextField summField) {
        this.summField = summField;
    }

    public JTextField getSummBYRField() {
        return summBYRField;
    }

    public void setSummBYRField(JTextField summBYRField) {
        this.summBYRField = summBYRField;
    }

    public JComboBox<String> getCurrencyBox() {
        return currencyBox;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getFamField() {
        return famField;
    }

    public void setFamField(JTextField famField) {
        this.famField = famField;
    }

    public JTextField getPassportField() {
        return passportField;
    }

    public void setPassportField(JTextField passportField) {
        this.passportField = passportField;
    }

    public Connection getConn() {
        return conn;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    private Connection conn;
    private JPanel panel;
    private JLabel datetimeLabel;
    private JLabel typeLabel;
    private JComboBox<String> operationTypeBox;
    private JLabel currencyLabel;
    private JComboBox<String> currencyBox;
    private JLabel summLabel;
    private JTextField summField;
    private JLabel summBYRLabel;
    private JTextField summBYRField;
    private JButton getSummButton;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel famLabel;
    private JTextField famField;
    private JLabel passportLabel;
    private JTextField passportField;
    private JButton addButton;
    private JButton clearButton;
    private String response;

    private Logger logger = Logger.getLogger(this.getClass());
}
