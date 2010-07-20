/*
 * setvect 
 */
package com.setvect.common.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.setvect.common.collection.ListMap;
import com.setvect.common.collection.StringUtil;

/**
 * XML ��Ʈ�� ���� util�� �޼ҵ� ����
 * 
 * @author <a href="mailto:setvect@setvect.com">����ȣ </a>
 * @version $Id$
 */
public class XmlUtil {
	/** XML root �±� */
	public final static String ROOT_TAG_NAME = "list";

	/** ������ �׸��� ������ �ױ� */
	public final static String ARTICLE_TAG_NAME = "article";

	private XmlUtil() {
		// not instance
	}

	/**
	 * ���ϵǴ� ���� <br>
	 * &lt;?xml version=&quot;1.0&quot; encoding=&quot;utf-8&quot;?&gt;
	 * <p>
	 * &lt;list&gt;<br>
	 * &lt;article&gt;<br>
	 * &lt;id&gt;31414_dqadmin&lt;/id&gt;<br>
	 * &lt;name&gt;�ȳ�&lt;/name&gt;<br>
	 * &lt;memo&gt;adsafdasf&lt;/memo&gt;<br>
	 * &lt;score&gt;1&lt;/score&gt;<br>
	 * &lt;date&gt;2008-11-11 18:04:22&lt;/date&gt;<br>
	 * &lt;/article&gt;<br>
	 * &lt;id&gt;31414_dqadmin111&lt;/id&gt;<br>
	 * &lt;name&gt;������&lt;/name&gt;<br>
	 * &lt;memo&gt;adsafdasf&lt;/memo&gt;<br>
	 * &lt;score&gt;1&lt;/score&gt;<br>
	 * &lt;date&gt;2008-11-11 18:04:22&lt;/date&gt;<br>
	 * &lt;/article&gt;<br>
	 * &lt;/list&gt;
	 * </p>
	 * 
	 * @param recordSet
	 *            ��ȯ�� ������
	 * @param charset
	 *            xml ĳ���� ��
	 * @return XML �������� ��ȯ
	 */
	public static String makeXmlString(ListMap recordSet, String charset) {
		Document docRoot = DocumentHelper.createDocument();
		Element contents = docRoot.addElement(ROOT_TAG_NAME);

		String[] keys = recordSet.getKeys();
		while (recordSet.next()) {
			Element content = contents.addElement(ARTICLE_TAG_NAME);
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				// null ���� ���ڿ��� ����
				String v = StringUtil.nvl(recordSet.getValue(key), "");
				content.addElement(key).addText(v);
			}
		}
		OutputFormat format = new OutputFormat("\t", true, charset);
		StringWriter out1 = new StringWriter();
		XMLWriter writer = new XMLWriter(out1, format);
		String xmlString = null;
		try {
			writer.write(docRoot);
			writer.flush();
			// XML ���� ����
			xmlString = out1.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out1 != null) {
					out1.close();
				}
			} catch (IOException e) {
				// ignore
			}

			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				// ignore
			}
		}
		return xmlString;
	}

	/**
	 * DOM ��ü�� ������ ��ҿ� �����Ѵ�.
	 * 
	 * @param docRootDOM
	 *            ��ü
	 * @param saveFile
	 *            ������ ����(XML)
	 * @param charset
	 *            charset
	 */
	public static void saveXML(org.dom4j.Document docRoot, File saveFile, String charset) {
		OutputFormat format = new OutputFormat("\t", true, charset);
		FileOutputStream out1 = null;
		XMLWriter writer = null;
		try {
			out1 = new FileOutputStream(saveFile);
			OutputStreamWriter osw = new OutputStreamWriter(out1, charset);
			writer = new XMLWriter(osw, format);
			writer.write(docRoot);
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (out1 != null) {
					out1.close();
				}
			} catch (IOException e) {
				// ignore
			}
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				// ignore
			}
		}
	}

	/**
	 * JDK 6���� ������ ���� ������ ��Ÿ�� �� �ִ�.<br>
	 * <strong> com.sun.org.apache.xerces.internal.dom.DeferredTextImpl cannot
	 * be cast to org.w3c.dom.Element</strong><br>
	 * �� ������ �ذ� �ϰ� ���ؼ� �ش� �޼ҵ� ���<br>
	 * ���� ������Ʈ�� whitespace�� ����<br>
	 * JDK 5������ whitespace ���ŵ� �� �ִ�. �׷��� JDK 6������ whitespace ���� ���� �ʴ´�.<br>
	 * �� ������ ���� �𸥴�.<br>
	 * === ���� �ڷ� === <br>
	 * http://forums.java.net/jive/message.jspa?messageID=260761<br>
	 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6564400 <br>
	 * 
	 * @param e
	 *            ������Ʈ
	 */
	public static void removeWhitespaceNodes(org.w3c.dom.Element e) {
		NodeList children = e.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--) {
			Node child = children.item(i);
			if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
				e.removeChild(child);
			} else if (child instanceof Element) {
				removeWhitespaceNodes((org.w3c.dom.Element) child);
			}
		}
	}
}
