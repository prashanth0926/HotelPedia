package helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditDates {

	public ArrayList<HashMap<String,Long>> addDates(long startDate,long endDate,ArrayList<HashMap<String,Long>> dates)
	{
		ArrayList<HashMap<String,Long>> newDates = new ArrayList<>();
		boolean status = true;
		for(HashMap<String, Long> tempdate : dates)
		{
			Long end 	= tempdate.get("endDate");
			Long start	= tempdate.get("startDate");
			Long dateStatus 	= tempdate.get("status");
			
			if(end >= startDate && dateStatus == (long)1 && status)
			{
				if(end == endDate && start != startDate)
				{
					HashMap<String, Long> dateTemp = new HashMap<String, Long>();
					dateTemp.put("startDate", start);
					dateTemp.put("endDate", startDate-1);
					dateTemp.put("status", (long)1);
					newDates.add(dateTemp);
					
					dateTemp = new HashMap<String, Long>();
					dateTemp.put("startDate", startDate);
					dateTemp.put("endDate", endDate);
					dateTemp.put("status", (long)0);
					newDates.add(dateTemp);
				}
				else if(start == startDate && end != endDate)
				{
					HashMap<String, Long> dateTemp = new HashMap<String, Long>();
					dateTemp.put("startDate", startDate);
					dateTemp.put("endDate", endDate);
					dateTemp.put("status", (long)0);
					newDates.add(dateTemp);
					
					dateTemp = new HashMap<String, Long>();
					dateTemp.put("startDate", endDate+1);
					dateTemp.put("endDate", end);
					dateTemp.put("status", (long)1);
					newDates.add(dateTemp);
				}
				else if(start == startDate && end == endDate)
				{
					tempdate.put("status", (long)0);
					newDates.add(tempdate);
				}
				else
				{
					tempdate.put("endDate", startDate-1);
					newDates.add(tempdate);
					HashMap<String, Long> dateTemp = new HashMap<String, Long>();
					dateTemp.put("startDate", startDate);
					dateTemp.put("endDate", endDate);
					dateTemp.put("status", (long)0);
					newDates.add(dateTemp);
				}
				status = false;
			}
			else
			{
				newDates.add(tempdate);
			}
		}
		return newDates;
	}
	
	public ArrayList<HashMap<String,Long>> removeDates(long startDate,long endDate,ArrayList<HashMap<String,Long>> dates)
	{
		int used = 0,index=0;
		for(int i=0;i<dates.size();i++)
		{
			if(dates.get(i).get("startDate") == (startDate))
			{
				index = i;
				if(i != 0)
				{
					if((dates.get(i-1).get("endDate")+1) == startDate && (dates.get(i-1).get("status") == (long)1) )
					{
						dates.get(i-1).put("endDate", endDate);
					}
				
					if((i+1) != dates.size())
					{
						if((dates.get(i+1).get("startDate")-1) == endDate && (dates.get(i+1).get("status") == (long)1))
						{
							dates.get(i-1).put("endDate", dates.get(i+1).get("endDate"));
							used++;
						}
					}
				}
				else
				{
					dates.get(i+1).put("startDate", startDate);
					break;
				}
			}
		}
		dates.remove(index);
		if(used == 1)
			dates.remove(index);
		return dates;
	}
}
