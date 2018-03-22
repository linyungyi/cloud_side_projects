package tw.com.cht.core;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

public interface BaseCore {
	public Source getSource(HttpServletRequest request);
	public StreamSource getXSLT(HttpServletRequest request);
}
