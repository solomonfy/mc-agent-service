package com.medochemie.ordermanagement.agentservice.service.impl;

import com.medochemie.ordermanagement.agentservice.entity.Agent;
import com.medochemie.ordermanagement.agentservice.repository.AgentRepository;
import com.medochemie.ordermanagement.agentservice.service.AgentService;
import com.medochemie.ordermanagement.agentservice.service.utils.GenerateAgentCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AgentServiceImpl implements AgentService {

    @Autowired
    AgentRepository repository;

    @Override
    public Agent addNewAgent(Agent agent) {
        Agent existingAgent = null;

        if (agent.getId() != null) {
            existingAgent = repository.findById(agent.getId()).orElse(null);
        }

        if (agent.getId() == null) {
            String agentCode = GenerateAgentCode.generateAgentCode(agent.getAgentName(), agent.getCountryId());
            agent.setAgentCode(agentCode);
            agent.setCreatedOn(new Date());
            agent.setActive(false);
            agent.setCreatedBy("logged in user");
        }
        try {
            agent = repository.save(agent);
        } catch (Exception e) {
            throw e;
        }

        return agent;
    }

    @Override
    public Agent findAgentByName(String agentName) {
        if (agentName != null) {
            Agent foundAgent = repository.findAgentByName(agentName);
            return foundAgent;
        } else {
            return null;
        }

    }

    @Override
    public Agent findById(String id) {
        Agent agent = repository.findById(id).get();
        return agent;
    }

    @Override
    public List<Agent> findAll() {
        return repository.findAll();
    }

    @Override
    public Agent updateAgent(String id) {
        return null;
    }

    @Override
    public String deleteAgent(String id) {
        Agent existingAgent = repository.findById(id).get();
        if (id != null && existingAgent !=null) {
            repository.deleteById(id);
        }
        return "Agent with id - " + id + " has been deleted";
    }
}
