package nerj.model.listeners.currencies;

import nerj.view.frames.CurrFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertButtonListener implements ActionListener{
    public InsertButtonListener(CurrFrame frame){
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent event){
        DefaultTableModel model = frame.getModel();
        int rows = model.getRowCount();
        Object[] data = new Object[3];
        data[0] = "Выбрать...";
        model.insertRow(rows, data);
        frame.setModel(model);
    }

    private CurrFrame frame;
}
