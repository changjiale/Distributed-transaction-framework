package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.mapper.ProMapper;
import server.model.Test;
import server.txTransaction.annotation.TxTransactional;

@Service
public class ProService {

    @Autowired
    private ProMapper proMapper;


    @TxTransactional(isEnd = true)
    @Transactional
    public void test() {
        Test test = new Test();
        test.setName("222");
        proMapper.insert("222");
    }

}
