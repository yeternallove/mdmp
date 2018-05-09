package com.eternallove.mdmp;

import com.eternallove.mdmp.model.task.TaskDefined;
import com.eternallove.mdmp.model.user.UserBean;
import com.eternallove.mdmp.model.user.UserView;
import com.eternallove.mdmp.util.gson.DateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class mdmpUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void gsonBuilderTest() throws JSONException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        System.out.println("asd");
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .create();
        String s1 = "[{\"flowId\":\"25\",\"flowName\":\"学生工作\",\"mdCluster\":null,\"mdModel\":null,\"mdConcepet\":null,\"operateType\":\"U\",\"pendingLink\":\"测试环节1\",\"beforeTime\":1524465462853,\"afterTime\":1524370594883,\"taskStatus\":2,\"taskId\":\"29\"}]";
        String s = "{\"beforeTime\":1524465462853,\"afterTime\":1524370594883}";
        JSONObject json = new JSONObject(s);
        TaskDefined taskDefined = gson.fromJson(s, TaskDefined.class);
        List<TaskDefined> tasks = gson.fromJson(s1, new TypeToken<List<TaskDefined>>() {
        }.getType());

    }

    @Test
    public void loginTest() throws JSONException {
        String ss = "{\"userInfo\":{\"role\":\"不然一般人\",\"roleId\":149,\"departmentId\":1,\"oldpassword\":\"\",\"pageRightList\":[{\"name\":\"系统配置\",\"id\":8,\"status\":1},{\"name\":\"日志记录\",\"id\":6,\"status\":1},{\"name\":\"用户管理\",\"id\":5,\"status\":1},{\"name\":\"服务管理\",\"id\":4,\"status\":1},{\"name\":\"任务监控\",\"id\":3,\"status\":1},{\"name\":\"工作流管理\",\"id\":2,\"status\":1},{\"name\":\"主数据管理\",\"id\":1,\"status\":1},{\"name\":\"我的任务\",\"id\":7,\"status\":1}],\"password\":\"\",\"createTime\":{\"date\":22,\"hours\":12,\"seconds\":32,\"month\":3,\"timezoneOffset\":-480,\"year\":118,\"minutes\":9,\"time\":1524370172532,\"day\":0},\"phone\":\"\",\"enable\":1,\"id\":160,\"department\":\"人事部\",\"account\":\"test\",\"lastUpdateTime\":1525155588068,\"username\":\"哈哈哈\"}}";
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateAdapter())
                .create();
        MyTest uer = gson.fromJson(ss, MyTest.class);
        UserView userView = uer.getUserInfo();
        UserView uu = UserView.build(userView.toString());
        System.out.println();
    }

    class MyTest {
        UserView userInfo;

        public UserView getUserInfo() {
            return userInfo;
        }

        @Override
        public String toString() {
            return "MyTest{" +
                    "userInfo=" + userInfo.toString() +
                    '}';
        }
    }

    @Test
    public void copyTEST() throws CloneNotSupportedException {
        UserView userView = new UserView();
        userView.setRole("123");
        userView.setAccount("qwe");
        UserView userView1 = (UserView) userView.clone();
        userView1.setRole("444");
        userView1.setAccount("asdasd");
        System.out.println(userView.toString());
        System.out.println(userView1.toString());

    }

    @Test
    public void mySplit() {
        String av = "1#名称#";
        String[] avs = av.split("#");
        for (String s : avs) {
            System.out.println(s);
        }
    }
}