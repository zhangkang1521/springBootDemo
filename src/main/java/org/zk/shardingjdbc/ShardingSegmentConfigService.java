/**
 * 
 */
package org.zk.shardingjdbc;

import com.google.common.collect.Range;

import java.util.List;

/** 
 * @ImplementProject auto-accounting
 * @Description 
 * @author chenlizhao
 * @date 2019年8月21日 下午4:11:20
 */
public interface ShardingSegmentConfigService {
	
	/**
	 * 根据分片id返回表后缀
	 * @param shardingId
	 * @return
	 */
	String getAutoInTableSuffixByShardingId(Long shardingId);

	/**
	 * 根据分片id范围返回表后缀列表
	 * @param shardingIdRange
	 * @return
	 */
	List<String> getAutoInTableSuffixListByShardingIdRange(Range<Long> shardingIdRange);
}
