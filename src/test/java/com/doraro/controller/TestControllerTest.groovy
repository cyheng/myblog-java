package com.doraro.controller

import groovy.json.JsonOutput
import spock.lang.Unroll


class TestControllerTest extends SuperMockMvcSetup {

    def testGet() {
        given:
        def url = "/test"
        when:
        def res = Get(url)
        then:
        res.status == 200
        def content = res.result
        content != null
        content.content == "this is content"
    }

    @Unroll("testGetWithArg,Check id:#id matches result:#expectedResult")
    def testGetWithArg() {
        given:
        def url = "/test2"
        when:
        def res = Get(url, ['id': id])
        then:
        res.status == 200
        def content = res.result
        content != null
        content.id == expectedResult
        where:
        id | expectedResult
        1  | 1
        3  | 3
    }

    def testGetWithExp() {
        given:
        def url = "/test2"
        when:
        def res = Get(url, ['id1': 1])
        then:
        res.status != 200
        res.result.error == "BAD_REQUEST"

    }

    @Unroll("Check id:#Pid ,name:#Pname matches #id, #categoryName")
    def testPostWithJson() {
        given:
        def url = "/test3"
        def param = JsonOutput.toJson([
                "id"          : Pid,
                "categoryName": Pname
        ])
        when:
        def res = Post(url, param)
        then:
        res.status == 200
        def content = res.result
        content != null
        content.id == id
        content.name == categoryName
        where:
        Pid | Pname   | id | categoryName
        1   | "test1" | 1  | "test1"
        3   | "test2" | 3  | "test2"
    }

    def testJsonValueEnum() {
        given:
        def url = "/test5"
        when:
        def res = Get(url)
        then:
        res.status == 200
        res.prettyPrint()

    }
}
