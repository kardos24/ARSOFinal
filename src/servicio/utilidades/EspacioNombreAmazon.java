package servicio.utilidades;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.xml.namespace.NamespaceContext;

public class EspacioNombreAmazon implements NamespaceContext {

	private HashMap<String, String> alias = new HashMap<String, String>();

	public EspacioNombreAmazon() {
		alias.put("a", "http://webservices.amazon.com/AWSECommerceService/2011-08-01");
	}

	@Override
	public String getNamespaceURI(String prefix) {
		return alias.get(prefix);
	}

	@Override
	public String getPrefix(String namespaceURI) {
		for (Entry<String, String> entry : alias.entrySet())
			if (entry.getValue().equals(namespaceURI))
				return entry.getKey();

		return null;
	}

	@Override
	public Iterator<String> getPrefixes(String namespaceURI) {
		return alias.keySet().iterator();
	}

}
