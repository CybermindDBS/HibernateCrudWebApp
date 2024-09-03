public class Table
{
  String tableName;
  List<ArrayList> rows;

  Table(String tableName, List<ArrayList<String>> rows)
  {
    this.tableName = tableName;
    this.rows = rows;
  }
}
