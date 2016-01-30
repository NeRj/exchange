package nerj.model.listeners.main;

import nerj.view.frames.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchButtonListener implements ActionListener{
    public SearchButtonListener(MainFrame frame){
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent event){
        ButtonModel selectedButton = frame.getButtonGroup().getSelection();
        frame.getAllButton().removeActionListener(frame.getAllButton().getActionListeners()[0]);
        if (selectedButton == frame.getRadioButtons()[0].getModel())
            frame.getAllButton().addActionListener(new AllOpsButtonListener(frame, "date", frame.getSearchField().getText()));
        else if (selectedButton == frame.getRadioButtons()[1].getModel())
            frame.getAllButton().addActionListener(new AllOpsButtonListener(frame, "type", frame.getSearchField().getText()));
        else if (selectedButton == frame.getRadioButtons()[2].getModel())
            frame.getAllButton().addActionListener(new AllOpsButtonListener(frame, "curr", frame.getSearchField().getText()));
        else if (selectedButton == frame.getRadioButtons()[3].getModel())
            frame.getAllButton().addActionListener(new AllOpsButtonListener(frame, "famc", frame.getSearchField().getText()));
        else if (selectedButton == frame.getRadioButtons()[4].getModel())
            frame.getAllButton().addActionListener(new AllOpsButtonListener(frame, "pasc", frame.getSearchField().getText()));
        else if (selectedButton == frame.getRadioButtons()[5].getModel())
            frame.getAllButton().addActionListener(new AllOpsButtonListener(frame, "famo", frame.getSearchField().getText()));
        frame.getAllButton().doClick();
        frame.getAllButton().removeActionListener(frame.getAllButton().getActionListeners()[0]);
        frame.getAllButton().addActionListener(new AllOpsButtonListener(frame));
        frame.getTabbedPane().setSelectedIndex(0);
    }
    
    private MainFrame frame;
}
