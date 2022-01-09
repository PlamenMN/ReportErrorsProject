package com.PNV.RepErrors.controllers;

import com.PNV.RepErrors.entities.Client;
import com.PNV.RepErrors.entities.payload.request.ClientRequest;
import com.PNV.RepErrors.repositories.ClientRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class ClientController {
    private final ClientRepo clientRepo;
    ClientController(ClientRepo clientRepo){
        this.clientRepo=clientRepo;
    }
    @PostMapping("/create")
    public ResponseEntity<?> persistNewClient(@RequestBody ClientRequest client) {
            Client client1 =clientRepo.findClientByUsername(client.getUsername());
            if(client1 !=null)
                return ResponseEntity.ok("User already exists");
        return ResponseEntity.ok("User "+ clientRepo.save(
                new Client(client.getFirstName(),client.getLastName(),client.getUsername()
                        ,client.getPassword(),client.getEmail())).getUsername()+" is saved.");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> persistDeleteClient(String username) {
        Optional<Client> result= Optional.ofNullable(clientRepo.findClientByUsername(username));
        if(result.isEmpty())
        {
            return ResponseEntity.ok("Client not found!");
        }
        clientRepo.delete(result.get());
        return ResponseEntity.ok(username+" was Deleted!");
    }
    @GetMapping("/filter")
    public ResponseEntity<?> filterClients(@RequestParam(defaultValue = "") String firstName,
                                           @RequestParam(defaultValue = "")String lastName,
                                           @RequestParam(defaultValue = "1")int currentPage,
                                           @RequestParam(defaultValue = "2")int perPage){
        Pageable pageable =PageRequest.of(currentPage-1,perPage);
        Page<Client> clients =clientRepo.filterClients(pageable,firstName.toLowerCase(),lastName.toLowerCase());
        Map<String,Object> response=new HashMap<>();
        response.put("Total elements",clients.getTotalElements());
        response.put("Total pages",clients.getTotalPages());
        response.put("clients",clients.getContent());
        return ResponseEntity.ok(response);
    }
}


