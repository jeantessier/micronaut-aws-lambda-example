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

    def "test handler"() {
        given:
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent()
        request.path = "/"
        request.httpMethod = HttpMethod.GET as String

        when:
        def response = handler.handleRequest(request, new MockLambdaContext())

        then:
        response.statusCode.intValue() == HttpStatus.OK.code
        response.getBody() == "{\"message\":\"Hello World\"}"
    }

    def "test name"() {
        given:
        def name = "Jean"

        and:
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent()
        request.path = "/"
        request.httpMethod = HttpMethod.GET as String
        request.queryStringParameters = [ name: name ]

        when:
        def response = handler.handleRequest(request, new MockLambdaContext())

        then:
        response.statusCode.intValue() == HttpStatus.OK.code
        response.getBody() == "{\"message\":\"Hello ${name}\"}"
    }

}
