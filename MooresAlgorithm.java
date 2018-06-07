import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class MooresAlgorithm {
	
	private TreeMap<Integer, Integer> map=new TreeMap<>();
	public MooresAlgorithm(int[] duedates, int[] processingtimes){
		int n=duedates.length;
		for(int i=0; i<n;i++) {
			map.put(duedates[i], processingtimes[i]);
		}
	}
	
	private void printMap() {
	    Set s = map.entrySet();
	    Iterator it = s.iterator();
	    while ( it.hasNext() ) {
	       Map.Entry entry = (Map.Entry) it.next();
	       int key = (int) entry.getKey();
	       int value = (int) entry.getValue();
	       System.out.println("{" + key + "," + value + "}");
	    }//while
	}//p
	
	private HashMap MooresAlgorithm() {
		// Initialize an empty final set S
		int jobid=0;
		HashMap<Integer, Integer> S = new HashMap<>();
		int numTasks=map.size();
		Iterator it=map.entrySet().iterator();
		while(it.hasNext()) {
			
			Map.Entry entry=(Map.Entry) it.next();
			// Get the processing time
			int processingtime=(int) entry.getValue();
			int duedate=(int) entry.getKey();
			int tmp=sumProcessingTimesIn(S);
			if(tmp+processingtime <= duedate){
				S.put((Integer) entry.getKey(), (Integer) entry.getValue());
			}
			else {
				S.put((Integer) entry.getKey(), (Integer) entry.getValue());
				Entry longestTask=longestTask(S);
				// Remove the longest task
				S.remove(longestTask.getKey(), longestTask.getValue());
			}
			jobid++;
		}
		return S;
	}
	
	private Entry longestTask(Map<Integer, Integer> S) {

		Iterator it=S.entrySet().iterator();
		Map.Entry longestTask = null;
		int max=0;
		while(it.hasNext()) {
			
			Map.Entry entry=(Map.Entry) it.next();
			// Get the processing time
			int processingtime=(int) entry.getValue();
			int duedate=(int) entry.getKey();
			if(processingtime>max) {
				max=processingtime;
				longestTask=entry;
			}
		}
		return longestTask;
	}

	private int sumProcessingTimesIn(Map<Integer, Integer> S) {
		if(S.isEmpty())
			return 0;
		int sum=0;
		
		Iterator it=S.entrySet().iterator();
		while(it.hasNext()) {
			
			Map.Entry entry=(Map.Entry) it.next();
			// Get the processing time
			int processingtime=(int) entry.getValue();
			int duedate=(int) entry.getKey();
			sum+=processingtime;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		
		// {11,6}, {9,5}, {8,2}, {7,3}, {6,4} are the {duedate_of_task_i, processing_time_of_task_i}
		int[] duedates = {11,9,8,7,6};
		int[] processingtimes= {6,5,2,3,4};
		// We use a treemap to hold the above pairs according in ascending order of due date
		MooresAlgorithm alg=new MooresAlgorithm(duedates, processingtimes);
		HashMap optimalSetOfTasks=alg.MooresAlgorithm();
		System.out.println("The {due_date,processing_time} of a task are as below (in ascending order of due dates): ");
		alg.printMap();
		// Print the set of tasks
		System.out.println("The optimal set of tasks that will minimize the number of late jobs: ");
		Iterator it=optimalSetOfTasks.entrySet().iterator();
		while(it.hasNext()) {
			Entry entry=(Entry) it.next();
			System.out.println("{" + entry.getKey() + "," + entry.getValue()+ "}");
		}
		
	}

}
