package org.zk.doc;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @author zhangkang
 * @date 2023/3/12 17:11
 */
@Data
@Document(indexName = "user", type = "_doc")
public class UserDoc {

    private Long userId;

    private String username;

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
