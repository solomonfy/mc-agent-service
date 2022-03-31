package com.medochemie.ordermanagement.agentservice.controller;

import com.medochemie.ordermanagement.agentservice.entity.Agent;
import com.medochemie.ordermanagement.agentservice.service.AgentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping("/list")
    @ApiOperation(
            value = "Returns all agents",
            notes = "",
            response = ResponseEntity.class)
    public ResponseEntity<List<Agent>> getAllAgents() {
        List<Agent> agents = agentService.findAll();
        return new ResponseEntity(agents, HttpStatus.OK);
    }


    @GetMapping("/list/{id}")
    @ApiOperation(
            value = "Returns an agent from the data with the give id",
            notes = "Given an ID, returns the agent",
            response = ResponseEntity.class)
    public ResponseEntity<Agent> getAgentById(
            @ApiParam(value = "Id is needed to retrieve an agent", required = true)
            @PathVariable String id) {
        return new ResponseEntity(agentService.findById(id), HttpStatus.OK);
    }



    @GetMapping("/list/agent-name/{agentName}")
    @ApiOperation(
            value = "Returns an agent from the data with the given name",
            notes = "Given agents name, returns the agent",
            response = ResponseEntity.class)
    public ResponseEntity<Agent> getAgentByName(
            @ApiParam(value = "Agent name is required to get the agent", required = true)
            @PathVariable String agentName) {
        return new ResponseEntity(agentService.findAgentByName(agentName), HttpStatus.OK);
    }




    @PostMapping("/add-agent")
    @ApiOperation(
            value = "Adds a new agent to the database",
            notes = "Given all the required information, adds the new agent",
            response = ResponseEntity.class
    )
    public ResponseEntity<Agent> addNewAgent(
            @ApiParam(value = "Full agent information is required")
            @RequestBody Agent agent) {
        try {
            if (agent.getAddress() != null && agent.getAgentName() != null) {
                agent.setCreatedOn(new Date());
                agent.setCountryId(agent.getAddress().getCountry());
                Agent newAgent = agentService.addNewAgent(agent);
                return new ResponseEntity(newAgent, HttpStatus.CREATED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
