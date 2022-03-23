package com.medochemie.ordermanagement.agentservice.service;

import com.medochemie.ordermanagement.agentservice.entity.Agent;

import java.util.List;

public interface AgentService {
    Agent addNewAgent(Agent agent);
    Agent findAgentByName(String agentName);
    Agent findById(String id);
    List<Agent> findAll();
    Agent updateAgent(String id);
    String deleteAgent(String id);

}
