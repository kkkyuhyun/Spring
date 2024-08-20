package org.scoula.mapper;
import org.apache.ibatis.annotations.Select;
public interface TimeMapper {
    @Select("SELECT sysdate()")
    //mybatis 가 제공하는 Select annotation
    public String getTime();
    //return 타입 String
    public String getTime2();
}
