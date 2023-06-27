package work.hdjava.sample.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.hdjava.sample.common.entity.ResEntity;
import work.hdjava.sample.user.dto.UserDto;

@FeignClient(name = "user-server")
public interface UserApi {
    /**
     * 根据用户名称查询用户
     *
     * @param account
     * @return
     */
    @RequestMapping("/u_user/queryByAccount")
    ResEntity<UserDto> queryByAccount(@RequestParam("account") String account);
}