package com.cdevworks.crudapphjs;

import java.util.List;

public class Table
{
  String tableName;
  List<List<String>> rows;

  public Table(String tableName, List<List<String>> rows)
  {
    this.tableName = tableName;
    this.rows = rows;
  }
}
