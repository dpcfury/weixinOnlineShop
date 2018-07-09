package com.slf.wx.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.slf.wx.pay.util.WxPayResultData;
import com.slf.wx.pay.util.WxPaySendData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * Description:测试dom4j的相关功能 Company:苏州咕噜信息科技有限公司
 * 
 * @author dpc
 * @date 2016年7月12日
 * 
 */
public class TestXml {

	// @Test
	public void testXml() throws IOException {

		// 第一种方式：创建文档，并创建根元素
		// 创建文档:使用了一个Helper类
		Document document = DocumentHelper.createDocument();

		// 创建根节点并添加进文档
		Element root = DocumentHelper.createElement("student");
		document.setRootElement(root);

		// 第二种方式:创建文档并设置文档的根元素节点
		Element root2 = DocumentHelper.createElement("student");
		Document document2 = DocumentHelper.createDocument(root2);

		// 添加属性
		root2.addAttribute("name", "zhangsan");
		// 添加子节点:add之后就返回这个元素
		Element helloElement = root2.addElement("hello");
		Element worldElement = root2.addElement("world");

		helloElement.setText("hello Text");
		worldElement.setText("world text");

		// 输出
		// 输出到控制台
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.write(document2);

		// 输出到文件
		// 格式
		OutputFormat format = new OutputFormat("    ", true);// 设置缩进为4个空格，并且另起一行为true
		XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream("student.xml"), format);
		xmlWriter2.write(document2);

		// 另一种输出方式，记得要调用flush()方法,否则输出的文件中显示空白
		XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("student2.xml"), format);
		xmlWriter3.write(document2);
		xmlWriter3.flush();
		// close()方法也可以
	}

	// @Test
	public void testReadXml() throws DocumentException, ParserConfigurationException, SAXException, IOException {
		SAXReader saxReader = new SAXReader();

		Document document = saxReader.read(new File("/students.xml"));

		// 获取根元素
		Element root = document.getRootElement();
		System.out.println("Root: " + root.getName());

		// 获取所有子元素
		List<Element> childList = root.elements();
		System.out.println("total child count: " + childList.size());

		// 获取特定名称的子元素
		List<Element> childList2 = root.elements("hello");
		System.out.println("hello child: " + childList2.size());

		// 获取名字为指定名称的第一个子元素
		Element firstWorldElement = root.element("world");
		// 输出其属性
		System.out.println("first World Attr: " + firstWorldElement.attribute(0).getName() + "="
				+ firstWorldElement.attributeValue("name"));

		System.out.println("迭代输出-----------------------");
		// 迭代输出
		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			System.out.println(e.attributeValue("name"));

		}

		System.out.println("用DOMReader-----------------------");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 注意要用完整类名
		org.w3c.dom.Document document2 = db.parse(new File("students.xml "));

		DOMReader domReader = new DOMReader();

		// 将JAXP的Document转换为dom4j的Document
		Document document3 = domReader.read(document2);

		Element rootElement = document3.getRootElement();

		System.out.println("Root: " + rootElement.getName());

	}

	// @Test
	public void testToXml() {
		WxPaySendData data = new WxPaySendData();
		data.setAppid("wx2421b1c4370ec43b");
		data.setAttach("支付测试");
		data.setBody("JSAPI支付测试");
		data.setMch_id("10000100");
		data.setNonce_str("1add1a30ac87aa2db72f57a2375d8fec");
		data.setNotify_url("http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php");
		data.setOpenid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
		data.setOut_trade_no("1415659990");
		data.setSpbill_create_ip("14.23.150.211");
		data.setTotal_fee(1);
		data.setTrade_type("JSAPI");
		data.setSign("0CB01533B8C1EF103065174F50BCA001");

		XStream xs = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		xs.alias("xml", WxPaySendData.class);
		String xml = xs.toXML(data);
		System.out.println(xml);
	}

//	@Test
	public void testFromXml() {
		String result = "<xml><return_-code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx2421b1c4370ec43b]]></appid><mch_id><![CDATA[10000100]]></mch_id>"
				+ " <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str><sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>"
				+ " <result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>"
				+ "<trade_type><![CDATA[JSAPI]]></trade_type></xml>";
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", WxPayResultData.class);
		WxPayResultData resultData = (WxPayResultData) xstream.fromXML(result);
		System.out.println("从xml读取出的返回对象为:"+resultData);
	}

}
