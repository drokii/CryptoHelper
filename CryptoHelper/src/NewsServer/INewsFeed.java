package NewsServer;

import java.util.List;

public interface INewsFeed {

    List<NewsPiece> getNews();
    void refreshNewsList();
}
