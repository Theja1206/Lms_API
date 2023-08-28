package resources;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;

public class TimeStamp {
	
	static String timeStamp;
	
	public static String timeStamp() {

		Date currentTime = new Date();
		Date newTime = DateUtils.addSeconds(currentTime, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		// Give it to me in GMT time.
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		timeStamp = sdf.format(newTime);


		return timeStamp;

	}
	public static String headersTimeStamp() {

		Date currentTime = new Date();
		Date newDate = DateUtils.addSeconds(currentTime, -2);
		
		// EEE - Day, HH for time in 24 hour , z for zone 
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm");

		// Give it to me in GMT time.
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		 sdf.format(newDate);
	
		return  sdf.format(newDate);

	}
	
}
