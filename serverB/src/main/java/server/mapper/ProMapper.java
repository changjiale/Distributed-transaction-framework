package server.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProMapper {
    @Insert("insert into test(name) values(#{name})")
    public void insert(@Param("name") String name);
}
