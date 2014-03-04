package Parsing;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public class MyNode extends Element {

	public MyNode(Tag tag, String baseUri) {
		super(tag, baseUri);
	}

	public MyNode(Tag tag, String baseUri, Attributes attributes) {
		super(tag, baseUri, attributes);
	}
	
	public List<Element> getElementByAttr(String attrName, String attrValue){
		if(!StringUtil.isBlank(attrName) && !StringUtil.isBlank(attrValue)){
			return this.getElementsByAttributeValue(attrName, attrValue);
		}
		return null;
	}

}
