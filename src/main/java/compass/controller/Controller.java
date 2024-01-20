package compass.controller;

import compass.logic.Side;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {
    Map<String, Map<String, Integer>> sides;

    @PostMapping(value = "/compass", consumes = "application/json")
    public void createSides(@RequestBody Map<String, Map<String, Integer>> map) {
        sides = map;
    }

    @GetMapping(value = "/compass",consumes = "application/json", produces = "application/json")
    public Map<String, String> getSide(@RequestBody Map<String, Integer> deg) {

        Map<String, String> response = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> entry : sides.entrySet()) {
            String name = entry.getKey();
            Map<String, Integer> degs = entry.getValue();
            Side side = new Side(name, degs.get("begin"), degs.get("end"));

            if (side.isSide(deg.get("Degree"))) {
                response.put("Side", side.name);
                break;
            }
        }

        return response;
    }


    //if begin > end
}
