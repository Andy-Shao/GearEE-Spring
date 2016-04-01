package wms.domain;

import java.io.Serializable;
import java.util.Objects;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 6880357856522648504L;
    private String id;
    private String nextTime;
    private long step;
    private String updateTime;
    private String wordId;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Schedule) {
            Schedule that = (Schedule) obj;
            return Objects.equals(this.id , that.id) && Objects.equals(this.nextTime , that.nextTime)
                && Objects.equals(this.step , that.step) && Objects.equals(this.updateTime , that.updateTime)
                && Objects.equals(this.wordId , that.wordId);
        } else return false;
    }

    public String getId() {
        return this.id;
    }

    public String getNextTime() {
        return this.nextTime;
    }

    public long getStep() {
        return this.step;
    }

    public String getUpdateTime() {
        return this.updateTime;
    }

    public String getWordId() {
        return this.wordId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id , this.nextTime , this.step , this.updateTime , this.wordId);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public void setStep(long step) {
        this.step = step;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    @Override
    public String toString() {
        return "Schedule [id=" + this.id + ", nextTime=" + this.nextTime + ", step=" + this.step + ", updateTime="
            + this.updateTime + ", wordId=" + this.wordId + "]";
    }
}
