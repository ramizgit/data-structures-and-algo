package strings;

public class StudentAttendanceRecordi {

    //https://leetcode.com/problems/student-attendance-record-i/

    public boolean checkRecord(String s)
    {
        //input validation
        if(s == null || s.isEmpty()){
            return false;
        }

        int absent = 0;
        int late = 0;

        for(char ch : s.toCharArray()){

            if(ch == 'P'){
                late = 0;
            }else if(ch == 'A'){
                absent++;
                late = 0;

                if(absent >= 2){
                    return false;
                }
            }else if (ch == 'L'){
                late++;

                if(late >= 3){
                    return false;
                }
            }
        }

        return true;
    }
}
