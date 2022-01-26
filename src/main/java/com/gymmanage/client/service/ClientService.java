package com.gymmanage.client.service;

import com.gymmanage.client.entity.Client;
import com.gymmanage.gym.entity.Coach;
import com.gymmanage.utils.AjaxRes;

import java.util.List;

public interface ClientService {
    List<Client> selectAll();

    AjaxRes clientAdd(Client client);

    AjaxRes clientUpdate(Client client);

    AjaxRes clientDel(Integer id);

    Client getOne(Integer id);
}
