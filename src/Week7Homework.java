public class Week7Homework {
    public static void main(String[] args) {
        System.out.println( getNthValue("a, 2, b, 3, c, 4", ", ", 0) ); // return a
        System.out.println( getNthValue("a, 2, b, 3, c, 4", ", ", 4) ); // return c
        System.out.println( getNthValue("a, 2, b, 3, c, 4", ", ", 6) ); // return empty string
        System.out.println( getNthValue("words/separated/by/slashes", "/", 2)); // return by
        System.out.println( getNthValue("Hello what's up there", " ", 2));  // return up


        System.out.println( removeNth("abc", "s", 0) );  // return abc
        System.out.println( removeNth("abc", "a", 0) );  // return bc
        System.out.println( removeNth("abc", "a", 1) );  // return abc
        System.out.println( removeNth("abca", "a", 1) ); // return abc
        System.out.println( removeNth("bobbobhellobob", "ob", 0) );  // return bbobhellobob
        System.out.println( removeNth("bobbobhellobob", "ob", 1) );  // return bobbhellobob

    }

    public static String getNthValue(String line, String separator, int n) {
        int start = 0; int end = 0;
        while ( n >= 0 ) {
            start = end;
            end = line.indexOf(separator, end);

            if (end == -1 && n > 0) return "";
            if (n > 0) end++;

            n--;
        }

        return line.substring(start, end).trim();
    }

    public static String removeNth(String line, String target, int n) {
        int start = initializeStart(line, target, n);
        if (start == -1) return line;

        int end = start + target.length();

        return line.substring(0, start) + line.substring(end);
    }

    public static int initializeStart(String line, String target, int n) {
        int start = 0;
        while ( n >= 0 ) {
            start = line.indexOf(target, start);
            if (start == -1) {
                return -1;
            }
            if (n > 0) start++;
            n--;
        }

        return start;
    }
}
