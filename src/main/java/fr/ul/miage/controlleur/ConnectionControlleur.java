package fr.ul.miage.controlleur;

import fr.ul.miage.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connection")
public class ConnectionControlleur {

    @Autowired
    private ConnectionService connectionService;

    @PostMapping
    public ResponseEntity<Boolean> verifierClef(
            @RequestBody String clef) {
        return connectionService.verifierClef(clef);
    }

}
