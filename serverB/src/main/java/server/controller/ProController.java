package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.service.ProService;

@RestController
@RequestMapping("/provider")
public class ProController {

    @Autowired
    private ProService proService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
            proService.test();
    }
}
