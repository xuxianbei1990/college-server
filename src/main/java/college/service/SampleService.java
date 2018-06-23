package college.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import college.dao.SampleMapper;
import college.po.SamplePo;

@Service
public class SampleService {

	@Autowired
	SampleMapper sampleMapper;
	
    public SamplePo testSample(int id) {
    	return sampleMapper.selectId(id);
    }
}
