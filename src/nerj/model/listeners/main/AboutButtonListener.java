package nerj.model.listeners.main;

import nerj.view.frames.AboutFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
        AboutFrame aboutFrame = new AboutFrame();
        aboutFrame.setVisible(true);
    }
}
