package org.zk.jsonconvert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 任意json转换为任意json
 * @author zhangkang
 * @date 2022/8/18 11:24
 */
public class JsonConvert {

    private ExpressRunner runner = new ExpressRunner();

    private DefaultContext<String, Object> context = new DefaultContext<>();

    public String convert(String source) throws Exception {
        JSONObject jsonObject = JSON.parseObject(source);

        jsonObject.forEach((k, v) -> {
            context.put(k, v);
        });

        ConvertConfig convertConfig = getConvertConfig();
        JSONObject target = new JSONObject();
        processField(convertConfig.getTargetFieldConfigs(), target);
        return target.toJSONString();
    }

    private void processField(List<TargetFieldConfig> targetFieldConfigs, JSONObject target) throws Exception {
        for (TargetFieldConfig targetFieldConfig : targetFieldConfigs) {
            if (StringUtils.isNotBlank(targetFieldConfig.getExpress())) {
                target.put(targetFieldConfig.getField(), runner.execute(targetFieldConfig.getExpress(), context, null, true, false));
            } else {
                JSONObject nestedTarget = new JSONObject();
                target.put(targetFieldConfig.getField(), nestedTarget);
                processField(targetFieldConfig.getTargetFieldConfigs(), nestedTarget);
            }
        }
    }

    private ConvertConfig getConvertConfig() {
        ConvertConfig convertConfig = new ConvertConfig();
        // 业务id
        TargetFieldConfig businessId = new TargetFieldConfig("business_id", "after.trade_id");
        TargetFieldConfig scene = new TargetFieldConfig("scene", "'短信'");
        TargetFieldConfig businessType = new TargetFieldConfig("businessType", "return after.pay_action == 1 ? 'a':'b'");
        TargetFieldConfig comm = new TargetFieldConfig("comm", "");
        comm.setTargetFieldConfigs(Arrays.asList(new TargetFieldConfig("nest_business_id", "after.trade_id + 'xxx'")));
        convertConfig.setTargetFieldConfigs(Arrays.asList(businessId, scene, businessType, comm));
        return convertConfig;
    }
}
