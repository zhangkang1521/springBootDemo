/**
 * 
 */
package org.zk.shardingjdbc;

import com.google.common.collect.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ShardingSegmentConfigServiceImpl implements ShardingSegmentConfigService {

	private Logger log = LoggerFactory.getLogger(ShardingSegmentConfigServiceImpl.class);

	private List<ShardingSegmentConfig> shardingSegmentConfigList;
	
	private ShardingSegmentConfigServiceImpl() {	
		try {
			init();
		} catch(Exception e) {
			log.error("ShardingSegmentConfigService init error", e);
		}
	}
	
	private static ShardingSegmentConfigServiceImpl instance;
	
	public static ShardingSegmentConfigService getInstance() {
		if(instance == null) {
			instance = new ShardingSegmentConfigServiceImpl();
		}
		
		return instance;
	}
	

	@Override
	public String getAutoInTableSuffixByShardingId(Long shardingId) {
		for(ShardingSegmentConfig segment : shardingSegmentConfigList) {
			if(shardingId >= segment.getStartId() && shardingId < segment.getEndId()) {
				log.debug("shardingId: {}, 表后缀：{}", shardingId, segment.getTableSuffix());
				return segment.getTableSuffix();
			}			
		}
		throw new RuntimeException("分表不足");
	}


	// between调用
	@Override
	public List<String> getAutoInTableSuffixListByShardingIdRange(Range<Long> shardingIdRange) {
		List<String> suffixList = new ArrayList<String>();
		for(ShardingSegmentConfig segment : shardingSegmentConfigList) {
			try {
				Range<Long> range = Range.closedOpen(segment.getStartId(), segment.getEndId()).intersection(shardingIdRange);
				if(!range.isEmpty())
					suffixList.add(segment.getTableSuffix());
			} catch (IllegalArgumentException e) {
				continue;
			}
		}
		log.debug("shardingIdRange:{}, 表后缀：{}", shardingIdRange, suffixList);
		return suffixList;
	}

	private void init() throws Exception {

		shardingSegmentConfigList = new ArrayList<>(2);
		final int STEP = 10;
		// [0, 10)
		// [10, 20)
		for(int i = 0; i < 2; i++) {
			ShardingSegmentConfig config = new ShardingSegmentConfig();
			config.setStartId(Long.valueOf(i * STEP));
			config.setEndId(config.getStartId() + STEP);
			config.setTableSuffix(String.valueOf(i));
			shardingSegmentConfigList.add(config);
		}

	}

}
