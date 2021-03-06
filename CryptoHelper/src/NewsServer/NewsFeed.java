package NewsServer;

import Shared.INewsFeed;
import Shared.NewsPiece;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class NewsFeed implements INewsFeed {

    final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String pubDate;

    final List<NewsPiece> entries = new ArrayList<NewsPiece>();

    public NewsFeed(String title, String link, String description, String language,
                String copyright, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
    }


    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }

    @Override
    public List<NewsPiece> getNews() {
        return entries;
    }

    @Override
    public void refreshNewsList() {
        //TODO: Refresh news list
    }
}
