package nerj.model.listeners.main;

import nerj.view.frames.MainFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchEnterListener implements KeyListener{
    public SearchEnterListener(MainFrame frame){
        this.frame = frame;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            frame.getSearchButton().doClick();
    }

    private MainFrame frame;
}
