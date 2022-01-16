package net.maybemc.cloud.http.server.rest.controller;

import net.maybemc.cloud.api.cloud.entity.group.CloudGroup;
import net.maybemc.cloud.http.server.service.group.ICloudGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CloudGroupController {

    @Autowired
    private ICloudGroupService cloudGroupService;

    @ResponseBody
    @GetMapping("/cloudGroups")
    public List<CloudGroup> fetchAllCloudGroups() {
        return cloudGroupService.fetchAll();
    }

    @GetMapping("/cloudGroup/get")
    public ResponseEntity<CloudGroup> fetchCloudGroupByName(@RequestParam("groupName") String groupName) {
        CloudGroup cloudGroup = cloudGroupService.fetchByName(groupName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            return new ResponseEntity<>(cloudGroup, headers, HttpStatus.OK);
        } catch (NullPointerException exception) {
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/cloudGroup/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CloudGroup> createCloudGroup(@RequestBody CloudGroup cloudGroup) {
        CloudGroup newCloudGroup;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            newCloudGroup = cloudGroupService.save(cloudGroup);
        } catch (Exception exception) {
            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newCloudGroup, headers, HttpStatus.OK);
    }

}
