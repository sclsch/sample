package work.hdjava.sample.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import work.hdjava.sample.common.entity.ResEntity;
import work.hdjava.sample.common.enums.ResEnum;
import work.hdjava.sample.user.UserApi;
import work.hdjava.sample.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserApi userApi;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResEntity<UserDto> userDtoResEntity = userApi.queryByAccount(username);
        if(ResEnum.SUCCESS.getCode().equals(userDtoResEntity.getCode())){
            UserDto data = userDtoResEntity.getData();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("admin"));
            return new User(data.getAccount(), data.getPassword(), authorities);
        }
        return null;
    }
}
