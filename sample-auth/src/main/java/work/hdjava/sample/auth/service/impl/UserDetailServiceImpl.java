package work.hdjava.sample.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import work.hdjava.sample.common.entity.ResEntity;
import work.hdjava.sample.common.enums.ResEnum;
import work.hdjava.sample.user.UserApi;
import work.hdjava.sample.user.dto.UserDto;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserApi userApi;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResEntity<UserDto> userDtoResEntity = userApi.queryByAccount(username);
        if(ResEnum.SUCCESS.getCode().equals(userDtoResEntity.getCode())){
            UserDto data = userDtoResEntity.getData();
            UserDetails userDetails = User.withDefaultPasswordEncoder()
                    .username(data.getAccount())
                    .password(data.getPassword())
                    .roles("USER")
                    .build();
            return userDetails;
        }
        return null;
    }
}
