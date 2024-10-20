package tools;

public class TextPair implements Comparable<TextPair> {
    private String name;
    private String value;
    public TextPair(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }
    @Override
    public int compareTo(TextPair other) {
        return name.compareTo(other.name);
    }

    public String toString() {
        return name + ":" + value;
    }
}
