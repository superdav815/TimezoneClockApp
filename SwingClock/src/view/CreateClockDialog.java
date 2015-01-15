package view;


import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.ClockPanelModel;
import model.Country;

public class CreateClockDialog {
	private JDialog dialog;
	private JPanel panel;
	private JLabel countryLabel;
	private JLabel selectLabel;
	private JComboBox comboBox;
	private JRadioButton hour24btn;
	private JRadioButton hour12btn;
	private JButton okBtn;
	private JButton cancelBtn;
	private Country country;
	private ClockFrame frame;
	private CreateClockEventListener listener;

	private ClockPanelModel clockPanelModel;
	private JPanel contentPanel;
	
	public CreateClockDialog(ClockFrame frame){
		this.frame = frame;
		panel = new JPanel();
		dialog = new JDialog();
		
		addComponentsToPanel();
		
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				exitProcedure();
			}
			
		});
		//dialog.getSize();
		dialog.setSize(300, 300);

	}
	public void exitProcedure(){
		dialog.setVisible(false);
		dialog.dispose();
	}
	public void setClockEventListener(CreateClockEventListener listener){
		this.listener = listener;
	}
	
	public void initState(){
		
	}
	
	public void setVisible(boolean b){
		if (b) dialog.setVisible(true);
		else dialog.setVisible(false);
	}
	
	public void addComponentsToPanel(){
		countryLabel = new JLabel("Timezone");
    	selectLabel = new JLabel("Select");
    	hour24btn = new JRadioButton("24 hour");
    	hour12btn = new JRadioButton("12 hour");
    	okBtn = new JButton("Ok");
    	cancelBtn = new JButton("Cancel");
    	country = new Country();
    	comboBox = new JComboBox(country.getTimezones());
    	hour12btn.setSelected(true);
    	
    	okBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selection = (String)comboBox.getSelectedItem();
				boolean hour24 = hour24btn.isSelected();
				clockPanelModel = new ClockPanelModel(selection,hour24);
				ClockEvent ev = new ClockEvent(CreateClockDialog.this,clockPanelModel);
				if (listener!= null){
					listener.ClockEventOccurred(ev);
					exitProcedure();
				}
				
			}
		});
    	
    	cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exitProcedure();
			}
		});
    	
    	ButtonGroup hourbg = new ButtonGroup(); 
    	hourbg.add(hour12btn);hourbg.add(hour24btn);
    	
		panel.setLayout(new GridBagLayout());
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,0,15,10);
		panel.add(countryLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(comboBox, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.NONE;
		panel.add(selectLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(hour24btn, c);
		
		c.gridx = 1;
		c.gridy= 2;
		
		panel.add(hour12btn, c);
		
		c.insets = new Insets(0,0,15,10);
		c.gridx = 0;
		c.gridy= 3;
		panel.add(okBtn, c);
		
		c.gridx = 1;
		c.gridy= 3;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(cancelBtn, c);
		
		dialog.add(panel);
	}
	public ClockPanelModel getClockPanelModel(){
		return clockPanelModel;
	}
}
