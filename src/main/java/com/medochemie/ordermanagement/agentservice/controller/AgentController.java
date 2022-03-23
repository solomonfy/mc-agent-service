package com.medochemie.ordermanagement.agentservice.controller;

import com.medochemie.ordermanagement.agentservice.entity.Agent;
import com.medochemie.ordermanagement.agentservice.repository.AgentRepository;
import com.medochemie.ordermanagement.agentservice.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;


    @GetMapping("/")
    public ResponseEntity<List<Agent>> getAllAgents() {
        List<Agent> agents = agentService.findAll();
        return new ResponseEntity(agents, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable String id) {
        return new ResponseEntity(agentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/agent-name/{agentName}")
    public ResponseEntity<Agent> getAgentByName(@PathVariable String agentName) {
        return new ResponseEntity(agentService.findAgentByName(agentName), HttpStatus.OK);
    }

    @PostMapping("/add-agent")
    public ResponseEntity<Agent> addNewAgent(@RequestBody Agent agent) {
        try {
            if (agent.getAddress() != null && agent.getAgentName() != null) {
                agent.setCreatedOn(new Date());
                agent.setCountryId(agent.getAddress().getCountry());
                Agent _agent = agentService.addNewAgent(agent);
                return new ResponseEntity(_agent, HttpStatus.CREATED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
