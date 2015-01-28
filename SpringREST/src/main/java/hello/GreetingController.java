package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * A key difference between a traditional MVC controller and the RESTful web service controller above is the way that the HTTP response body is created.
 * A view technology return HTML, moreover rest technology return an object in JSON format.
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*
     * RequestMapping without parameter maps all HTTP operations by default (GET, POST, PUT, DELETE)
     * RequestParam mapping the name attribute of url to parameter in method
     */
    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}