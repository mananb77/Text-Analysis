public class DocInfo {
    String textName = "";
    double fleschScore = 0.0;
    double kincaidScore = 0.0;


    public DocInfo( String textName, double fleschScore, double kincaidScore ) {
        this.textName = textName;
        this.fleschScore = fleschScore;
        this.kincaidScore = kincaidScore;
    }

    public String getTextName() {
        return this.textName;
    }

    public void setTextName( String newTextName ) {
        this.textName = newTextName;
    }

    public double getFleschScore() {
        return this.fleschScore;
    }

    public double getKincaidScore() {
        return this.kincaidScore;
    }
}
