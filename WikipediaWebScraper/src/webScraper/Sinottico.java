package webScraper;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Sinottico {
	private Map<String, List<Info>> tabella;
	
	public Sinottico() {
		tabella = new HashMap<>();
	}
	
	public void addCategoria(String categoria) {
		tabella.computeIfAbsent(categoria, k -> new ArrayList<>());
	}
	
	public void addInformazione(String categoria, String informazione, String urlInfo) {
		Info info = new Info(informazione, urlInfo);
		tabella.computeIfAbsent(categoria, k -> new ArrayList<>());
		tabella.get(categoria).add(info);
	}
	
	public List<Info> getInformazione(String categoria) {
		if (tabella.containsKey(categoria)) {
			return tabella.get(categoria);
		}
		return null;
	}
	
	public class Info{
		String nomeInfo;
		String urlInfo;
		
		public Info(String nomeInfo, String urlInfo) {
			this.nomeInfo = nomeInfo;
			this.urlInfo = urlInfo;
		}
		
		public String getNomeInfo() {
			return nomeInfo;
		}
		
		public String getUrlInfo() {
			return urlInfo;
		}
		
		@Override
		public String toString(){
			return nomeInfo + " : " + urlInfo;
		}
	}

}
