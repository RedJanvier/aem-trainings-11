package it.codeland.academy.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;

@Component(service = Servlet.class)
@SlingServletPaths(value = {"/bin/pages"})
public class JanvierServlet extends SlingAllMethodsServlet {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {
        final ResourceResolver resourceResolver = request.getResourceResolver();
        Page page = resourceResolver.adaptTo(PageManager.class).getPage("/content/academy-janvier/us/en");

        JSONArray pagesArr = new JSONArray();
        try {
            Iterator<Page> childPages = page.listChildren();
            while(childPages.hasNext()) {
                Page childPage = childPages.next();
                JSONObject pageObject1 = new JSONObject();
                pageObject1.put("title", childPage.getTitle());
                pageObject1.put("path", childPage.getPath().toString());
                pagesArr.put(pageObject1);
            }
        } catch (Exception e) {
            log.error("Some error that occurred, {}", e);
        }

        response.setContentType("application/json");
        response.getWriter().write(pagesArr.toString());
    }
}
