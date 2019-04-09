package college.service;

import college.dao.SampleMapper;
import college.po.SamplePo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SampleService {

    @Resource
    SampleMapper sampleMapper;

    public SamplePo testSample(int id) {
        return sampleMapper.selectId(id);
    }
}
