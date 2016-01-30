package nerj.model.listeners.operation;

import nerj.view.frames.NewOpFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimeUpdater implements ActionListener{
    public TimeUpdater(NewOpFrame frame){
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        JLabel date = frame.getDatetimeLabel();
        date.setText(String.format("\t\t\t %tF \t %<tT", new Date()));
        frame.setDatetimeLabel(date);
    }

    private NewOpFrame frame;
}
