import javax.swing.*;
import javax.swing.table.*;

public class NonEditableModel extends DefaultTableModel {

    NonEditableModel(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
