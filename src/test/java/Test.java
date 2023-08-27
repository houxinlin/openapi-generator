import com.hxl.utils.openapi.*;
import com.hxl.utils.openapi.body.OpenApiApplicationJSONBodyNode;
import com.hxl.utils.openapi.body.OpenApiFormDataRequestBodyNode;
import com.hxl.utils.openapi.body.OpenApiFormUrlencodedBodyNode;
import com.hxl.utils.openapi.body.OpenApiRequestBodyNode;
import com.hxl.utils.openapi.parameter.OpenApiHeaderParameterNode;
import com.hxl.utils.openapi.parameter.OpenApiUrlPathParameter;
import com.hxl.utils.openapi.parameter.OpenApiUrlQueryParameter;
import com.hxl.utils.openapi.properties.*;
import com.hxl.utils.openapi.response.OpenApiResponseDetailNode;
import com.hxl.utils.openapi.response.OpenApiStatusCodeResponse;


public class Test {
    public static void main(String[] args) {
        PropertiesBuilder propertiesBuilder = new PropertiesBuilder()
                .addObjectProperties("type",(b)->{
                    b.addBooleanProperties("name","a");
                },"描述")
                .addStringProperties("a", "描述");
        OpenApiStatusCodeResponse openApiStatusCodeResponse =
                new OpenApiStatusCodeResponse(200, new OpenApiResponseDetailNode("响应成功","application/json",propertiesBuilder.object()));

        ObjectProperties object = new PropertiesBuilder()
                .addObjectProperties("a", (v) -> {
                    v.addProperties("a", "描述", Type.string);
                }, "描述")
                .addStringProperties("sss", "描述")
                .addProperties("test", "测试", Type.string)
                .addObjectProperties("address", (v) -> {
                    v.addProperties("city", "城市", Type.string);
                    v.addProperties("area", "区域", Type.string);
                }, "地址").object();

        OpenApiRequestBodyNode openApiRequestBodyNode = new OpenApiApplicationJSONBodyNode(object);

        OpenApi api = OpenApiBuilder.get("/user/get/{userId}", "获取用户")
                .addParameter(new OpenApiUrlPathParameter("userId", "sdd", true, Type.number))
                .addParameter(new OpenApiHeaderParameterNode("name", "asd", false, Type.string))
                .addParameter(new OpenApiUrlQueryParameter("urlpaa", "asd", false, Type._boolean))
                .setRequestBody(openApiRequestBodyNode)
                .setResponse(openApiStatusCodeResponse).build();
        System.out.println(api.toJSON());
    }
}
