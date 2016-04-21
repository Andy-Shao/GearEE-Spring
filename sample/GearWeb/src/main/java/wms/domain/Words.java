package wms.domain;

import java.io.Serializable;
import java.util.Objects;

public class Words implements Serializable {
    private static final long serialVersionUID = 2143355527606585332L;
    private String id;
    private String insertTime;
    private String wordName;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Words) {
            Words that = (Words) obj;
            return Objects.equals(this.id , that.id) && Objects.equals(this.insertTime , that.insertTime) && Objects.equals(this.wordName , that.wordName);
        } else return false;
    }

    public String getId() {
        return this.id;
    }

    public String getInsertTime() {
        return this.insertTime;
    }

    public String getWordName() {
        return this.wordName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id , this.insertTime , this.wordName);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    @Override
    public String toString() {
        return "Words [id=" + this.id + ", insertTime=" + this.insertTime + ", wordName=" + this.wordName + "]";
    }
}
