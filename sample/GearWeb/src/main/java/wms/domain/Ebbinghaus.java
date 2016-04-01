package wms.domain;

import java.io.Serializable;
import java.util.Objects;

public class Ebbinghaus implements Serializable {
    public static final int EBBINGHAUS_MAX = 8;
    public static final int EBBINGHAUS_MIN = 1;
    private static final long serialVersionUID = 6336310149831105489L;
    private long id;
    private long times;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ebbinghaus) {
            Ebbinghaus that = (Ebbinghaus) obj;
            return Objects.equals(this.id , that.times) && Objects.equals(this.times , that.times);
        } else return false;
    }

    public long getId() {
        return this.id;
    }

    public long getTimes() {
        return this.times;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id , this.times);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "Ebbinghaus [id=" + this.id + ", times=" + this.times + "]";
    }
}
