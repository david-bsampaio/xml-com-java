package br.com.samtech.xmlcomjava.main;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.samtech.xmlcomjava.model.Produto;

public class Principal {

	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);
		dbf.setNamespaceAware(true);
		dbf.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
		DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
		Document documento = documentBuilder.parse("src/vendas.xml");
		
		
		NodeList _venda = documento.getElementsByTagName("venda");
		Element venda = (Element)_venda.item(0);
		System.out.println("Moeda: " + venda.getAttribute("moeda"));
		
		NodeList formasDePagamento = documento.getElementsByTagName("formaDePagamento");
		Element item = (Element)formasDePagamento.item(0);
		
		System.out.println("Forma de Pagamento: " + item.getTextContent());
		
		String exp = "/venda/produtos/produto[2]";
		XPath path = XPathFactory.newInstance().newXPath();
		XPathExpression expression = path.compile(exp);
		
		NodeList produtos = (NodeList) expression.evaluate(documento,XPathConstants.NODESET);
		
		// NodeList produtos = documento.getElementsByTagName("produto");
		
		for (int i = 0; i < produtos.getLength(); i++) {
			Element _produto = (Element) produtos.item(i);
			String nome = _produto.getElementsByTagName("nome").item(0).getTextContent();
			Double preco = Double.parseDouble(_produto.getElementsByTagName("preco").item(0).getTextContent());
			Produto produto = new Produto(nome, preco);
			
			System.out.println(produto);
		}
	}
}
