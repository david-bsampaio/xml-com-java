package br.com.samtech.xmlcomjava.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.samtech.xmlcomjava.model.Produto;

public class ProdutosHandler extends DefaultHandler {
	
	private List<Produto> produtos = new ArrayList<Produto>();
	private StringBuilder _conteudo;
	private Produto _produto;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// System.out.println(qName);
		_conteudo = new StringBuilder();
		
		if (qName.equals("produto")) {
			_produto = new Produto();
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		_conteudo.append(new String(ch, start, length));
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("produto")) {
			produtos.add(_produto);
		} else if(qName.equals("nome")) {
			_produto.setNome(_conteudo.toString());
		} else if(qName.equals("preco")) {
			_produto.setValor(Double.parseDouble(_conteudo.toString()));
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}
