package com.gymmanage.client.dao;

import com.gymmanage.client.entity.Client;
import com.gymmanage.utils.AjaxRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {
    List<Client> selectAll();

    AjaxRes clientAdd(Client client);

    AjaxRes clientUpdate(Client client);

    AjaxRes clientDel(Integer id);

    Client getOne(Integer id);
}
