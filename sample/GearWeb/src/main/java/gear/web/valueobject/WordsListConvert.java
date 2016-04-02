package gear.web.valueobject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.andyshaox.jdbc.JdbcReturnConvert;
import com.github.andyshaox.jdbc.ObjectReturnConvert;

import wms.domain.Words;

public class WordsListConvert implements JdbcReturnConvert<List<Words>> {
    private static ObjectReturnConvert convert = new ObjectReturnConvert();

    static {
        WordsListConvert.convert.setReturnType(Words.class);
    }

    @Override
    public List<Words> convert(ResultSet in) throws SQLException {
        List<Words> result = new ArrayList<>();
        do
            result.add((Words) WordsListConvert.convert.convert(in));
        while (in.next());
        return result;
    }

}
