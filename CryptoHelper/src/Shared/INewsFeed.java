package Shared;

import Shared.NewsPiece;

import java.util.List;

public interface INewsFeed {

    List<NewsPiece> getNews();
    void refreshNewsList();
}
