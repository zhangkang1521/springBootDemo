package org.zk.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * sharding-jdbc数据源配置
 *
 * @author zhangkang
 * @date 2022/5/28 17:12
 */
@Component
public class ShardingDsConfig {

    @Autowired
    private Ds0Config ds0Config;

    @Autowired
    private Ds1Config ds1Config;

    @Autowired
    private DatabaseShardingAlgorithm databaseShardingAlgorithm;

    @Autowired
    private TableShardingAlgorithm tableShardingAlgorithm;

    @Bean
    public DataSource getDataSource() throws SQLException {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(ds0Config.getDatabaseName(), ds0Config.createDataSource());
        dataSourceMap.put(ds1Config.getDatabaseName(), ds1Config.createDataSource());

        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, ds0Config.getDatabaseName());

        TableRule tableRule = TableRule.builder("goods")
                .actualTables(Arrays.asList("goods0", "goods1"))
                .dataSourceRule(dataSourceRule)
                .build();

        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(tableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("goods_id", databaseShardingAlgorithm))
                .tableShardingStrategy(new TableShardingStrategy("goods_id", tableShardingAlgorithm))
                .build();

        // 可以修改sharding-jdbc的线程池数量，默认100
        Properties properties = new Properties();
        properties.put("executor.max.size", "4");

        return ShardingDataSourceFactory.createDataSource(shardingRule, properties);
    }

}
