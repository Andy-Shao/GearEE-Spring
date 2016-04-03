package wms.dao;

import wms.domain.Words;

public interface WordsDao {
    //    void add(Words words);
    //
    //    long findTimeBigNum(String nextTime);
    //
    //    long findTimeLesNum(String nextTime);
    //
    //    List<Words> findTimeLessThan(String nextTime , long size);
    //
    //    void remove(Words words);
    //
    //    long totally();
    //
    //    void update(Words words);

    Words find(String wordName);
}
