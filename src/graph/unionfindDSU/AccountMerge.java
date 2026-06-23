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

    /*
    Every email is treated as a node.
    If two emails appear in the same account, they belong to the same person.
    So we connect (union) all emails inside one account.

    Finally:
        every connected component = one merged account
        collect emails belonging to the same component
        sort them
        prepend the owner's name
     */

    public List<List<String>> accountsMerge(List<List<String>> accounts)
    {
        //email to id and name mapping
        Map<String, Integer> emailToId = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int id = 0;

        //assign every email a unique id - O(E) where E = total number of emails
        for(List<String> account : accounts){

            String name = account.getFirst();

            for(int i=1; i<account.size(); i++){
                String email = account.get(i);
                if(!emailToId.containsKey(email)){
                    emailToId.put(email, id++);
                    emailToName.put(email, name);
                }
            }
        }

        //union emails inside each account - O(E) as every email participates once.
        graph.unionfindDSU.UnionFind uf = new graph.unionfindDSU.UnionFind(id);

        for(List<String> account : accounts){

            String firstEmail = account.get(1);
            int firstEmailId = emailToId.get(firstEmail);

            for(int i=2; i<account.size(); i++){
                String email = account.get(i);

                //union by id
                uf.union(firstEmailId, emailToId.get(email));
            }
        }

        //group emails - O(E)
        Map<Integer, List<String>> groupedEmails = new HashMap<>();

        for(String email : emailToId.keySet()){
            int root = uf.find(emailToId.get(email));
            groupedEmails.computeIfAbsent(root, key -> new ArrayList<>()).add(email);
        }

        //collect result
        List<List<String>> result = new ArrayList<>();

        for(List<String> emails : groupedEmails.values()){

            Collections.sort(emails); //sort emails - O(E log E)

            String name = emailToName.get(emails.getFirst());

            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);

            result.add(account);
        }

        return result;
    }
}
