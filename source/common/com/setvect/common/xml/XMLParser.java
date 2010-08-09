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
 * XML, XSD�� �̿��Ͽ� Docuemnt ��ü�� �������
 * 
 * @version $Id$
 */
public class XMLParser {
	/**
	 * @param xmlFile
	 *            XML ����
	 * @param xmlSchema
	 *            ��Ű�� ����
	 * @return �ش� XML Root Document
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
	 *            XML ����
	 * @param xmlSchema
	 *            ��Ű�� ����
	 * @return �ش� XML Root Document
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
	 *            XML ���� ���
	 * @param xmlSchema
	 *            XML ��Ű�� ���� ���
	 * @return �ش� XML Root Document
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
	 *            XML ����
	 * @param xmlSchema
	 *            ��Ű�� ����
	 * @return �ش� XML Root Document
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
	 *            XML ����
	 * @param schema
	 *            ��Ű�� ����
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
	 *            XML ��Ʈ��
	 * @param schema
	 *            ��Ű�� ����
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

			// DOM �ļ� ����
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setIgnoringElementContentWhitespace(true);
			factory.setSchema(schema);

			builder = factory.newDocumentBuilder();
			System.out.println(factory);
			System.out.println(builder);
			bis = new BufferedInputStream(openStream);
			return builder.parse(bis);
		} finally {
			if (bis != null) {
				bis.close();
			}
		}
	}

}
