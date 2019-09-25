package com.hero.sell.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author yejx
 * @Date 2019/9/8
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinRest {

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。。");
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxa5ded3a627b40b78&secret=b7d4986df3c922e754a987efee743454&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}
