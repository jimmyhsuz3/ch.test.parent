package ch.test.cxf;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
public class HttpPostClient {
	public static String postMethod(final String url, final Map<String, String> paramMap) throws HttpException, IOException  {
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));   
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Iterator<String> iterator = paramMap.keySet().iterator(); iterator.hasNext();) {
			String paramName = iterator.next();
			params.add(new org.apache.commons.httpclient.NameValuePair(paramName, paramMap.get(paramName)));
		}
		method.setRequestBody(params.toArray(new NameValuePair[0]));
		int statusCode = client.executeMethod(method);
        if (statusCode == HttpStatus.SC_OK) {
        	return convertStreamToString(method.getResponseBodyAsStream());
        }
		return null;
	}
	private static String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {        
            return "";
        }
    }
}