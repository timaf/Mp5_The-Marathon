package at.refugeescode.Mp5_TheMarathon.view;

import at.refugeescode.Mp5_TheMarathon.model.Runner;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/runners")
public class RunnersEndpoint {

    private List<Runner> runners = new ArrayList <>();

    @GetMapping
    List<Runner> bringAll(){
        return runners;
    }

    @PostMapping
    Runner send(@RequestBody Runner runner){
        runners.add(runner);
        return runner;
    }

    @GetMapping("/winner")
   Runner getWinner(){
        Optional<Runner>  theWinner = runners.stream()
                .sorted((r1,r2) -> r1.getTime().compareTo(r2.getTime()))
                .findFirst();
        if (!theWinner.isPresent()){
           return new Runner("no winner", Duration.ZERO);
        }
        return theWinner.get();
   }
}

