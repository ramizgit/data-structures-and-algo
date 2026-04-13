package graph.unionfindDSU;

import java.util.*;

public class AccountMerge {

    //https://leetcode.com/problems/accounts-merge/

    /*
    [
         ["John", "a@mail.com", "b@mail.com"],
         ["John", "b@mail.com", "c@mail.com"],
         ["Mary", "x@mail.com"],
         ["John", "y@mail.com"]
    ]
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts)
    {
        //email to id and name mapping
        Map<String, Integer> emailToId = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int id = 1;

        for(List<String> account : accounts){
            String name = account.get(0);

            for(int i=1; i<account.size(); i++){
                String email = account.get(i);

                if(!emailToId.containsKey(email)){
                    emailToId.put(email, id++);
                    emailToName.put(email, name);
                }
            }
        }

        //iterate input and union email ids for the same user
        UnionFind unionFind = new UnionFind(id);
        for(List<String> account : accounts){
            String firstEmail = account.get(1);

            for(int i=2; i<account.size(); i++){
                String email = account.get(i);
                unionFind.union(emailToId.get(firstEmail), emailToId.get(email));
            }
        }

        //group emails
        Map<Integer, List<String>> groupedEmails = new HashMap<>();

        for(String email : emailToId.keySet()){
            int root = unionFind.find(emailToId.get(email));
            groupedEmails.computeIfAbsent(root, key -> new ArrayList<>()).add(email);
        }

        //collect result
        List<List<String>> result = new ArrayList<>();
        for(List<String> emails : groupedEmails.values()){
            Collections.sort(emails); //sort emails

            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);

            result.add(account);
        }

        return result;
    }
}
