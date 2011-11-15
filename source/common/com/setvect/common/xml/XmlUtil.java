/*
 * setvect 
 */
package com.setvect.common.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * XML ��Ʈ�� ���� util�� �޼ҵ� ����
 * 
 * @version $Id$
 */
public class XmlUtil {

	private XmlUtil() {
		// not instance
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
			}
			else if (child instanceof Element) {
				removeWhitespaceNodes((org.w3c.dom.Element) child);
			}
		}
	}
}
