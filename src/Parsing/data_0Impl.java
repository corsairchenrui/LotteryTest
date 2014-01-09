package Parsing;

import MySQL.MySQLEntity;
import MySQL.field;


public class data_0Impl extends MySQLEntity {
	public data_0Impl(String tablename) {
		super(tablename);
	}
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
	public int gethRanking() {
		return hRanking;
	}
	public void sethRanking(int hRanking) {
		this.hRanking = hRanking;
	}
	public int getaRanking() {
		return aRanking;
	}
	public void setaRanking(int aRanking) {
		this.aRanking = aRanking;
	}
}
