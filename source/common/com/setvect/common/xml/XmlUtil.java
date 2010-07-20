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
 * XML 컨트롤 관련 util성 메소드 모음
 * 
 * @author <a href="mailto:setvect@setvect.com">장정호 </a>
 * @version $Id$
 */
public class XmlUtil {
	/** XML root 태그 */
	public final static String ROOT_TAG_NAME = "list";

	/** 데이터 항목을 나누는 테그 */
	public final static String ARTICLE_TAG_NAME = "article";

	private XmlUtil() {
		// not instance
	}

	/**
	 * 리턴되는 형태 <br>
	 * &lt;?xml version=&quot;1.0&quot; encoding=&quot;utf-8&quot;?&gt;
	 * <p>
	 * &lt;list&gt;<br>
	 * &lt;article&gt;<br>
	 * &lt;id&gt;31414_dqadmin&lt;/id&gt;<br>
	 * &lt;name&gt;안녕&lt;/name&gt;<br>
	 * &lt;memo&gt;adsafdasf&lt;/memo&gt;<br>
	 * &lt;score&gt;1&lt;/score&gt;<br>
	 * &lt;date&gt;2008-11-11 18:04:22&lt;/date&gt;<br>
	 * &lt;/article&gt;<br>
	 * &lt;id&gt;31414_dqadmin111&lt;/id&gt;<br>
	 * &lt;name&gt;복슬이&lt;/name&gt;<br>
	 * &lt;memo&gt;adsafdasf&lt;/memo&gt;<br>
	 * &lt;score&gt;1&lt;/score&gt;<br>
	 * &lt;date&gt;2008-11-11 18:04:22&lt;/date&gt;<br>
	 * &lt;/article&gt;<br>
	 * &lt;/list&gt;
	 * </p>
	 * 
	 * @param recordSet
	 *            변환할 데이터
	 * @param charset
	 *            xml 캐릭터 셋
	 * @return XML 포맷으로 변환
	 */
	public static String makeXmlString(ListMap recordSet, String charset) {
		Document docRoot = DocumentHelper.createDocument();
		Element contents = docRoot.addElement(ROOT_TAG_NAME);

		String[] keys = recordSet.getKeys();
		while (recordSet.next()) {
			Element content = contents.addElement(ARTICLE_TAG_NAME);
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				// null 값을 빈문자열로 변경
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
			// XML 본문 저장
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
			} else if (child instanceof Element) {
				removeWhitespaceNodes((org.w3c.dom.Element) child);
			}
		}
	}
}
