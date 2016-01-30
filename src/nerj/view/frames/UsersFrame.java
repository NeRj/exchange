package nerj.view.frames;

import nerj.model.listeners.users.AddUserButtonListener;
import nerj.model.listeners.users.DelUserButtonListener;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class UsersFrame extends JFrame{
    public UsersFrame(Connection conn){
        this.conn = conn;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        URL path = UsersFrame.class.getResource("/img/currency.png");
        setIconImage(new ImageIcon(path).getImage());
        setTitle("Операторы обенных пунктов");

        lastData = new ArrayList<Vector<String>>();
        table = new JTable();
        showUsers();
        scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new EmptyBorder(10,10,10,10));
        Box row = Box.createHorizontalBox();
        path = UsersFrame.class.getResource("/img/plus.png");
        addButton = new JButton(new ImageIcon(path));
        addButton.setToolTipText("Добавить нового оператора");
        addButton.addActionListener(new AddUserButtonListener(this));
        path = UsersFrame.class.getResource("/img/minus.png");
        delButton = new JButton(new ImageIcon(path));
        delButton.setToolTipText("Удалить выбранного оператора");
        delButton.addActionListener(new DelUserButtonListener(this));


        row.add(Box.createHorizontalStrut(10));
        row.add(addButton);
        row.add(delButton);
        row.add(Box.createHorizontalStrut(10));

        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(row, BorderLayout.SOUTH);
        pack();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(UsersFrame.this);
        } catch (ClassNotFoundException clEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        } catch (InstantiationException instEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        } catch (IllegalAccessException accEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        } catch (UnsupportedLookAndFeelException lafEx){
            JOptionPane.showMessageDialog(null,"Ошибка применения стиля. Программа будет завершена");
            System.exit(0);
        }
    }

    public void showUsers(){
        int rows = 0;
        try {
            Statement query = conn.createStatement();
            ResultSet queryResult = query.executeQuery("SELECT operator.famOperator, operator.nameOperator, " +
                    "operator.positionOperator, users.login, users.pass FROM operator, users " +
                    "WHERE operator.login = users.login ORDER BY operator.famOperator, operator.nameOperator");
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
            JOptionPane.showMessageDialog(null,ex);
        }
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMinWidth(90);
        table.getColumnModel().getColumn(1).setMinWidth(90);
        table.getColumnModel().getColumn(2).setMinWidth(100);
        table.getColumnModel().getColumn(3).setMinWidth(90);
        table.getColumnModel().getColumn(4).setMinWidth(90);
    }

    public ArrayList<Vector<String>> getLastData() {
        return lastData;
    }

    public Connection getConn() {
        return conn;
    }

    public JTable getTable() {
        return table;
    }

    private ArrayList<Vector<String>> lastData;
    private Connection conn;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton addButton;
    private JButton delButton;
    private DefaultTableModel model;
    private final String[] headers = {"Фамилия", "Имя", "Должность", "Логин", "Пароль"};

    private Logger logger = Logger.getLogger(this.getClass());
}
