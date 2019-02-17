package com.doraro.mapper

import com.doraro.SuperIntergrateSetup
import com.doraro.service.ICommentUserService
import org.springframework.beans.factory.annotation.Autowired

class CommentUserMapperTest extends SuperIntergrateSetup {
    @Autowired
    CommentUserMapper mapper

    def testService() {
        given:
        def count = mapper.selectById(1)
        expect:
        count.id == 1
    }
}
