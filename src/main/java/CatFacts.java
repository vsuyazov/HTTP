
import com.fasterxml.jackson.annotation.JsonProperty;

public class CatFacts {

    public String id;
    public String text;
    public String type;
    public String name;
    public int upvotes;

    public CatFacts(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String name,
            @JsonProperty("upvotes") int upvotes
    ) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.name = name;
        this.upvotes = upvotes;
    }

    public int getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "id:" + id +
                " text:" + text + '\'' +
                " type:'" + type + '\'' +
                " user:'" + name + '\'' +
                " upvotes:" + upvotes;
    }
}
