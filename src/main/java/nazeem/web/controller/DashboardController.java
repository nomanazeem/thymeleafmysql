package nazeem.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("/")
    public String index() {
        return "admin/dashboard/index";
    }

    // Added to test 500 page
    @RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error500() throws Exception {
        throw new Exception();
    }

}
