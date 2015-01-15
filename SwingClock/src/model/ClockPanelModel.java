package model;

public class ClockPanelModel {
	
	private String timezone;
	private boolean hour24 = false;
	
	public ClockPanelModel(String timezone,boolean hour24){
		this.timezone = timezone;
		this.hour24 = hour24;
	}
	
	public String getTimezone(){
		return timezone;
	}
	
	public boolean ishour24(){
		return hour24;
	}
}
