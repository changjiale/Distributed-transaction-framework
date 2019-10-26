package consumer.server.service;

import consumer.server.txTransaction.annotation.TxTransactional;
import consumer.server.mapper.ConMapper;
import consumer.server.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class ConService{

    @Autowired
    private ConMapper conMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    @TxTransactional
    public void test(){
        Test test = new Test();
        test.setName("111");
        conMapper.insert(test);
        restTemplate.getForObject("http://localhost:8082/provider/test", String.class);
        //HttpClient.get("http://localhost:8082/provider/test");
        int i = 100/0;
    }

}
