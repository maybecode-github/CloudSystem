package net.maybemc.cloud.http.server.rest.controller;

import net.maybemc.cloud.api.cloud.entity.server.CloudServer;
import net.maybemc.cloud.http.server.service.server.ICloudServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CloudServerController {

    @Autowired
    private ICloudServerService cloudServerService;

    @ResponseBody
    @GetMapping("/cloudServer/all")
    public List<CloudServer> fetchAllCloudServers() {
        return cloudServerService.fetchAll();
    }

    @GetMapping("/cloudServer/get")
    public ResponseEntity<CloudServer> fetchCloudServerByName(@RequestParam("serverName") String serverName) {
        CloudServer cloudServer = cloudServerService.fetchByName(serverName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            return new ResponseEntity<>(cloudServer, headers, HttpStatus.OK);
        } catch (NullPointerException exception) {
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cloudServer/get/all")
    public ResponseEntity<List<CloudServer>> getAllCloudServersFromCloudGroup(@RequestParam("serverName") String groupName) {
        return ResponseEntity.ok(cloudServerService.fetchFromGroup(groupName));
    }

    @PostMapping(value = "/cloudServer/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CloudServer> createCloudServer(@RequestBody CloudServer cloudServer) {
        CloudServer newCloudServer;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            newCloudServer = cloudServerService.save(cloudServer);
        } catch (Exception exception) {
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newCloudServer, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cloudServer/delete")
    public ResponseEntity<String> deleteCloudServer(@RequestParam("serverName") String serverName) {
        cloudServerService.delete(serverName);
        return ResponseEntity.ok(serverName);
    }

}
