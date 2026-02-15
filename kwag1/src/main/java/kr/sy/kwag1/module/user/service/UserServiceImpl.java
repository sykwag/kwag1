package kr.sy.kwag1.module.user.service;

import kr.sy.kwag1.core.context.TenantContextHolder;
import kr.sy.kwag1.module.user.mapper.UserMapper;
import kr.sy.kwag1.module.user.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 사용자 관리 서비스 구현
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserVO> getUserList() {
        String tenantId = TenantContextHolder.getCurrentTenantId();

        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", tenantId);

        logger.debug("사용자 목록 조회: 테넌트 {}", tenantId);

        return userMapper.selectUserList(params);
    }

    @Override
    public UserVO getUser(Long userId) {
        String tenantId = TenantContextHolder.getCurrentTenantId();

        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", tenantId);
        params.put("userId", userId);

        logger.debug("사용자 상세 조회: ID {}", userId);

        UserVO user = userMapper.selectUser(params);

        if (user == null) {
            logger.warn("사용자를 찾을 수 없습니다: {}", userId);
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        return user;
    }

    @Override
    @Transactional
    public void createUser(UserVO userVO) {
        String tenantId = TenantContextHolder.getCurrentTenantId();

        if (userVO == null) {
            throw new IllegalArgumentException("사용자 정보가 제공되지 않았습니다.");
        }

        userVO.setTenantId(tenantId);
        userVO.setRegUser("SYSTEM");
        userVO.setRegDate(new Date());

        // 비밀번호 암호화
        if (userVO.getUserPassword() != null && !userVO.getUserPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(userVO.getUserPassword());
            userVO.setUserPassword(encodedPassword);
        }

        // 기본값 설정
        if (userVO.getUserRole() == null) {
            userVO.setUserRole("USER");
        }
        if (userVO.getUserStatus() == null) {
            userVO.setUserStatus("ACTIVE");
        }

        int result = userMapper.insertUser(userVO);

        if (result > 0) {
            logger.info("사용자 등록 완료: {} ({})", userVO.getUserLoginId(), userVO.getUserName());
        } else {
            logger.error("사용자 등록 실패: {}", userVO.getUserLoginId());
            throw new RuntimeException("사용자 등록에 실패했습니다.");
        }
    }

    @Override
    @Transactional
    public void updateUser(UserVO userVO) {
        String tenantId = TenantContextHolder.getCurrentTenantId();

        if (userVO == null) {
            throw new IllegalArgumentException("사용자 정보가 제공되지 않았습니다.");
        }

        userVO.setTenantId(tenantId);
        userVO.setUpdUser("SYSTEM");
        userVO.setUpdDate(new Date());

        int result = userMapper.updateUser(userVO);

        if (result > 0) {
            logger.info("사용자 수정 완료: {}", userVO.getUserLoginId());
        } else {
            logger.error("사용자 수정 실패: {}", userVO.getUserLoginId());
            throw new RuntimeException("사용자 수정에 실패했습니다.");
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        String tenantId = TenantContextHolder.getCurrentTenantId();

        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", tenantId);
        params.put("userId", userId);

        int result = userMapper.deleteUser(params);

        if (result > 0) {
            logger.info("사용자 삭제 완료: ID {}", userId);
        } else {
            logger.error("사용자 삭제 실패: ID {}", userId);
            throw new RuntimeException("사용자 삭제에 실패했습니다.");
        }
    }

    @Override
    public List<UserVO> searchUsers(String searchKeyword) {
        String tenantId = TenantContextHolder.getCurrentTenantId();

        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", tenantId);
        params.put("searchKeyword", searchKeyword);

        logger.debug("사용자 검색: 키워드 {}", searchKeyword);

        return userMapper.searchUsers(params);
    }

    @Override
    public int getUserCount() {
        String tenantId = TenantContextHolder.getCurrentTenantId();

        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", tenantId);

        return userMapper.countUsers(params);
    }
}
