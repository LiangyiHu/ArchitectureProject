package com.architecture.project.gui;

import com.architecture.project.processor.Processor;
import com.architecture.project.processor.cache.CacheLine;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * @author taoranxue on 10/12/16 3:37 PM.
 */
public class CacheModel extends AbstractTableModel {
    private final String[] columnNames = new String[]{"Valid", "Tag", "000", "010", "100", "110"};
    private final List<CacheLine> cacheLineList;

    public CacheModel() {
        cacheLineList = Processor.cache.getCacheLines();
    }


    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return cacheLineList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return (cacheLineList.get(rowIndex).isValid()) ? 1 : 0;
        } else if (columnIndex == 1) {
            return "0x" + Integer.toHexString(cacheLineList.get(rowIndex).getTag());
        } else {
            return "0x" + Integer.toHexString(cacheLineList.get(rowIndex).getBlocks().get(columnIndex - 2));
        }
    }
}
