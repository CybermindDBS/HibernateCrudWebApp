public class PageServices
  {
    public void page1Get(HttpServletRequest request, HttpServletResponse response)
    {
      request.setAttribute("pageName", "Hibernate | Single Row Operations");
      request.setAttribute("tables", List.of(new Table(), new Table()));
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
