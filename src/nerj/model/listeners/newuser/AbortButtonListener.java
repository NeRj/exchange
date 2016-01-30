package nerj.model.listeners.newuser;

import nerj.view.frames.NewUserFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbortButtonListener implements ActionListener{
    public AbortButtonListener(NewUserFrame frame){
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent event){
        frame.dispose();
    }

    private NewUserFrame frame;
}
