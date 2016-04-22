package wms.dao;

import java.util.List;

import wms.domain.Words;

public interface WordsDao {
    void add(Words words);

    Words find(String wordName);

    List<Words> findInfoByLess(String nextTime , long start , long length);

    long findTimeBigNum(String nextTime);

    long findTimeLesNum(String nextTime);

    default List<Words> findTimeLessThan(String nextTime , long size){
        return this.findInfoByLess(nextTime , 0 , size);
    }

    void remove(String wordsId);

    default void remove(Words words) {
        this.remove(words.getId());
    }

    long totally();

    void update(Words words);
}
