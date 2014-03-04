package Parsing;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import utils.quantilizied_ranking;

public class MatchFactory {
	public data_0Impl parse(Node node) {
		if(node!=null&&node.attr("match_status").equals("-1")){
			data_0Impl ret = new data_0Impl("danchang");
			ret.hostname = node.attr("value").split("\\|")[1].split(",")[0];
			ret.guestname = node.attr("value").split("\\|")[2].split(",")[0];
			try{
				ret.date = node.attr("which_match").split("\\|")[1].replaceAll("-", "");
				ret.hRanking = Integer.parseInt(node.attr("lgsort").split(",")[0]);
				ret.aRanking = Integer.parseInt(node.attr("lgsort").split(",")[1]);
				ret.hgoal = Integer.parseInt(node.attr("host_bf"));
				ret.agoal = Integer.parseInt(node.attr("guest_bf"));
				ret.difference = ret.hgoal - ret.agoal;
				ret.concede = Integer.parseInt(node.attr("rangqiu_rqspf"));
				ret.level = quantilizied_ranking.getQRanking(ret.hRanking - ret.aRanking);
			}catch(NumberFormatException nfe){
				return null;
			}
			return ret;
		}
		return null;
	}

	public data_0Impl parse1(Node node) {
		if(node!=null){
			danchang ret = new danchang("danchang");
			ret.leagueId = Integer.parseInt(node.attr("leagueId"));
			ret.matchId = node.attr("matchId");
			ret.hostId = node.attr("hostId");
			ret.visitId = node.attr("visitId");
			ret.hostRedCard = Integer.parseInt(node.attr("hostRedCard"));
			ret.visitRedCard = Integer.parseInt(node.attr("visitRedCard"));
			ret.hostYellowCard = Integer.parseInt(node.attr("hostYellowCard"));
			ret.visitYellowCard = Integer.parseInt(node.attr("visitYellowCard"));
			ret.matchStatus = Integer.parseInt(node.attr("matchStatus"));
			for(Node n:node.childNodes()){
				switch(n.attr("class")){
				case "analysisDetail":
					ret.concede = Integer.parseInt(((Element)n).getElementsByAttributeValue("class", "concede").text());
					break;
				case "vsDetail":
					Element host = ((Element)n).getElementsByAttributeValue("class", "host").get(0);
					Element guest = ((Element)n).getElementsByAttributeValue("class", "guest").get(0);
					int[] tRanking = new int[2];
					for(String s:host.text().split(" ")){
						s = s.trim();
						if(s.matches("\\(\\d+\\).+")){
							s = s.substring(1, s.indexOf(")"));
							tRanking[0] = Integer.parseInt(s);
						}
					}
					for(String s:guest.text().split(" ")){
						s = s.trim();
						if(s.matches(".+\\(\\d+\\)")){
							s = s.substring(s.lastIndexOf("(")+1,s.length()-1);
							tRanking[1] = Integer.parseInt(s);
						}
					}
					if(tRanking[0] != 0&&tRanking[1] != 0){
						ret.hRanking = tRanking[0];
						ret.aRanking = tRanking[1];
						ret.level = quantilizied_ranking.getQRanking(ret.hRanking - ret.aRanking);
					}
					break;
				}
			}
			
			if(ret.matchStatus == 0){
				String[] splittedScore = node.attr("score").split(":");
				ret.hgoal = Integer.parseInt(splittedScore[0]);
				ret.agoal = Integer.parseInt(splittedScore[1]);
				ret.difference = ret.hgoal - ret.agoal;
			}
			return ret;
		}
		return null;
	}
}
