package work.hdjava.sample.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.hdjava.sample.user.domain.UUser;
import work.hdjava.sample.user.dto.UserDto;

/**
 * All rights Reserved, Designed By 八方浩达.
 * @author: suncl
 * @date: 2023/6/9 21:13
 * @Copyright ?2022 八方浩达. All rights reserved.
 * 注意：本内容仅限于八方浩达内部传阅，禁止外泄以及用于其他的商业用途。
 */
@Mapper
public interface UUserMapper extends BaseMapper<UUser> {
    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(UUser record);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(UUser record);

    int updateBatch(List<UUser> list);

    int updateBatchSelective(List<UUser> list);

    int batchInsert(@Param("list") List<UUser> list);

    int insertOrUpdate(UUser record);

    int insertOrUpdateSelective(UUser record);

    UserDto queryByAccount(String account);
}