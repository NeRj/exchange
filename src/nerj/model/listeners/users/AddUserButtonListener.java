package nerj.model.listeners.users;

import nerj.view.frames.NewUserFrame;
import nerj.view.frames.UsersFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserButtonListener implements ActionListener{
    public AddUserButtonListener(UsersFrame frame){
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent event){
        NewUserFrame newUserFrame = new NewUserFrame(frame);
        newUserFrame.setVisible(true);
    }

    private UsersFrame frame;
}
