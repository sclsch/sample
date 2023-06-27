package work.hdjava.sample.user.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import work.hdjava.sample.user.dto.UserDto;
import work.hdjava.sample.user.mapper.UUserMapper;
import work.hdjava.sample.user.domain.UUser;
import work.hdjava.sample.user.service.UUserService;
/**
 * All rights Reserved, Designed By 八方浩达.
 * @author: suncl
 * @date: 2023/6/9 21:13
 * @Copyright ?2022 八方浩达. All rights reserved.
 * 注意：本内容仅限于八方浩达内部传阅，禁止外泄以及用于其他的商业用途。
 */
@Service
public class UUserServiceImpl extends ServiceImpl<UUserMapper, UUser> implements UUserService{

    @Override
    public int insertSelective(UUser record) {
        return baseMapper.insertSelective(record);
    }
    @Override
    public int updateByPrimaryKeySelective(UUser record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public int updateBatch(List<UUser> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<UUser> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<UUser> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(UUser record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(UUser record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public UserDto queryByAccount(String account) {
        return  this.baseMapper.queryByAccount(account);
    }
}
