package com.fastenum.fenum;

import com.fastenum.bo.EnumBO;
import com.fastenum.condition.EnumCondition;
import com.fastenum.fservice.EnumService;
import com.fastenum.fenum.controller.EnumController;
import com.fastenum.fenum.start.FenumApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes=FenumApplication.class)
class FenumApplicationTests {

    @Resource
    private EnumService enumService;

    @Resource
    private EnumController codeEnumController;

    /**
     * 条件查询代码枚举值
     */
    @Test
    void selectCodeEnumByCondition() {
        EnumCondition enumCondition= new EnumCondition("C00001" , "LA");
        Date date = new Date(  );
        List<EnumBO> enumBOList = enumService.getEnumBoListByCondition( enumCondition );
        Date edate = new Date(  );
        System.out.println(date.getTime() - edate.getTime());
        Assert.notEmpty( enumBOList );
    }


}
