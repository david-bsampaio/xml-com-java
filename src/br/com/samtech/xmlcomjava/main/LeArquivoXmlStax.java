package br.com.samtech.xmlcomjava.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

import br.com.samtech.xmlcomjava.model.Produto;

public class LeArquivoXmlStax {

	private static XMLEventReader eventos;

	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("src/vendas.xml");
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		eventos = inputFactory.createXMLEventReader(is);
		List<Produto> produtos = new ArrayList<Produto>();

		while (eventos.hasNext()) {
			XMLEvent evento = eventos.nextEvent();

			if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("produto")) {
				Produto produto = criaProduto(eventos);
				produtos.add(produto);
			}
		}
		System.out.println(produtos);
	}

	private static Produto criaProduto(XMLEventReader eventos) throws Exception {
		Produto produto = new Produto();
		
		while (eventos.hasNext()) {
			XMLEvent evento = eventos.nextEvent();
			if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("nome")) {
				evento = eventos.nextEvent();
				String _nome = evento.asCharacters().getData();
				produto.setNome(_nome);
			} else if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("preco")) {
				evento = eventos.nextEvent();
				Double _preco = Double.parseDouble(evento.asCharacters().getData());
				produto.setValor(_preco);
			} else if (evento.isEndElement() && evento.asEndElement().getName().getLocalPart().equals("produto")) {
				break;
			}
		}
		return produto;
	}
}
