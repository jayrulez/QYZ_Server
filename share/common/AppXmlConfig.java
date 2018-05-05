package common;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by HuangQiang on 2016/8/21.
 */
public abstract class AppXmlConfig {
    public void load(String filename) throws Exception {
        final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filename);
        final Element root = doc.getDocumentElement();
        parse(root);
    }

    protected abstract void parse(Element root) throws Exception;

    protected final static boolean getBool(Element ele, String key) {
        return ele.getAttribute(key).equalsIgnoreCase("true");
    }

    protected final static int getInt(Element ele, String key) {
        return Integer.parseInt(ele.getAttribute(key));
    }

    protected final static String getString(Element ele, String key) {
        return ele.getAttribute(key);
    }
}
