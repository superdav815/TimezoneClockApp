package view;

import java.util.EventObject;

import model.ClockPanelModel;

public class ClockEvent extends EventObject {

	private ClockPanelModel model;
	public ClockEvent(Object source,ClockPanelModel model){
		super(source);
		this.model = model;
	}
	
	public ClockPanelModel getModel(){
		return model;
	}
	
}
