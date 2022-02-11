package com.gymmanage.client.dao;

import com.gymmanage.client.entity.Client;
import com.gymmanage.utils.AjaxRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {
    List<Client> selectAll();

    int clientAdd(Client client);

    int clientUpdate(Client client);

    int clientDel(Integer id);

    Client getOne(Integer id);
}
