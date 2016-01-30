package nerj.view.frames;

import nerj.model.listeners.report.PrintButtonListener;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class OpReportFrame extends JFrame{
    public OpReportFrame(ArrayList<String> report){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        setIconImage(new ImageIcon("./img/currency.png").getImage());
        setTitle("Итог");

        dateLabel = new JLabel(report.get(7));
        typeLabel = new JLabel(report.get(0) + " - " + report.get(1));
        fLabel = new JLabel("ФИО: ");
        fioLabel = new JLabel(report.get(2));
        pLabel = new JLabel("Номер пасспорта: ");
        passportLabel = new JLabel(report.get(3));
        cLabel = new JLabel("Сумма в " + report.get(1));
        currLabel = new JLabel(report.get(4));
        bLabel = new JLabel("Сумма в BYR: ");
        byrLabel = new JLabel(report.get(5));
        rLabel = new JLabel("Сдача: ");
        respLabel = new JLabel(report.get(6) + "руб.");
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        Box row1 = Box.createHorizontalBox();
        row1.add(fLabel);
        row1.add(Box.createHorizontalGlue());
        row1.add(fioLabel);
        Box row2 = Box.createHorizontalBox();
        row2.add(pLabel);
        row2.add(Box.createHorizontalStrut(30));
        row2.add(Box.createHorizontalGlue());
        row2.add(passportLabel);
        Box row3 = Box.createHorizontalBox();
        row3.add(cLabel);
        row3.add(Box.createHorizontalGlue());
        row3.add(currLabel);
        Box row4 = Box.createHorizontalBox();
        row4.add(bLabel);
        row4.add(Box.createHorizontalGlue());
        row4.add(byrLabel);
        Box row5 = Box.createHorizontalBox();
        row5.add(rLabel);
        row5.add(Box.createHorizontalGlue());
        row5.add(respLabel);
        Box col = Box.createVerticalBox();
        col.add(dateLabel);
        col.add(Box.createVerticalStrut(10));
        col.add(typeLabel);
        col.add(Box.createVerticalStrut(20));
        col.add(row1);
        col.add(Box.createVerticalStrut(5));
        col.add(row2);
        col.add(Box.createVerticalStrut(5));
        col.add(row3);
        col.add(Box.createVerticalStrut(5));
        col.add(row4);
        col.add(Box.createVerticalStrut(5));
        col.add(row5);
        panel.add(col, BorderLayout.CENTER);
        printButton = new JButton("Печать", new ImageIcon("./img/print_m.png"));
        printButton.addActionListener(new PrintButtonListener(this));

        add(printButton, BorderLayout.SOUTH);
        add(panel);
        pack();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(OpReportFrame.this);
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

    public JPanel getPanel() {
        return panel;
    }

    private JPanel panel;
    private JButton printButton;
    private JLabel dateLabel;
    private JLabel typeLabel;
    private JLabel fioLabel;
    private JLabel passportLabel;
    private JLabel currLabel;
    private JLabel byrLabel;
    private JLabel respLabel;
    private JLabel fLabel;
    private JLabel pLabel;
    private JLabel cLabel;
    private JLabel bLabel;
    private JLabel rLabel;

    private Logger logger = Logger.getLogger(this.getClass());
}
