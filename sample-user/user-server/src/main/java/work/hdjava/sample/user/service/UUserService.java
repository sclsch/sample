package work.hdjava.sample.user.service;

import java.util.List;
import work.hdjava.sample.user.domain.UUser;
import com.baomidou.mybatisplus.extension.service.IService;
import work.hdjava.sample.user.dto.UserDto;

/**
 * All rights Reserved, Designed By 八方浩达.
 * @author: suncl
 * @date: 2023/6/9 21:13
 * @Copyright ?2022 八方浩达. All rights reserved.
 * 注意：本内容仅限于八方浩达内部传阅，禁止外泄以及用于其他的商业用途。
 */
public interface UUserService extends IService<UUser>{


    int insertSelective(UUser record);

    int updateByPrimaryKeySelective(UUser record);

    int updateBatch(List<UUser> list);

    int updateBatchSelective(List<UUser> list);

    int batchInsert(List<UUser> list);

    int insertOrUpdate(UUser record);

    int insertOrUpdateSelective(UUser record);

    /**
     * 根据账号查询用户
     * @param account 账号名称
     * @return
     */
    UserDto queryByAccount(String account);
}
