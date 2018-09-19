package com.lidong.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlOfDom {
	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			// 传入文件路径，返回Document对象．也就是我们要操作的对象
			Document d = db.parse("resource/bookstore.xml");

			// 通过元素名称解析得到 所有子节点的集合
			NodeList books = d.getElementsByTagName("book");
			for (int i = 0; i < books.getLength(); i++) {
				Node book = books.item(i);

				// 解析得到节点 (**不是子节点**) 的属性的 map集合
				NamedNodeMap mapOfAttr = book.getAttributes();
				for (int j = 0; j < mapOfAttr.getLength(); j++) {
					// 通过map集合返回单个节点
					Node nodeOfAttr = mapOfAttr.item(j);
					// 输出节点名称以及属性值
					System.out.print(nodeOfAttr.getNodeName());
					System.out.println(nodeOfAttr.getNodeValue());
				} // for attrOfRoot

				// 解析子节点
				NodeList childsOfBook = book.getChildNodes();
				for (int l = 0; l < childsOfBook.getLength(); l++) {

					// 节点共有9个，偶数节点没有内容(节点类型为Text)．从0到8.
					if (l % 2 != 0) {
						Node childOfBook = childsOfBook.item(l);
						System.out.print(childOfBook.getNodeName());

						// 注意:闭合标签内部被认为是子节点.此时"冰与火之歌＂是子节点的节点值
						Node grandchildOfBook = childOfBook.getFirstChild();
						System.out.println(grandchildOfBook.getNodeValue());
					}
				}

			}
		} // try
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// main
}
