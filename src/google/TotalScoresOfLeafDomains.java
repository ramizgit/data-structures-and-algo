package consistenthashing.google;

import java.util.*;

/*
You are given a list of domain names and an integer score for each of them.

A domain is a leaf if it does not have any child domains in the input.

A leaf domain's total score is the sum of:
its own score, and
the scores of all of its ancestor domains that are present in the input.
Write a program that, given the input list, returns all leaf domains with their respective total scores.

For example, for mail.test.mydomain.com, the ancestor domains are:
test.mydomain.com
mydomain.com
com
Method Signature
List<String> getLeafDomainsWithTotalScores(List<String> domainScores)
domainScores[i] is a string in the format "domain,score"
domain is a dot-separated domain name such as mail.test.com
score is an integer and may be negative, zero, or positive
Return each answer string in the format "leafDomain,totalScore"
Return the answer strings sorted in lexicographical ascending order by leaf domain.
Definitions
A domain a.b.c is a child of domain b.c.

More generally, a domain x is a child of domain y if:
x ends with "." + y
x has at least one additional label before y
A domain is a leaf if no input domain is its child.

The total score of a leaf domain is the sum of the scores of all domains on its path from itself up to the top-most ancestor present in the input.
Input Format
The input is provided as a list of strings.

Each string is in the format:

"domain,score"

Example entries:
"test.mydomain.com,10"
"com,20"
"www.test.com,-5"
Output Format
Return a list of strings.

Each string must be in the format:

"leafDomain,totalScore"

Example output entry:
"www.test.com,5"
Constraints
1 ≤ domainScores.size() ≤ 100,000
Each domain name contains one or more non-empty labels separated by .
Each input string contains exactly one domain and one integer score separated by a comma
-10^6 ≤ score ≤ 10^6
All domain names in the input are unique
You must never use null as a parameter value
Notes
If a parent or ancestor domain is not present in the input, it does not contribute to the total score.

A domain with no child domains in the input is still a leaf even if it has one or more ancestors in the input.
 */

public class TotalScoresOfLeafDomains {

    //https://codezym.com/question/158

    public List<String> getLeafDomainsWithTotalScores(List<String> domainScores)
    {
        // Step 1: Parse input and populate score map
        Map<String, Integer> scoreMap = new HashMap<>();

        for(String domainScore : domainScores){
            String[] entry = domainScore.split(",");
            String domain = entry[0];
            int score = Integer.parseInt(entry[1]);

            scoreMap.put(domain, score);
        }

        // Step 2: Find all non-leaf domains
        Set<String> nonLeaves = new HashSet<>();

        for(String domain : scoreMap.keySet()){
            String parent = getParent(domain);
            if(!parent.isEmpty() && scoreMap.containsKey(parent)){
                nonLeaves.add(parent);
            }
        }

        // Step 3: Compute answer for every leaf
        List<String> result = new ArrayList<>();

        for(String domain : scoreMap.keySet()){
            if(!nonLeaves.contains(domain)){

                int score = scoreMap.get(domain); //add leaf domain score

                //keep on adding parent scores
                String current = getParent(domain);
                while(!current.isEmpty()){
                    score += scoreMap.getOrDefault(current, 0);
                    current = getParent(current);
                }

                result.add(domain + "," + score);
            }
        }

        Collections.sort(result);
        return result;
    }

    private String getParent(String domain)
    {
        int index = domain.indexOf(".");

        if(index != -1){
            return domain.substring(index+1);
        }

        return "";
    }
}
