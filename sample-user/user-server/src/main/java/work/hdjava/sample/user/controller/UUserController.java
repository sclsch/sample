package work.hdjava.sample.user.controller;
import work.hdjava.sample.user.domain.UUser;
import org.springframework.web.bind.annotation.*;
import work.hdjava.sample.user.service.UUserService;

import javax.annotation.Resource;

/**
* (u_user)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/u_user")
public class UUserController {
    /**
    * 服务对象
    */
    @Resource
    private UUserService uUserService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("selectOne")
    public UUser selectOne(Integer id) {
      return uUserService.getById(id);
    }

}
