package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.UserLogin;
import generator.service.UserLoginService;
import generator.mapper.UserLoginMapper;
import org.springframework.stereotype.Service;

/**
* @author 小K
* @description 针对表【user_login(用户登录表)】的数据库操作Service实现
* @createDate 2024-03-03 20:38:27
*/
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin>
    implements UserLoginService{

}




