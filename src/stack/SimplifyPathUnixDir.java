package stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPathUnixDir {

    /*
    Key Rules
    "." → current directory → ignore it.
    ".." → go up one level → pop from stack if not empty.
    Empty parts or multiple / → ignore.
    Always start with / in the result.
    No trailing slash at the end (unless root /).
     */

    public static void main(String[] args)
    {
        System.out.println(simplifyPath("/home/"));
        System.out.println(simplifyPath("/home//foo/"));
        System.out.println(simplifyPath("/home/user/Documents/../Pictures"));
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/.../a/../b/c/../d/./"));
    }

    private static String simplifyPath(String path)
    {
        Deque<String> stack = new ArrayDeque<>();

        String[] dirs = path.split("/");

        for(String dir : dirs){
            if(dir.isEmpty() || dir.equals(".")){
                //ignore
            }else if(dir.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(dir);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.insert(0, "/" + stack.pop());
        }

        return sb.isEmpty() ? "/" : sb.toString();
    }
}
