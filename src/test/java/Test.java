import com.hxl.utils.openapi.*;
import com.hxl.utils.openapi.body.OpenApiApplicationJSONBodyNode;
import com.hxl.utils.openapi.body.OpenApiFormDataRequestBodyNode;
import com.hxl.utils.openapi.body.OpenApiFormUrlencodedBodyNode;
import com.hxl.utils.openapi.body.OpenApiRequestBodyNode;
import com.hxl.utils.openapi.parameter.OpenApiHeaderParameter;
import com.hxl.utils.openapi.parameter.OpenApiUrlPathParameter;
import com.hxl.utils.openapi.parameter.OpenApiUrlQueryParameter;
import com.hxl.utils.openapi.properties.*;
import com.hxl.utils.openapi.response.OpenApiResponseDetailNode;
import com.hxl.utils.openapi.response.OpenApiStatusCodeResponse;


public class Test {
    public static void main(String[] args) {
        PropertiesBuilder propertiesBuilder = new PropertiesBuilder()
                .addObjectProperties("type", (b) -> {
                    b.addBooleanProperties("name", "a");
                }, "描述")
                .addStringProperties("a", "描述");
        OpenApiStatusCodeResponse openApiStatusCodeResponse =
                new OpenApiStatusCodeResponse(200, new OpenApiResponseDetailNode("响应成功", "application/json", propertiesBuilder.object()));

        ObjectProperties object = new PropertiesBuilder()
                .addObjectProperties("a", (v) -> v.addProperties("a", "描述", Type.string), "描述")
                .addStringProperties("sss", "描述")
                .addProperties("test", "测试", Type.file)
                .addObjectProperties("address", (v) -> {
                    v.addProperties("city", "城市", Type.string);
                    v.addProperties("area", "区域", Type.string);
                }, "地址").object();

        OpenApiRequestBodyNode openApiRequestBodyNode = new OpenApiFormUrlencodedBodyNode(object);

        OpenApiFormDataRequestBodyNode openApiFormDataRequestBodyNode = new OpenApiFormDataRequestBodyNode(object);

        //一个openapi只需要一个OpenApi实例
        //使用OpenApiBuilder创建不同的http请求，通过addToOpenApi添加到OpenApi实例,通过任意json序列化库直接将OpenApi转换为json
        OpenApi openApi = new OpenApi();
        String curl = OpenApiBuilder.post("/user/get/{userId}", "获取用户")
                .addParameter(new OpenApiUrlPathParameter("userId", "sdd", true, Type.number))
                .addParameter(new OpenApiHeaderParameter("name", "asd", false, Type.string))
                .addParameter(new OpenApiUrlQueryParameter("urlpaa", "asd", false, Type._boolean))
                .setRequestBody(openApiFormDataRequestBodyNode)
                .setResponse(openApiStatusCodeResponse).toCurl();
        System.out.println(curl);

        System.out.println(openApi);
    }
}
