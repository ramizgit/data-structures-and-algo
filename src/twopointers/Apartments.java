package twopointers;

import java.util.*;

/*
There are n applicants and m free apartments. Your task is to distribute the apartments so that as many applicants as possible will get an apartment.
Each applicant has a desired apartment size, and they will accept any apartment whose size is close enough to the desired size.
Input
The first input line has three integers n, m, and k: the number of applicants, the number of apartments, and the maximum allowed difference.
The next line contains n integers a_1, a_2, \ldots, a_n: the desired apartment size of each applicant. If the desired size of an applicant is x, they will accept any apartment whose size is between x-k and x+k.
The last line contains m integers b_1, b_2, \ldots, b_m: the size of each apartment.
Output
Print one integer: the number of applicants who will get an apartment.
Constraints

1 \le n, m \le 2 \cdot 10^5
0 \le k \le 10^9
1 \le a_i, b_i \le 10^9

Example
Input:
4 3 5
60 45 80 60
30 60 75

Output:
2
 */

public class Apartments {

    //https://cses.fi/problemset/task/1084

    /*
    Time Complexity
        Sorting: O(n log n + m log m)
        Two pointers: O(n + m)
    Overall: O(n log n + m log m)
    Space: O(1)
     */
    public int findMatch(int n, int m, int k, int[] applicants, int[] apartments)
    {
        //n, m, and k -> the number of applicants, the number of apartments, and the maximum allowed difference

        //sort both arrays
        Arrays.sort(applicants);
        Arrays.sort(apartments);

        int i = 0; //pointer on applicants
        int j = 0; //pointer on apartments
        int matches = 0;

        while(i < n && j < m){

            if(apartments[j] < (applicants[i] - k)){
                //case 1 : apartment too small
                j++; //discard this apartment; it's too small for the current (and all future) applicants.
            }else if(apartments[j] > (applicants[i] + k)){
                //case 2 : apartment too large
                i++; //current applicant cannot be matched; try the next applicant.
            }else{
                //case 3 : match
                i++;
                j++;

                matches++;
            }
        }

        return matches;
    }
}

