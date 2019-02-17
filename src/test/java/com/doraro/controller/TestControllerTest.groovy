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
        def content = res.contentAsJson
        content != null
        content."content" == "this is content"
    }

    @Unroll("Check #id matches #expectedResult")
    def testGetWithArg() {
        given:
        def url = "/test2"
        when:
        def res = Get(url, ['id': id])
        then:
        res.status == 200
        def content = res.contentAsJson
        content != null
        content."categoryId" == expectedResult
        where:
        id | expectedResult
        1  | '1'
        3  | '3'
    }

    def testGetWithExp() {
        given:
        def url = "/test2"
        when:
        def res = Get(url, ['id1': 1])
        then:
        res.status != 200
        def content = res.contentAsJson
        content != null
        content."message" == "id 不能为空"

    }

    @Unroll("Check #Pid ,#Pname matches #id, #name")
    def testPostWithJson() {
        given:
        def url = "/test3"
        def param = JsonOutput.toJson([
                "id"  : Pid,
                "name": Pname
        ])
        when:
        def res = Post(url, param)
        then:
        res.status == 200
        def content = res.contentAsJson
        content != null
        content."id" == id
        content."name" == name
        where:
        Pid | Pname   | id | name
        1   | "test1" | 1  | "test1"
        3   | "test2" | 3  | "test2"
    }

}
