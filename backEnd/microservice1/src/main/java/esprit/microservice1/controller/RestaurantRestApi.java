package esprit.microservice1.controller;

import esprit.microservice1.entities.Ttable;
import esprit.microservice1.services.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("mic1/")
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantRestApi {

    @Autowired
    ITableService iTableService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello I'm Microservice 1 : Restaurant ";
    }


    @GetMapping("/getall")
    public List<Ttable> getAllTables() {
        return iTableService.getAllTables();
    }

    @GetMapping("gettable/{id}")
    public Ttable getTableById(@PathVariable Long id) {
        return iTableService.getTableById(id);
    }

    @PostMapping("/createtab")
    public Ttable createTable(@RequestBody Ttable table) {
        return iTableService.createTable(table);
    }



    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable Long id) {
        iTableService.deleteTable(id);
    }
}
