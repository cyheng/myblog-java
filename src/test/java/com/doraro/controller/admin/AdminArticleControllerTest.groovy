package com.doraro.controller.admin

import com.doraro.controller.SuperMockMvcSetup

class AdminArticleControllerTest extends SuperMockMvcSetup {
    def baseUrl = "/api/admin/articles"

    void testGetArticleNumber() {
        when:
        def res = Get(baseUrl + "/numbers")
        then:
        res.status == 200
        def json = res.result
        json != null
        res.prettyPrint()
    }

    void testFindAllUnDelete() {
        when:
        def res = Get(baseUrl)
        then:
        res.status == 200
        res.prettyPrint()
    }

    void testGetArticle() {
        when:
        def res = Get(baseUrl + "/1098634387660009473")
        then:
        res.status == 200
        res.prettyPrint()
    }

    void testGetAllDeleteArticle() {
        given:
        def url = "/getDeletes"
        when:
        def res = Get(baseUrl + url)
        then:
        res.status == 200
        res.prettyPrint()
    }

    void testCreateArticle() {

    }

    void testUpdateArticle() {
    }

    void testDeleteArticle() {
    }
}
