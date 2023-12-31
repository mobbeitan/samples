import java.io.IOException;
import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicRequestLine;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApacheHttpSSRF extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String sink = request.getParameter("uri");
            URI uri = new URI(sink);

            HttpGet httpGet = new HttpGet(uri); // $ SSRF
            HttpGet httpGet2 = new HttpGet();
            httpGet2.setURI(uri); // $ SSRF

            new BasicHttpRequest(new BasicRequestLine("GET", uri.toString(), null)); // $ SSRF

            RequestBuilder.get(uri.toString()); // $ SSRF
            RequestBuilder.get("").setUri(uri); // $ SSRF
            RequestBuilder.get().setUri(uri); // $ SSRF

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
