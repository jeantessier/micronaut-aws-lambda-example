package micronaut.aws.lambda.example
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction
import io.micronaut.function.aws.proxy.MockLambdaContext
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class HomeControllerSpec extends Specification {
    @Shared
    @AutoCleanup
    ApiGatewayProxyRequestEventFunction handler = new ApiGatewayProxyRequestEventFunction()
    void "test handler"() {
        given:
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent()
        request.path = "/"
        request.httpMethod = HttpMethod.GET.toString()

        when:
        def response = handler.handleRequest(request, new MockLambdaContext())

        then:
        HttpStatus.OK.code == response.statusCode.intValue()
        "{\"message\":\"Hello World\"}" == response.getBody()
    }

}