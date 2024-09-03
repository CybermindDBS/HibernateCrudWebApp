public class PageServices
  {
    public void page1Get(HttpServletRequest request, HttpServletResponse response)
    {
      request.setAttribute("pageName", "Hibernate | Single Row Operations");
      List<List<String>> table1 = new ArrayList();
      table1.add(Arrays.asList("a1", "a2", "a3"));
      table1.add(Arrays.asList("a4", "a5", "a6"));
      List<List<String>> table2 = new ArrayList();
      table1.add(Arrays.asList("b1", "b2", "b3"));
      table1.add(Arrays.asList("b4", "b5", "b6"));
      request.setAttribute("tables", List.of(new Table("Table A", table1), new Table("Table B", table2)));
    }
    
    public void page1Post(HttpServletRequest request, HttpServletResponse response)
    {
      
    }

    public void page1Get(HttpServletRequest request, HttpServletResponse response)
    {
      
    }
    
    public void page1Post(HttpServletRequest request, HttpServletResponse response)
    {
      
    }
  }
