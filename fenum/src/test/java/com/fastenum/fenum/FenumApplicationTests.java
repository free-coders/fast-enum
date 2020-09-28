package com.fastenum.fenum;

import com.fastenum.bo.EnumBO;
import com.fastenum.condition.CodeEnumCondition;
import com.fastenum.einterface.CodeEnumService;
import com.fastenum.fenum.controller.CodeEnumController;
import com.fastenum.fenum.start.FenumApplication;
import com.sun.tools.javac.jvm.Code;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes=FenumApplication.class)
class FenumApplicationTests {

    @Resource
    private CodeEnumService codeEnumService;

    @Resource
    private CodeEnumController codeEnumController;

    /**
     * 条件查询代码枚举值
     */
    @Test
    void selectCodeEnumByCondition() {
        CodeEnumCondition codeEnumCondition = new CodeEnumCondition("C00001" , "LA");
        Date date = new Date(  );
        List<EnumBO> enumBOList = codeEnumService.getEnumBoListByCondition( codeEnumCondition );
        Date edate = new Date(  );
        System.out.println(date.getTime() - edate.getTime());
        Assert.notEmpty( enumBOList );
    }


}
