class Solution {
    private final int[] daysinmonth={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {

        int startA=days(arriveAlice);
        int endA=days(leaveAlice);
        int startB=days(arriveBob);
        int endB=days(leaveBob);
        
        int latestarrive=Math.max(startA,startB);
        int latestend=Math.min(endA,endB);
        int overlap=(latestend-latestarrive)+1;
        return (overlap<0)?0:overlap;
    }

    private int days(String date){
        int month=Integer.parseInt(date.substring(0,2));
        int day=Integer.parseInt(date.substring(3));
        int totaldays=0;

        for(int i=0;i<month-1;i++) totaldays+=daysinmonth[i];
        return totaldays+day;
    }
}