package com.cdevworks.crudapphjs;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.Tag;

import java.io.IOException;
import java.util.List;

public class TablePopulationTagHandler implements Tag {
    PageContext pageContext;
    StringBuilder html;
    List<Table> tables;

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public Tag getParent() {
        return null;
    }

    @Override
    public void setParent(Tag tag) {

    }

    @Override
    public int doStartTag() throws JspException {
        if (tables == null)
            return SKIP_BODY;

        html = new StringBuilder();
        for (Table table : tables) {
            html.append("<div class='table-container'>");
            html.append("<h2 class='heading-h2'>").append(table.tableName).append("</h2>");
            html.append("<table>");
            html.append("<thead>");
            html.append("<tr>");

            for (String val : table.rows.get(0))
                html.append("<th>").append(val).append("</th>");

            html.append("</tr>");
            html.append("</thead>");
            html.append("<tbody>");

            for (List<String> row : table.rows) {
                if (row.equals(table.rows.get(0)))
                    continue;
                html.append("<tr>");
                for (String val : row) {
                    html.append("<td>").append(val).append("</td>");
                }
                html.append("</tr>");
            }

            html.append("</tbody>");
            html.append("</table>");
            html.append("</div>");
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        if (tables == null)
            return SKIP_BODY;

        try {
            pageContext.getOut().println(html.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return EVAL_PAGE;
    }

    @Override
    public void release() {

    }
}
