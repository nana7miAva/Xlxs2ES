package mysql;


import com.alibaba.excel.util.FileUtils;
import entity.CaseDetailInfo;
import entity.CaseInfo;
import mapper.CaseDetailInfoMapper;
import mapper.CaseInfoMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MysqlDao {
    private static Logger log = Logger.getLogger(MysqlDao.class);

    private SqlSession session = null;


    private CaseDetailInfoMapper caseDetailInfoMapper;
    private CaseInfoMapper caseInfoMapper;

    public MysqlDao() {
        InputStream in = null;
        try {
            in = FileUtils.openInputStream(new File("config/mybatis-config.xml"));
//            in = Resources.getResourceAsStream("config/mybatis-config.xml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //获取SqlSessionFactory工厂对象
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);

        //获取sqlSession对象
        session = fac.openSession(true);//true表示自动提交事物，false表示手动提交事务
        caseDetailInfoMapper = session.getMapper(CaseDetailInfoMapper.class);
        caseInfoMapper = session.getMapper(CaseInfoMapper.class);


    }

    public CaseDetailInfo getCaseDetailInfo(String caseDetailId) {
        CaseDetailInfo caseDetailInfos = caseDetailInfoMapper.getAllDetailDataById(caseDetailId);
        return caseDetailInfos;
    }


    public List<CaseInfo> getCaseInfo() {


        List<CaseInfo> list;

        list = caseInfoMapper.getAllCaseInfo();

        return list;
    }

}
