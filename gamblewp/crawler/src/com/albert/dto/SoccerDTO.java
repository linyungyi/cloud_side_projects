package com.albert.dto;

public class SoccerDTO {
	String homeName;
	String visitName;
	String gameNumber;
	String eventDatetime;
	String League;
	double moneyLineVisit;
	double moneyLineHome;
	double moneyLineDraw;
	double totalPoint;
	double overAdjust;
	double underAdjust;

	public String getHomeName() {
		return homeName;
	}
	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}
	public String getVisitName() {
		return visitName;
	}
	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}
	public String getGameNumber() {
		return gameNumber;
	}
	public void setGameNumber(String gameNumber) {
		this.gameNumber = gameNumber;
	}
	public String getEventDatetime() {
		return eventDatetime;
	}
	public void setEventDatetime(String eventDatetime) {
		this.eventDatetime = eventDatetime;
	}
	public String getLeague() {
		return League;
	}
	public void setLeague(String league) {
		League = league;
	}
	public double getMoneyLineVisit() {
		return moneyLineVisit;
	}
	public void setMoneyLineVisit(double moneyLineVisit) {
		this.moneyLineVisit = moneyLineVisit;
	}
	public double getMoneyLineHome() {
		return moneyLineHome;
	}
	public void setMoneyLineHome(double moneyLineHome) {
		this.moneyLineHome = moneyLineHome;
	}
	public double getMoneyLineDraw() {
		return moneyLineDraw;
	}
	public void setMoneyLineDraw(double moneyLineDraw) {
		this.moneyLineDraw = moneyLineDraw;
	}
	public double getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(double totalPoint) {
		this.totalPoint = totalPoint;
	}
	public double getOverAdjust() {
		return overAdjust;
	}
	public void setOverAdjust(double overAdjust) {
		this.overAdjust = overAdjust;
	}
	public double getUnderAdjust() {
		return underAdjust;
	}
	public void setUnderAdjust(double underAdjust) {
		this.underAdjust = underAdjust;
	}
}
