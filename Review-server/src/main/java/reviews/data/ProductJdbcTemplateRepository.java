package reviews.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import reviews.data.mappers.ProductMapper;
import reviews.models.Product;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class ProductJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product findByName(String name) {
        final String sql = """
                select product_id, name
                from product
                where name = 'name';
                """;

        return jdbcTemplate.query(sql, new ProductMapper(), name).stream()
                .findFirst().orElse(null);
    }

    public Product add(Product product) {

        final String sql = """
                insert into product (name, description)
                values (?,?);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        product.setId(keyHolder.getKey().intValue());
        return product;
    }

    public boolean update(Product product) {

        final String sql = """
                update product set
                name = ?,
                description = ?
                where product_id = ?;
                """;

        return jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getId()) > 0;
    }

}
