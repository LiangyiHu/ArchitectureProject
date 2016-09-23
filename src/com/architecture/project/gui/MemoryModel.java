package com.architecture.project.gui;

import com.architecture.project.memory.MainMemory;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Table model class used for gui {@linkplain javax.swing.JTable}.
 *
 * @author taoranxue on 9/19/16 1:14 AM.
 */
public class MemoryModel extends AbstractTableModel {
    private final String[] columnNames = new String[]{"Address", "Data(Hex)", "Data(Binary)"};
    private List data;

    /**
     * Display memory data in giving range.
     *
     * @param beginIndex start index.
     * @param endIndex   end index.
     */
    public MemoryModel(int beginIndex, int endIndex) {
        data = MainMemory.getMemoryData(beginIndex, endIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((String[]) data.get(rowIndex))[columnIndex];
    }
}
