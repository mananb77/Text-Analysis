import javax.print.Doc;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TextLib {

    public static String readFileAsString(String filename) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output.append(line.trim()+"\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
    }

    public static ArrayList<String> splitIntoSentences(String text) {
        ArrayList<String> output = new ArrayList<>();

        Locale locale = Locale.US;
        BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
        breakIterator.setText(text);

        int prevIndex = 0;
        int boundaryIndex = breakIterator.first();
        while(boundaryIndex != BreakIterator.DONE) {
            String sentence = text.substring(prevIndex, boundaryIndex).trim();
            if (sentence.length()>0)
                output.add(sentence);
            prevIndex = boundaryIndex;
            boundaryIndex = breakIterator.next();
        }

        String sentence = text.substring(prevIndex).trim();
        if (sentence.length()>0)
            output.add(sentence);

        return output;
    }

    public static ArrayList<DocInfo> readAllTextsFile(String filename) {
        Scanner scanner;
        ArrayList<DocInfo> docInfos = new ArrayList<>();

        try {
            scanner = new Scanner(new FileReader(filename));
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                DocInfo d = getDocInfo( line );
                docInfos.add(d);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return docInfos;
    }

    private static DocInfo getDocInfo(String line) {
        String[] data = line.split(",");

        String textName = data[0].trim();
        textName = adjust(textName);

        double fleschScore = Double.parseDouble(data[91].trim());
        double kincaidScore = Double.parseDouble(data[92].trim());

        return new DocInfo( textName, fleschScore, kincaidScore );
    }

    private static String adjust(String name) {
        if (name == null) {
            return "";
        }

        if ( name.contains("WNL India's rich") || name.contains("What's the secret") || name.contains("What is world's")) {
            return name;
        } else if (name.equals("WNL-Use-of-e-cig-adv.txt")) {
            return "WNL Use of e-cig-adv.txt";
        } else if (name.equals("WNL-Use-of-e-cig-ele.txt")) {
            return "WNL Use of e-cig-ele.txt";
        } else if (name.equals("WNL-Use-of-e-cig-int.txt")) {
            return "WNL Use of e-cig-int.txt";
        }

        return name.substring( 0, name.length() - 8).replace("-", " ") + name.substring(name.length() - 8);
        // if there are any dashes in the name other than the last dash, replace it with a space

    }

    public static ArrayList<Word> readSyllablesFile(String filename) {
        Scanner scanner;
        ArrayList<Word> words = new ArrayList<Word>();

        try {
            scanner = new Scanner(new FileReader(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // ------- process the line here -------
                String word = getWordFromLine(line);
                int syllables = getSyllablesFromLine(line);

                // -------------------------------------
                Word w = new Word(word, syllables);
                words.add(w);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return words;
    }

    private static int getSyllablesFromLine(String line) {
        int start = line.indexOf("=") + 1;
        int count = 0;

        for (; start < line.length(); start++) {
            if (line.substring(start, start+1).equals("*")) count++;
        }

        return count+1;
    }

    private static String getWordFromLine(String line) {
        return line.substring(0, line.indexOf("="));
    }

}
