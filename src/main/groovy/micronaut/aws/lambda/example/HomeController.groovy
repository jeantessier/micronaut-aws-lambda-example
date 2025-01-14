package micronaut.aws.lambda.example
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class HomeController {

    @Get
    Map<String, Object> index() {
        [message: "Hello World"]
    }
}