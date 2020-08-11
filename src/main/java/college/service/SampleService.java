package college.service;

import college.dao.SampleMapper;
import college.po.SamplePo;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SampleService {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Resource
    SampleMapper sampleMapper;

    public SamplePo testSample(int id) {
        return sampleMapper.selectId(id);
    }

    public SamplePo testMapper(int id) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession();
        SampleMapper mapper = sqlSession.getMapper(SampleMapper.class);
        return mapper.selectId(id);
    }
}
