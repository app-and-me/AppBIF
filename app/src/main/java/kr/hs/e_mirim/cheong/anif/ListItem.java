package kr.hs.e_mirim.cheong.anif;


/**
 * Created by 수현 on 2018-05-27.
 */

public class ListItem {
    private String text;
    private String name;
    private String date;

    public ListItem(String text, String name, String date) {
        this.text = text;
        this.name = name;
        this.date = date;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
