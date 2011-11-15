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
 * XML 컨트롤 관련 util성 메소드 모음
 * 
 * @version $Id$
 */
public class XmlUtil {

	private XmlUtil() {
		// not instance
	}

	/**
	 * DOM 객체를 지정된 장소에 저장한다.
	 * 
	 * @param docRootDOM
	 *            객체
	 * @param saveFile
	 *            저장할 파일(XML)
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
	 * JDK 6에서 다음과 같은 에러가 나타날 수 있다.<br>
	 * <strong> com.sun.org.apache.xerces.internal.dom.DeferredTextImpl cannot
	 * be cast to org.w3c.dom.Element</strong><br>
	 * 이 문제를 해결 하게 위해서 해당 메소드 사용<br>
	 * 하위 엘리먼트에 whitespace를 제거<br>
	 * JDK 5에서는 whitespace 제거될 수 있다. 그러나 JDK 6에서는 whitespace 제거 되지 않는다.<br>
	 * 그 이유는 나도 모른다.<br>
	 * === 관련 자료 === <br>
	 * http://forums.java.net/jive/message.jspa?messageID=260761<br>
	 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6564400 <br>
	 * 
	 * @param e
	 *            엘리먼트
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
