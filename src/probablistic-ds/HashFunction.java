public class HashFunction {

    public static int hash1(int num){
        return (num * 30) % 10;
    }

    public static int hash2(int num){
        return (num * 31+1) % 10;
    }

    public static int hash3(int num){
        return (num * 32+2) % 10;
    }

    public static int hash1(String input){
        return (hash(input) * 30) % 10;
    }

    public static int hash2(String input){
        return (hash(input) * 31 + 1) % 10;
    }

    public static int hash3(String input){
        return (hash(input) * 32 + 2) % 10;
    }

    public static int hash(String input)
    {
        int h = 0;
        for(char ch : input.toCharArray()){
            h += ch;
        }
        return h;
    }
}
