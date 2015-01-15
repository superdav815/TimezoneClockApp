package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.font.LineMetrics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ClockPanelModel;

public class ClockPanel extends JPanel {
	private ClockPanelModel clockPanelModel;
	private ClockFrame frame;
	private JLabel timezoneLabel;
	private JLabel time;
	private int hour, minute, second;
	private int[] fontColor = { 0xBBC23C, 0xFFFFFF, 0x683031 };
	private int[] backColor = {0x181818,0xD41B2D,0x412C71,0x9CA93D,0x0B7AA7};
	private int ranfont, ranback;
	private String timezone;
	private boolean hour24 = false; //false = 12 hour, true = 24 hour
	private Font font = new Font("Arial", Font.BOLD, 14);

	public ClockPanel(ClockFrame frame, ClockPanelModel model) {
		timezone = model.getTimezone();
		hour24 = model.ishour24();
		this.frame = frame;
		this.clockPanelModel = model;
		Random ran = new Random();
		ranfont = ran.nextInt(3);
		ranback = ran.nextInt(5);
		setPreferredSize(new Dimension(150,150));
		setBackground(new Color(backColor[ranback]));
		setVisible(true);
		createPartControl();
		

	}

	public void createPartControl() {

		Timer t = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		t.start();
	}

	@Override
	protected void paintComponent(Graphics v) {
		super.paintComponent(v);
		Calendar cal = new GregorianCalendar(
				TimeZone.getTimeZone(clockPanelModel.getTimezone()));
		
		if (hour24){
			hour = cal.get(Calendar.HOUR_OF_DAY);
			minute = cal.get(Calendar.MINUTE); // 0..59
			second = cal.get(Calendar.SECOND); // 0..59
		}else{
			hour = cal.get(Calendar.HOUR); // 0..11
			minute = cal.get(Calendar.MINUTE); // 0..59
			second = cal.get(Calendar.SECOND); // 0..59
		}

		
		String timeFormat = timezone;
		String time = "=> "+hour + ":" + minute + ":" + second;
		
		v.setFont(font);

	    FontMetrics fm = getFontMetrics(new Font("Arial",Font.BOLD,13));
	    int y = fm.getHeight() + fm.getAscent();
	    char [] c = timeFormat.toCharArray();
	    int width = fm.charsWidth(c, 0, c.length);
	    Rectangle r = getBounds();
	    v.setColor(Color.WHITE);
	    v.drawString(timeFormat, (int)(r.getWidth()/2)-(width/2), (int)(r.getHeight()/2));
	    v.drawString(time, (int)(r.getWidth()/2)-(width/2),(int)(r.getHeight()/2)+y);
	}

}
