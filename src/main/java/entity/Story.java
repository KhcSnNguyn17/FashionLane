package entity;

public class Story {
    int story_ID;
    String thumbnail;
    String title;
    String description;

    public Story() {
    }

    public Story(int story_ID, String thumbnail, String title, String description) {
        this.story_ID = story_ID;
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
    }

    public int getStory_ID() {
        return story_ID;
    }

    public void setStory_ID(int story_ID) {
        this.story_ID = story_ID;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
