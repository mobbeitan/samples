import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@WebServlet(value = "/test")
public class Test extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("xml");
        if (param == null) param = "";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            factory.newDocumentBuilder().parse(new InputSource(new StringReader(param)));
        } catch (ParserConfigurationException | SAXException e) {
            throw new ServletException(e);
        }
        response.getWriter().println("ok");
    }
}
