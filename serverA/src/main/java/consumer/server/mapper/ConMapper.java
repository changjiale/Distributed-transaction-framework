package consumer.server.mapper;

import consumer.server.model.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConMapper {
    @Insert("insert into test(name) values(#{name})")
    public Integer insert(@Param("name") String name);

}
