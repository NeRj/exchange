package nerj.model.listeners.operation;

import nerj.view.frames.NewOpFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonListener implements ActionListener{
    public ClearButtonListener(NewOpFrame frame){
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        JTextField summ = frame.getSummField();
        JTextField summBYR = frame.getSummBYRField();
        JTextField name = frame.getNameField();
        JTextField fam = frame.getFamField();
        JTextField passport = frame.getPassportField();
        summ.setText("");
        summBYR.setText("");
        name.setText("");
        fam.setText("");
        passport.setText("");
        frame.setSummField(summ);
        frame.setSummBYRField(summBYR);
        frame.setNameField(name);
        frame.setFamField(fam);
        frame.setPassportField(passport);
    }
    
    private NewOpFrame frame;
}
