package hello;

import com.poc.local.LocalContext;
import com.poc.local.LocalCounter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        LocalCounter.increment();
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/echo/{id}")
    public String echo(@PathVariable("id") long id) {
        LocalContext.store(id);
        String returnValue = LocalContext.get();
        LocalContext.clean();
        return returnValue;
    }

}