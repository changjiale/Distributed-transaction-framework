package server.mapper;

import org.apache.ibatis.annotations.Mapper;
import server.model.Test;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface ProMapper extends BaseMapper<Test> {
}
