package nerj.model.listeners.main;

import nerj.view.frames.MainFrame;
import nerj.view.frames.NewOpFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewOpButtonListener implements ActionListener{
    public NewOpButtonListener(MainFrame frame){
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        NewOpFrame newOpFrame = new NewOpFrame(frame);
        newOpFrame.setVisible(true);
    }

    private MainFrame frame;
}
