package org.zk;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author zhangkang
 * @date 2022/7/31 18:50
 */
@Mapper
//@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "age1", target = "age2")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "desc", target = "desc", defaultValue = "default desc")
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserVO toUserVo(User user);
}
