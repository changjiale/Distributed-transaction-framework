package consumer.server.mapper;

import consumer.server.model.Test;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface ConMapper extends BaseMapper<Test> {
}
