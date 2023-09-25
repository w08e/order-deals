package com.zhouyi.order.domain.aggregate.userdomain.domainManager;

import com.zhouyi.order.domain.aggregate.userdomain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jinyueWang
 * @date 2023/9/25
 */
@Service
public class UserManager {
    private final UserRepository userRepository;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
