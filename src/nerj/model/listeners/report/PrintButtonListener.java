package nerj.model.listeners.report;

import nerj.view.frames.OpReportFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintButtonListener implements ActionListener{
    public PrintButtonListener(OpReportFrame frame){
        this.frame = frame;
    }
    
    public void actionPerformed(ActionEvent event){
        Toolkit def = Toolkit.getDefaultToolkit();
        PrintJob job = def.getPrintJob(frame, "Печать отчета", null);
        frame.getPanel().print(job.getGraphics());
        job.end();
    }
    
    private OpReportFrame frame;
}
