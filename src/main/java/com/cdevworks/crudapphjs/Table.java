package com.cdevworks.crudapphjs;

import java.util.ArrayList;
import java.util.List;

public class Table {
    String tableName;
    List<List<String>> rows;

    public Table(String tableName, List<List<String>> rows) {
        this.tableName = tableName;
        this.rows = rows;
    }

    public static String getName(List data) {
        StringBuilder sb = new StringBuilder(data.toString());
        return sb.substring(1, sb.indexOf("{"));
    }

    public static List<List<String>> getRows(List data) {

        //For getting all the rows.
        List<List<String>> rows = new ArrayList<>();
        StringBuilder sb = new StringBuilder(data.toString());
        int start = 0, end = 0;
        String rowStr = null;
        while (true) {
            start = sb.indexOf("{", start) + 1;
            end = sb.indexOf("}", end + 1);
            if (start <= 0 || end <= 0)
                break;
            rowStr = sb.substring(start, end);
            ArrayList<String> row = new ArrayList<>();
            StringBuilder sb2 = new StringBuilder(rowStr);
            int start2 = 0, end2 = 0;
            while (true) {
                start2 = sb2.indexOf("=", start2) + 1;
                end2 = sb2.indexOf(",", end2 + 1);
                if (end2 == -1 && start2 != -1) end2 = sb2.length();
                if (start2 <= 0 || end2 <= 0)
                    break;
                row.add(sb2.substring(start2, end2).replaceAll("'", ""));
            }
            rows.add(row);
        }

        //For getting column information.
        ArrayList<String> columns = new ArrayList<>();
        StringBuilder sb3 = new StringBuilder(rowStr);
        int start3 = 0, end3 = 0;
        while (true) {
            start3 = sb3.indexOf(" ", start3) + 1;
            end3 = sb3.indexOf("=", end3 + 1);
            if (end3 > 0 && start3 > end3) start3 = 0;
            if (end3 <= 0)
                break;
            columns.add(sb3.substring(start3, end3));
        }
        rows.add(0, columns);
        return rows;
    }
}
