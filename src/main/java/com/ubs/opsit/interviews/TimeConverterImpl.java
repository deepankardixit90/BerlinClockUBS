package com.ubs.opsit.interviews;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeConverterImpl implements TimeConverter {

	
	private static final Logger LOG = LoggerFactory.getLogger(TimeConverterImpl.class);
	
	public TimeConverterImpl() {
		LOG.info("TimeConverterImpl constructor called");
	}

	@Override
	public String convertTime(String aTime) {		
		int[] parts = Stream.of(aTime.split(":")).mapToInt(Integer::parseInt).toArray();
        return new String (
                getSeconds(parts[2])+"\n"+
                getTopHours(parts[0])+"\n"+
                getBottomHours(parts[0])+"\n"+
                getTopMinutes(parts[1])+"\n"+
                getBottomMinutes(parts[1])
        );
	}
	
	protected String getSeconds(int number) {
        if (number % 2 == 0) return "Y";
        else return "O";
    }
 
    protected String getTopHours(int number) {
        return getOnOff(4, getTopNumberOfOnSigns(number));
    }
 
    protected String getBottomHours(int number) {
        return getOnOff(4, number % 5);
    }
 
    protected String getTopMinutes(int number) {
        return getOnOff(11, getTopNumberOfOnSigns(number), "Y").replaceAll("YYY", "YYR");
    }
 
    protected String getBottomMinutes(int number) {
        return getOnOff(4, number % 5, "Y");
    }
 
    private String getOnOff(int lamps, int onSigns) {
        return getOnOff(lamps, onSigns, "R");
    }
    private String getOnOff(int lamps, int onSigns, String onSign) {
        String out = "";
        for (int i = 0; i < onSigns; i++) {
            out += onSign;
        }
        for (int i = 0; i < (lamps - onSigns); i++) {
            out += "O";
        }
        return out;
    }
 
    private int getTopNumberOfOnSigns(int number) {
        return (number - (number % 5)) / 5;
    }
}
