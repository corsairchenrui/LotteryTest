package Parsing;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import MySQL.MySQLEntity;
import MySQL.field;
import MySQL.DAO.DAOImpl;
import utils.quantilizied_ranking;



public class RawParsing {
	protected static class Match extends MySQLEntity{
		@field(type="char")
		protected String date;
		@field(type="int")
		protected int dayofweek;
		@field(type="int")
		protected int indexofday;
		@field(type="varchar")
		protected String hostname;
		@field(type="varchar")
		protected String guestname;
		@field(type="int")
		protected int hostteamid;
		@field(type="int")
		protected int visitteamid;
		@field(type="int")
		protected int difference;
		@field(type="int")
		protected int hRanking;
		@field(type="int")
		protected int aRanking;
		@field(type="int")
		protected int ranking_difference = 4;
		public Match(String tablename) {
			super(tablename);
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public int getDayofweek() {
			return dayofweek;
		}

		public void setDayofweek(int dayofweek) {
			this.dayofweek = dayofweek;
		}

		public int getIndexofday() {
			return indexofday;
		}

		public void setIndexofday(int indexofday) {
			this.indexofday = indexofday;
		}

		public String getHostname() {
			return hostname;
		}

		public void setHostname(String hostname) {
			this.hostname = hostname;
		}

		public String getGuestname() {
			return guestname;
		}

		public void setGuestname(String guestname) {
			this.guestname = guestname;
		}

		public int getHostteamid() {
			return hostteamid;
		}

		public void setHostteamid(int hostteamid) {
			this.hostteamid = hostteamid;
		}

		public int getVisitteamid() {
			return visitteamid;
		}

		public void setVisitteamid(int visitteamid) {
			this.visitteamid = visitteamid;
		}

		public int getDifference() {
			return difference;
		}

		public void setDifference(int difference) {
			this.difference = difference;
		}

		public int getHRanking() {
			return hRanking;
		}

		public void setHRanking(int hRanking) {
			this.hRanking = hRanking;
		}

		public int getARanking() {
			return aRanking;
		}

		public void setARanking(int aRanking) {
			this.aRanking = aRanking;
		}
		
		public int getRanking_difference() {
			return ranking_difference;
		}

		public static Match parse(Element e) {
			Match ret = new Match("data_0");
			String s = e.getAttributeValue("matchcode");
			int len = 8;
			ret.date = s.substring(0, len);
			s = s.substring(len);
			len = 1;
			ret.dayofweek = Integer.valueOf(s.substring(0,len));
			s = s.substring(len);
			ret.indexofday = Integer.valueOf(s);
			ret.hostname = e.getAttributeValue("hostname");
			ret.guestname = e.getAttributeValue("guestname");
			for(Element tE:e.getChildren()){
				String tText;
				switch(tE.getAttributeValue("class")){
				case "co4 hostName":
					tText = tE.getChildTextTrim("i");
					if(null != tText){
						tText = tText.substring(1,tText.length() - 1);
						ret.hRanking = Integer.valueOf(tText);
					}
					break;
				case "co5 score":
					if(tE.getChildTextTrim("em").equals("È¡Ïû"))
						ret.difference = 0;
					else{
						String[] dd = tE.getChildTextTrim("em").split(" ")[1].split(":");
						ret.difference = Integer.valueOf(dd[0]) - Integer.valueOf(dd[1]);
					}
					break;
				case "co6 guestName":
					tText = tE.getChildTextTrim("i");
					if(null != tText){
						tText = tText.substring(1,tText.length() - 1);
						ret.aRanking = Integer.valueOf(tText);
					}
					break;
				}
			}
			ret.ranking_difference = quantilizied_ranking.getQRanking(ret.hRanking - ret.aRanking);
			return ret;
		}
	}
//	public static InputStream step1(final InputStream in){
//        return new InputStream() {
//        	int status = 0;
//        	int len = 0;
//        	int pos = 0;
//        	byte[] data = new byte[1024];
//        	List<Byte> read = new ArrayList<Byte>();
//        	private int read0() throws IOException{
//        		int ret;
//        		if(pos == len){
//					pos = 0;
//					len = in.read(data);
//				}
//        		if(len > pos)
//					ret = data[pos++];
//				else
//					ret = -1;
//        		return ret;
//        	}
//			@Override
//			public int read() throws IOException {
//				int ret;
//				if(read.size() > 0)
//					ret = read.remove(0);
//				else
//					ret = transfer();
//				return ret;
//			}
//			private int read1() {
//				
//			}
//			private byte transfer() throws IOException {
//				if(0 == status){
//					switch(ret){
//					case '<':
//						if(expecting("dd"))
//							
//						break;
//					}
//				}
//				
//				return -1;
//			}
//			private boolean expecting(String string) throws IOException {
//				if(null == string || string.isEmpty() || string.trim().getBytes().length > 1024)
//					return false;
//				byte[] data1 = data;
//				int pos1 = pos;
//				in.mark(1024);
//				byte[] strData = string.trim().getBytes();
//				boolean flag = true;
//				for(int i = 0;i<strData.length;i++){
//					int read;
//					if(0 == i)
//						read = skipBlank();
//					else
//						read = read0();
//					if(strData[i] != read){
//						flag = false;
//						break;
//					}
//				}
//				in.reset();
//				data = data1;
//				pos = pos1;
//				return flag;
//			}
//			private int skipBlank() throws IOException{
//				int ret;
//				while((ret = read0()) == ' '){}
//				return ret;
//			}
//			
//		};
//	}
	public static void step2(InputStream in){
		SAXBuilder sb = new SAXBuilder();
		Set<Match> matchSet = new HashSet<Match>();
		try {
			Document doc = sb.build(in);
			DAOImpl dao = DAOImpl.newDAO("lottery_data");
			for(Element e:doc.getRootElement().getChildren())
				matchSet.add(Match.parse(e));
			dao.insert(matchSet);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		
//		File dir = new File("E:\\");
//		File[] raw = dir.listFiles(new FilenameFilter() {
//			
//			@Override
//			public boolean accept(File dir, String name) {
//				if(name.startsWith("raw"))
//					return true;
//				return false;
//			}
//		});
////		for(File f:raw){
////			try {
////				step2(step1(new FileInputStream(f)));
////			} catch (FileNotFoundException e) {
////				e.printStackTrace();
////			}
////		}
//		try {
//			step2((new FileInputStream(new File("E:\\stuff\\step2\\20131217"))));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	

}
