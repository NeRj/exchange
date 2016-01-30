package nerj.model.listeners.main;

import nerj.view.frames.MainFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

public class PrintButtonListener implements ActionListener{
    public PrintButtonListener(MainFrame frame){
        this.frame = frame;
    }
    
    public void actionPerformed(ActionEvent e) {
        try{
            MessageFormat headerFormat = new MessageFormat("Сводная таблица операций - Страница {0}");
            MessageFormat footerFormat = new MessageFormat("- {0} -");
            frame.getTable().print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
        } catch (PrinterException prEx){
            JOptionPane.showMessageDialog(null,"Ошибка доступа к устройствам печати");
            logger.warn("Ошибка доступа к устройствам печати");
        }
    }  
    
    private MainFrame frame;

    private Logger logger = Logger.getLogger(this.getClass());
}
