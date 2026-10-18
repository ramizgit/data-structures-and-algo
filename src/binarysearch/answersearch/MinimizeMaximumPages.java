package binarysearch.answersearch;

/*
Problem: Minimize Maximum Pages

You are given:

an array pages[]
where pages[i] = number of pages in the i-th book
an integer students

Allocate books to students such that:

Every student gets at least one book
Books are allocated contiguously
A book cannot be split
Minimize the maximum pages assigned to any student

Return the minimized maximum pages.

If allocation is impossible, return -1.

Example 1
Input:
pages = [12,34,67,90]
students = 2

Output: 113
[12,34,67] = 113
[90] = 90
 */

public class MinimizeMaximumPages {

    /*
    Time Complexity: O(n + n log(sum-max)) ~ O(n log(sum-max))
    Space Complexity: O(1)
    */
    public int allocateBooks(int[] pages, int students)
    {
        //input validation - edge case
        if(students > pages.length){
            return -1;
        }

        //binary search
        int max = Integer.MIN_VALUE;
        int total = 0;

        for(int page : pages){ //O(n)
            max = Math.max(max, page);
            total += page;
        }

        /*
        Lower bound is max because:
            a student must take entire books
            no book can be split
            therefore answer can NEVER be smaller than the largest single book
         */
        int low = max; //min pages that can be allocated to any student
        int high = total; //max pages that can be allocated to any student
        int answer = -1;

        while(low <= high){ //O(log(total - max))
            int mid = low + (high - low)/2;

            if(canAllocate(pages, students, mid)){ //O(n)
                answer = mid; //possible answer
                high = mid - 1; //try lower to minimize max page
            }else{
                low = mid + 1; //cant allocate, go higher
            }

        }

        return answer;
    }

    private boolean canAllocate(int[] pages, int students, int maxAllocation)
    {
        int total = 0;
        int count = 1; //start allocating books to the first student

        for(int page : pages){
            total += page;

            if(total > maxAllocation){ //overflow condition
                //current student capacity exceeded, allocate to next student
                count++;
                total = page;
            }
        }

        /*
        why count <= students?
        reason : If we can allocate using FEWER students, then we can ALWAYS split further to use exactly all students without breaching the maxAllocation threshold
         */
        return count <= students;
    }
}

/*
Example: pages = [10, 20, 30], students = 2, maxAllocation = 20.
- Student 1 gets [10] → 10 pages.
- Student 2 gets [20] → 20 pages.
- The book with 30 pages exceeds the limit, so it needs another student.
Greedy allocation results in count = 3.
Since 3 > students (2), count <= students is false.
We cannot allocate all books to 2 students without someone exceeding 20 pages, so the threshold is too small. Increase low.
 */