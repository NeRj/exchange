package nerj.model.listeners.operation;

import nerj.view.frames.NewOpFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypeChangeListener implements ActionListener{
    public TypeChangeListener(NewOpFrame frame){
        this.frame = frame;    
    }
    
    public void actionPerformed(ActionEvent e) {
        int index = frame.getOperationTypeBox().getSelectedIndex();
        JTextField summ = frame.getSummField();
        JTextField summBYR = frame.getSummBYRField();
        if (index == 0){
            summ.setEditable(false);
            summBYR.setEditable(true);
        }
        else if (index == 1){
            summ.setEditable(true);
            summBYR.setEditable(false);
        }
        frame.setSummField(summ);
        frame.setSummBYRField(summBYR);
    }

    private NewOpFrame frame;
}
