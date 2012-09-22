/*
 * setvect 
 */
package com.setvect.common.xml;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * XML, XSD를 이용하여 Docuemnt 객체를 만들어줌
 * 
 * @version $Id$
 */
public class XMLParser {
	/**
	 * @param xmlFile
	 *            XML 파일
	 * @param xmlSchema
	 *            스키마 파일
	 * @return 해당 XML Root Document
	 * 
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Document parsing(File xmlFile, File xmlSchema) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {

		Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xmlSchema);

		return parsing(xmlFile, schema);
	}

	/**
	 * @param xmlFile
	 *            XML 파일
	 * @param xmlSchema
	 *            스키마 파일
	 * @return 해당 XML Root Document
	 * 
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document parsing(File xmlFile, URL xmlSchema) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {

		Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xmlSchema);

		return parsing(xmlFile, schema);
	}

	/**
	 * @param xmlFile
	 *            XML 파일 경로
	 * @param xmlSchema
	 *            XML 스키마 파일 경로
	 * @return 해당 XML Root Document
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document parsing(String xmlFile, String xmlSchema) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {
		return parsing(new File(xmlFile), new File(xmlSchema));
	}

	/**
	 * @param xmlFile
	 *            XML 파일
	 * @param xmlSchema
	 *            스키마 파일
	 * @return 해당 XML Root Document
	 * 
	 * @throws XPathExpressionException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document parsing(URL xmlFile, URL xmlSchema) throws XPathExpressionException,
			ParserConfigurationException, SAXException, IOException {

		Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xmlSchema);
		return parsing(xmlFile.openStream(), schema);
	}

	/**
	 * @param xmlFile
	 *            XML 파일
	 * @param schema
	 *            스키마 정보
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static Document parsing(File xmlFile, Schema schema) throws ParserConfigurationException, SAXException,
			IOException {
		return parsing(new FileInputStream(xmlFile), schema);
	}

	/**
	 * @param openStream
	 *            XML 스트림
	 * @param schema
	 *            스키마 정보
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	private static Document parsing(InputStream openStream, Schema schema) throws ParserConfigurationException,
			SAXException, IOException {
		BufferedInputStream bis = null;
		DocumentBuilder builder = null;
		try {

			// DOM 파서 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setIgnoringElementContentWhitespace(true);
			factory.setSchema(schema);

			builder = factory.newDocumentBuilder();
			bis = new BufferedInputStream(openStream);
			return builder.parse(bis);
		} finally {
			if (bis != null) {
				bis.close();
			}
		}
	}

}
