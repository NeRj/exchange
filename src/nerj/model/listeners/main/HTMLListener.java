package nerj.model.listeners.main;

import nerj.view.frames.MainFrame;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.io.IOException;

public class HTMLListener implements HyperlinkListener{
    public HTMLListener(MainFrame frame){
        this.frame = frame;
    }
    
    public void hyperlinkUpdate(HyperlinkEvent event) {
        JEditorPane editorPane = frame.getEditorPane();
        if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
        try{
            editorPane.setPage(event.getURL());
        } catch (IOException ioEx){
            JOptionPane.showMessageDialog(null, "Ссылка не рабочая!");
            logger.warn("Не рабочая ссылка в файле справки");
        }
        frame.setEditorPane(editorPane);
    }
    
    private MainFrame frame;

    private Logger logger = Logger.getLogger(this.getClass());
}
