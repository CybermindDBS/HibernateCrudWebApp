public class Table
{
  String tableName;
  List<List> rows;

  Table(String tableName, List<List<String>> rows)
  {
    this.tableName = tableName;
    this.rows = rows;
  }
}
