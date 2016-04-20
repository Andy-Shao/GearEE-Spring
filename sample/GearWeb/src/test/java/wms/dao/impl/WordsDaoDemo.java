package wms.dao.impl;

import java.util.List;

import wms.dao.WordsDao;
import wms.domain.Words;

public class WordsDaoDemo implements WordsDao{

    @Override
    public void add(Words words) {
    }

    @Override
    public Words find(String wordName) {
        return null;
    }

    @Override
    public long findTimeBigNum(String nextTime) {
        return 0;
    }

    @Override
    public long findTimeLesNum(String nextTime) {
        return 0;
    }

    @Override
    public List<Words> findTimeLessThan(String nextTime , long size) {
        return null;
    }

    @Override
    public void remove(Words words) {
    }

    @Override
    public long totally() {
        return 0;
    }

    @Override
    public void update(Words words) {
    }
}
