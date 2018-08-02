package testng;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.httpFluent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestNGDemo2 {
    String vkey,vvalue;

    @BeforeMethod
    public void beforeMethod()throws Exception{
        String url = "https://passport.banggo.com/CASServer/custom/registryPage.do";
        String rsString = httpFluent.Get(url);
        Pattern p = Pattern.compile("vKey\" value=\"(.+)\"");
        Matcher m = p.matcher(rsString);
        if (m.find()){
            vkey = m.group(1);
        }

        p = Pattern.compile("vValue\" value=\"(.+)\"");
        m = p.matcher(rsString);
        if (m.find()){
            vvalue = m.group(1);
        }
        System.out.println(vkey+"    "+vvalue);
    }

    @DataProvider
    public Object[][] data1(){
        return new Object[][]{
                new Object[]{"test","已经存在"},
                new Object[]{"admin","已经存在"},
        };
    }

    @Test(dataProvider = "data1")
    public void test1(String userid,String check) throws Exception {
        String url = "https://passport.banggo.com/CASServer/custom/checkUidUniqueAjax.do";
        String body = "uid="+userid+"&"+vkey+"="+vvalue;
        String rsString = httpFluent.Post(url, body);
        Assert.assertTrue(rsString.indexOf(check)>=0,userid+"check Fail");
    }
}
