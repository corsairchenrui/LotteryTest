import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import MySQL.DAO.DAOImpl;
import Parsing.MatchFactory;
import Parsing.data_0Impl;


public class ResultRetriever {
	protected Set<data_0Impl> matches;
	protected MatchFactory factory;
	protected DAOImpl dao;
	protected String issue;
	public ResultRetriever(String issue){
		matches = new HashSet<data_0Impl>();
		factory = new MatchFactory();
		this.issue = issue;
		dao = DAOImpl.newDAO("lottery_data");
	}
	public void add(List<Node> nodes) {
		for(Node n:nodes)
			add(n);
	}
	
	public void add(Node node){
		data_0Impl match = factory.parse1(node);
		if(match!=null)
			matches.add(match);
	}

	
	public static void main(String[] args) {
//		args = new String[2];
//		args[1] = "140208";
		try {
//			new ResultRetriever(args[0]).proceed();
			new ResultRetriever(null).proceed();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void proceed() throws IOException {
//		Document doc = Jsoup.connect("http://888.qq.com/info/bf/index.php?mod=football&op=jczq&qihao="+issue).get();
		Document doc = Jsoup.connect("http://live.caipiao.163.com/dcbf/?date=40204").get();
		for(Node n:doc.getElementById("gameList").childNodes()){
			if(n.nodeName().equals("dd"))
				add(n);
		}
		dao.insert(matches);
	}

}
