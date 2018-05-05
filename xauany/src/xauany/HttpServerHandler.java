package xauany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Map;

import xauany.utils.HttpUtils;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HttpServerHandler implements HttpHandler {

	@Override
	public void handle(final HttpExchange exchange) throws IOException {
		try {
			final URI uri = exchange.getRequestURI();
			final String path = uri.getPath();
			final String query = getQueryString(exchange);
			if( xdb.Trace.isDebugEnabled()){
				xdb.Trace.debug( "HttpServerHandler path = " + path + " params = " + query);
			}
			PlatProcess process = PlatManager.getPlatProcessByPath(path);
			if(process == null){
				xdb.Trace.warn("PlatProcess not found. Path = " + path);
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				return;
			}
			
			Map<String, String> params = HttpUtils.convertQueryString2Map(query);
			final byte[] result = process.orderCallBack(params);
			if(null == result) {
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
				return;
			}
			
			final Headers responseHeaders = exchange.getResponseHeaders();
			responseHeaders.set("Content-Type", "text/plain");
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			exchange.getResponseBody().write( result);
		}
		catch (Exception e) {
			xdb.Trace.error("handle call back error", e);
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
		}
		finally {
			exchange.close();
		}
	}
	
	private static String getQueryString(HttpExchange exchange) throws IOException {
		if(!"GET".equalsIgnoreCase(exchange.getRequestMethod())){
			String requestBodyString = getRequestBodyString(exchange);
			if(requestBodyString != null && !requestBodyString.isEmpty()){
				return requestBodyString;
			}
		}
		return exchange.getRequestURI().getQuery();
	}

	private static String getRequestBodyString(HttpExchange exchange) throws IOException {
		final BufferedReader bufferreader = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "UTF-8"));
		final StringBuilder result = new StringBuilder();
		for (String line = null; (line = bufferreader.readLine()) != null; ) {
			result.append(line);
		}
		return result.toString();
	}
}
