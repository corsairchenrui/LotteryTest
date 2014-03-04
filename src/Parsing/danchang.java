package Parsing;

import MySQL.field;

public class danchang extends data_0Impl {
	@field(type="int")
	protected int leagueId;
	@field(type="varchar")
	protected String matchId;
	@field(type="int")
	protected int hostRedCard;
	@field(type="int")
	protected int visitRedCard;
	@field(type="int")
	protected int matchStatus;
	@field(type="int")
	protected int hostYellowCard;
	@field(type="int")
	protected int visitYellowCard;
	@field(type="varchar")
	protected String hostId;
	@field(type="varchar")
	protected String visitId;

	
	public int getLeagueId() {
		return leagueId;
	}


	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}


	public String getMatchId() {
		return matchId;
	}


	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}


	public int getHostRedCard() {
		return hostRedCard;
	}


	public void setHostRedCard(int hostRedCard) {
		this.hostRedCard = hostRedCard;
	}


	public int getVisitRedCard() {
		return visitRedCard;
	}


	public void setVisitRedCard(int visitRedCard) {
		this.visitRedCard = visitRedCard;
	}


	public int getMatchStatus() {
		return matchStatus;
	}


	public void setMatchStatus(int matchStatus) {
		this.matchStatus = matchStatus;
	}


	public int getHostYellowCard() {
		return hostYellowCard;
	}


	public void setHostYellowCard(int hostYellowCard) {
		this.hostYellowCard = hostYellowCard;
	}


	public int getVisitYellowCard() {
		return visitYellowCard;
	}


	public void setVisitYellowCard(int visitYellowCard) {
		this.visitYellowCard = visitYellowCard;
	}


	public String getHostId() {
		return hostId;
	}


	public void setHostId(String hostId) {
		this.hostId = hostId;
	}


	public String getVisitId() {
		return visitId;
	}


	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}


	public danchang(String tablename) {
		super(tablename);
	}

}
