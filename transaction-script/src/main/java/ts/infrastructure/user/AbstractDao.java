package ts.infrastructure.user;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ts.infrastructure.SqlTemplate;

public abstract class AbstractDao<T> {
    
    private JdbcTemplate jdbc;
    private Class<T> tableClass;

    public AbstractDao(JdbcTemplate jdbc, Class<T> tableClass) {
        this.jdbc = jdbc;
        this.tableClass = tableClass;
    }

    protected ImmutableList<T> read(String methodName, Object... args) {
        String sql = new SqlTemplate().getTemplate(this.getClass(), methodName);
        return Lists.immutable.ofAll(this.jdbc.query(sql, new BeanPropertyRowMapper<>(this.tableClass), args));
    }
}
