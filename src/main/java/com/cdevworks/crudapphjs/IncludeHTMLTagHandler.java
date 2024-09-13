package com.cdevworks.crudapphjs;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.PageContext;
import jakarta.servlet.jsp.tagext.Tag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IncludeHTMLTagHandler implements Tag {
    String filePath;
    PageContext pageContext;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            if (pageContext.getRequest().getParameter("pageId").equals("1")) {
                String html = new String(Files.readAllBytes(Path.of(pageContext.getServletContext().getRealPath(filePath))));
                pageContext.getOut().println(html);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return EVAL_PAGE;
    }

    @Override
    public void release() {

    }
}
