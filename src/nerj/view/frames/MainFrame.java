package nerj.view.frames;

import nerj.model.listeners.main.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

public class MainFrame extends JFrame {
    public MainFrame(Connection conn, String login, boolean isAdmin){
        this.conn = conn;
        this.login = login;
        this.isAdmin = isAdmin;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        if (screenHeight <= 600)
            setExtendedState(Frame.MAXIMIZED_BOTH);
        setLocationByPlatform(true);
        path = MainFrame.class.getResource("/img/currency.png");
        setIconImage(new ImageIcon(path).getImage());
        setTitle("Обмен валют");

        panel = new JPanel(new BorderLayout());
        createToolbar();
        createTabbedPane();
        add(panel);

        if(isAdmin)
            newButton.setEnabled(false);
        else
            usersButton.setVisible(false);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(MainFrame.this);
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

    private void createToolbar(){
        toolBar = new JToolBar();
        path = MainFrame.class.getResource("/img/all.png");
        allButton = new JButton("Все операции",new ImageIcon(path));
        allButton.setToolTipText("Показать все проведенные операции");
        path = MainFrame.class.getResource("/img/new.png");
        newButton = new JButton("Новая операция",new ImageIcon(path));
        newButton.setToolTipText("Добавить новую операцию по обмену валют");
        path = MainFrame.class.getResource("/img/curs.png");
        coursesButton = new JButton("Курсы валют", new ImageIcon(path));
        coursesButton.setToolTipText("Открыть окно информации о курсах валют");
        path = MainFrame.class.getResource("/img/user.png");
        usersButton = new JButton("Операторы", new ImageIcon(path));
        usersButton.setToolTipText("Открыть окно управления пользователями программы");
        path = MainFrame.class.getResource("/img/logout.png");
        logoutButton = new JButton("Выход", new ImageIcon(path));
        logoutButton.setToolTipText("Сменить пользователя");
        path = MainFrame.class.getResource("/img/print.png");
        printButton = new JButton("Печать", new ImageIcon(path));
        printButton.setToolTipText("Печать текущей таблицы с данными об операциях");
        path = MainFrame.class.getResource("/img/info.png");
        aboutButton = new JButton("О программе...", new ImageIcon(path));
        Box row = Box.createHorizontalBox();
        row.add(toolBar);
        allButton.setFocusable(false);
        allButton.addActionListener(new AllOpsButtonListener(this));
        newButton.setFocusable(false);
        newButton.addActionListener(new NewOpButtonListener(this));
        coursesButton.setFocusable(false);
        coursesButton.addActionListener(new CurrButtonListener(this));
        usersButton.setFocusable(false);
        usersButton.addActionListener(new UsersButtonListener(this));
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(new LogoutButtonListener(this));
        printButton.setFocusable(false);
        printButton.addActionListener(new PrintButtonListener(this));
        aboutButton.setFocusable(false);
        aboutButton.addActionListener(new AboutButtonListener());
        toolBar.setFloatable(false);
        toolBar.add(allButton);
        toolBar.add(newButton);
        toolBar.add(coursesButton);
        toolBar.add(usersButton);
        toolBar.add(printButton);
        toolBar.add(logoutButton);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(aboutButton);
        panel.add(row,BorderLayout.NORTH);
    }

    private void createTabbedPane(){
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        createFirstTab();
        createSecondTab();
        createThirdTab();
        panel.add(tabbedPane);
    }

    private void createFirstTab(){
        table = new JTable();
        allButton.doClick();
        scrollPane1 = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setPreferredScrollableViewportSize(new Dimension(200, 100));
        path = MainFrame.class.getResource("/img/table.png");
        tabbedPane.addTab("      Список операций      ", new ImageIcon(path), scrollPane1);
    }

    private void createSecondTab(){
        panel1 = new JPanel(new BorderLayout(10, 10));
        searchField = new JTextField();
        searchField.addKeyListener(new SearchEnterListener(this));
        path = MainFrame.class.getResource("/img/search.png");
        searchButton = new JButton("Найти", new ImageIcon(path));
        searchButton.addActionListener(new SearchButtonListener(this));
        sub = new JPanel();
        buttonGroup = new ButtonGroup();
        radioButtons = new JRadioButton[params.length];
        for (int i = 0; i < params.length; i++){
            radioButtons[i] = new JRadioButton(params[i]);
            buttonGroup.add(radioButtons[i]);
        }
        panel1.setBorder(new EmptyBorder(20,20,20,20));
        Box row1 = Box.createHorizontalBox();
        row1.add(searchField);
        row1.add(searchButton);
        sub.setLayout(new BoxLayout(sub, BoxLayout.Y_AXIS));
        sub.setBorder(new TitledBorder(new EtchedBorder(), "Поиск по:"));
        radioButtons[0].setSelected(true);
        for (int i = 0; i < params.length; i++)
            sub.add(radioButtons[i]);
        panel1.add(row1, BorderLayout.NORTH);
        panel1.add(sub);
        path = MainFrame.class.getResource("/img/search.png");
        tabbedPane.addTab("     Поиск операций     ", new ImageIcon(path), panel1);
    }
    
    private void createThirdTab(){
        try{
            path = MainFrame.class.getResource("/help/index.html");
            editorPane = new JEditorPane(path);
            editorPane.setEditable(false);
            editorPane.addHyperlinkListener(new HTMLListener(this));
            scrollPane2 = new JScrollPane(editorPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        } catch (IOException ioEx){
            JOptionPane.showMessageDialog(null,"Файл справки не найден");
            logger.warn("Файл справки не найден");
        }
        path = MainFrame.class.getResource("/img/help.png");
        tabbedPane.addTab("        Справка        ", new ImageIcon(path), scrollPane2);
    }
    
    public void setupTable(){
        table.setCellSelectionEnabled(false);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMinWidth(110);
        table.getColumnModel().getColumn(2).setMinWidth(90);
        table.getColumnModel().getColumn(3).setMinWidth(100);
        table.getColumnModel().getColumn(4).setMinWidth(40);
        table.getColumnModel().getColumn(5).setMinWidth(100);
        table.getColumnModel().getColumn(6).setMinWidth(90);
        table.getColumnModel().getColumn(7).setMinWidth(120);
        table.getColumnModel().getColumn(8).setMinWidth(120);
        table.getColumnModel().getColumn(9).setMinWidth(150);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
    }

    public JTable getTable() {
        return table;
    }

    public Connection getConn() {
        return conn;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public JButton getAllButton() {
        return allButton;
    }

    public String getLogin() {
        return login;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public JRadioButton[] getRadioButtons() {
        return radioButtons;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JEditorPane getEditorPane() {
        return editorPane;
    }

    public void setEditorPane(JEditorPane editorPane) {
        this.editorPane = editorPane;
    }

    private Connection conn;
    private String login;
    private boolean isAdmin;
    private JPanel panel;
    private JToolBar toolBar;
    private JButton allButton;
    private JButton newButton;
    private JButton coursesButton;
    private JButton usersButton;
    private JButton logoutButton;
    private JButton printButton;
    private JButton aboutButton;
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JTable table;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel sub;
    private ButtonGroup buttonGroup;
    private JRadioButton[] radioButtons;
    private final String[] params = {"Дата операции", "Тип операции", "Валюта", "Фамилия клиента",
            "Пасспорт клиента", "Фамилия оператора"};
    private JEditorPane editorPane;
    
    private URL path;

    private Logger logger = Logger.getLogger(MainFrame.class);
}