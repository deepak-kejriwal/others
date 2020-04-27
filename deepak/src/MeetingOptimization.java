import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MeetingOptimization {

	public static void main(String[] args) {
		int[][] flights=new int[5][5];
		Map<Integer,List<int[]>> map=Arrays.stream(flights).collect(Collectors.groupingBy(x->x[0]));
		MeetingOptimization mo=new MeetingOptimization();
		mo.test1();

	}

	private void test1() {
		Meeting[] meetings=new Meeting[5];
		meetings[0]=new Meeting("1",4);
		meetings[1]=new Meeting("2",6);
		meetings[2]=new Meeting("3",3);
		meetings[3]=new Meeting("3",1);
		meetings[4]=new Meeting("3",2);
		
		
		List<Meeting> meetingList=optimizeMeetings(meetings,6);
		System.out.println(meetingList);
	}

	private List<Meeting> optimizeMeetings(Meeting[] meetings, int totalHour) {
		int[] max= {0};
		List<Meeting>[][] dp=new ArrayList[meetings.length][totalHour+1];
		List<Meeting> res= helper(0,meetings,totalHour,max,dp);
		return res;
	}

	private List<Meeting> helper(int index,Meeting[] meetings, int totalHour,int[] max,List<Meeting>[][] dp) {
		if(index>meetings.length-1) return new ArrayList<>();
		if(dp[index][totalHour]!=null) {
			System.out.println("");
			return new ArrayList<>(dp[index][totalHour]);
		}
		for(int i=index;i<meetings.length;++i) {
			if(meetings[i].hour<=totalHour) {
				int[] x=new int[] {max[0]};
				List<Meeting> meetingsWithOutI=helper(i+1,meetings,totalHour,x,dp);
				int[] y=new int[] {max[0]};
				List<Meeting> meetingsWithI=helper(i+1,meetings,totalHour-meetings[i].hour,y,dp);
				meetingsWithI.add(meetings[i]);
				y[0]+=meetings[i].hour;
				if(x[0]>y[0]) {
					max[0]=x[0];
					dp[index][totalHour]=new ArrayList<>(meetingsWithOutI);
					return meetingsWithOutI;
				}else if(x[0]<y[0]) {
					max[0]=y[0];
					dp[index][totalHour]=new ArrayList<>(meetingsWithI);
					return meetingsWithI;
				}else if(meetingsWithI.size()>meetingsWithOutI.size()){
					max[0]=y[0];
					dp[index][totalHour]=new ArrayList<>(meetingsWithI);
				}else {
					max[0]=x[0];
					dp[index][totalHour]=new ArrayList<>(meetingsWithOutI);
					return meetingsWithOutI;
				}
			}
		}
		return new ArrayList<>();
	}

	class Meeting{
		String name;
		int hour;

		Meeting(String name, int hour) {
			this.name = name;
			this.hour = hour;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Meeting [name=");
			builder.append(name);
			builder.append(", hour=");
			builder.append(hour);
			builder.append("]");
			return builder.toString();
		}
		
		
	}
}
