package org.zk.shardingjdbc;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class ShardingAlgorithm implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {


    private ShardingSegmentConfigService shardingSegmentConfigService;

    public ShardingAlgorithm() {
        shardingSegmentConfigService = ShardingSegmentConfigServiceImpl.getInstance();
    }
	
    /**
     * 精准匹配
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
    	String suffix = shardingSegmentConfigService.getAutoInTableSuffixByShardingId(shardingValue.getValue());
        for (String targetName : availableTargetNames) {
            if (targetName.endsWith(suffix)) {
                return targetName;
            }
        }

        throw new UnsupportedOperationException("PreciseShardingValue: " +shardingValue.getValue() +" 未匹配到分片");
    }

    /**
     * 范围匹配
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames,
                                         RangeShardingValue<Long> shardingValue) {
    	Set<String> result = new LinkedHashSet<>();
        List<String> autoInTableSuffixList = shardingSegmentConfigService
                .getAutoInTableSuffixListByShardingIdRange(shardingValue.getValueRange());

        for (String suffix : autoInTableSuffixList) {
            for (String targetName : availableTargetNames) {
                if (targetName.endsWith(suffix)) {
                    result.add(targetName);
                    break;
                }
            }
        }
        return result;
    }
}
