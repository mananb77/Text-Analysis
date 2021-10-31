public class Word2 {
    int frequency = 0;
    String word = "";

    public Word2(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getWord() {
        return word;
    }

}
