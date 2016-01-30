package nerj.model.listeners.login;

import nerj.view.frames.LoginFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EnterKeyListener implements KeyListener{
    public EnterKeyListener(LoginFrame frame){
        this.frame = frame;
    }
    
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            frame.getEnterButton().doClick();
    }

    private LoginFrame frame;
}
