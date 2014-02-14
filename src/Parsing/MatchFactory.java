package Parsing;

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
}
