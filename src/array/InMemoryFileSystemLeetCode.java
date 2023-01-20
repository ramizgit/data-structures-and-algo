package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryFileSystemLeetCode {

    public static void main(String[] args)
    {
        FileSystem fs = new FileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        fs.mkdir("/a1/b1/c1");
        System.out.println(fs.ls("/"));
        System.out.println(fs.ls("/a"));
        System.out.println(fs.ls("/a/b"));
        System.out.println(fs.ls("/a/b/c"));
        fs.mkdir("/");

        fs.addContent("/a/b/c", "content of file c");
        System.out.println(fs.getContent("/a/b/c"));
        fs.addContent("/a/b/c", "===adding more content to file c");
        System.out.println(fs.getContent("/a/b/c"));

        fs.addContent("/a/b/c1", "content of file c1");
        System.out.println(fs.getContent("/a/b/c1"));
        System.out.println(fs.ls("/a/b"));
    }
}

class FileSystem{

    private Map<String, List<String>> dirs;
    private Map<String, String> files;

    public FileSystem() {
        dirs = new HashMap<>();
        files = new HashMap<>();

        dirs.put("/", new ArrayList<>());
    }

    public void mkdir(String path){

        if(dirs.containsKey(path)){
            System.out.println("directory already exists : "+path);
        }

        String dir = "/";
        for(String d : path.split("/")){
            dir += d;
            if(!dirs.containsKey(dir)){
                dirs.put(dir, new ArrayList<>());

                String prev = dir.substring(0, dir.lastIndexOf('/')).equals("") ? "/" : dir.substring(0, dir.lastIndexOf('/'));
                List<String> list = dirs.get(prev);
                list.add(d);
                dirs.put(prev, list);
            }
            if(!dir.equals("/")){
                dir += "/";
            }
        }
    }

    public List<String> ls(String path){
        return this.dirs.get(path);
    }

    public void addContent(String file, String content){
        if(!dirs.containsKey(file)){
            this.mkdir(file);
        }

        String existingContent = this.files.getOrDefault(file, "");
        String newcontent = existingContent.concat(content);
        this.files.put(file, newcontent);
    }

    public String getContent(String file){
        return this.files.get(file);
    }
}
