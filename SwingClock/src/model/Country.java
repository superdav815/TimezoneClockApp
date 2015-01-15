package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 *read all timezones from timezones.txt and put into list
 */
public class Country {
	
	//private List<String> timezones;
	private String[] timezones;
	
	public Country(){
		timezones = new String[561];
		try {

			InputStream is = Country.class.getClassLoader().getResourceAsStream("timezones.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String str = new String();
			int i =0;
			while ((str = br.readLine()) != null){
				timezones[i++] = str;
			}
			is.close();
			br.close();
			System.out.println(timezones.length);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	public String[] getTimezones(){
		return timezones;
	}
}
