package consumer.server.controller;

import consumer.server.service.ConService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConController {

    @Autowired
    private ConService conService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() {
            conService.test();
    }
}
