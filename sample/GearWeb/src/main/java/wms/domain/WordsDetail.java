package wms.domain;

import java.io.Serializable;
import java.util.Objects;

public class WordsDetail implements Serializable {
    private static final long serialVersionUID = 8414955419418514940L;
    private String explaining;
    private String mnemonic;
    private String wordId;
    private String wordType;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WordsDetail) {
            WordsDetail that = (WordsDetail) obj;
            return Objects.equals(this.wordId , that.wordId) && Objects.equals(this.wordType , that.wordType)
                && Objects.equals(this.explaining , that.explaining) && Objects.equals(this.mnemonic , that.mnemonic);
        } else return false;
    }

    public String getExplaining() {
        return this.explaining;
    }

    public String getMnemonic() {
        return this.mnemonic;
    }

    public String getWordId() {
        return this.wordId;
    }

    public String getWordType() {
        return this.wordType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.wordId , this.wordType , this.explaining , this.mnemonic);
    }

    public void setExplaining(String explaining) {
        this.explaining = explaining;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    @Override
    public String toString() {
        return "WordsDetail [wordId=" + this.wordId + ", wordType=" + this.wordType + ", explaining=" + this.explaining
            + ", mnemonic=" + this.mnemonic + "]";
    }
}
