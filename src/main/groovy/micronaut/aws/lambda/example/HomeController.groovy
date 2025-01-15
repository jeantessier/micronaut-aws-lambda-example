package micronaut.aws.lambda.example

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.annotation.Nullable

@Controller
class HomeController {

    @Get
    Map<String, Object> index(@Nullable String name) {
        def greeting = name ?: "World"
        [ message: "Hello ${greeting}" as String ]
    }

    @Get("/micronaut-aws-lambda-example")
    Map<String, Object> second(@Nullable String name) {
        def greeting = name ?: "World"
        [ message: "For the second time: Hello ${greeting}" as String ]
    }

}
