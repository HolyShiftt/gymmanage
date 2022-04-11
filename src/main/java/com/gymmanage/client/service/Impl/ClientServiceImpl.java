package com.gymmanage.client.service.Impl;

import com.gymmanage.client.dao.ClientMapper;
import com.gymmanage.client.entity.Client;
import com.gymmanage.client.service.ClientService;
import com.gymmanage.utils.AjaxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<Client> selectAll() {
        return clientMapper.selectAll();
    }

    @Override
    public AjaxRes clientAdd(Client client) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            clientMapper.clientAdd(client);
            ajaxRes.setMsg("添加成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes clientUpdate(Client client) {
        if (client.getIs_vip()==null){
            client.setIs_vip(0);
        }
        AjaxRes ajaxRes = new AjaxRes();
        try {
            clientMapper.clientUpdate(client);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("更新失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes clientDel(Integer id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            clientMapper.clientDel(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public Client getOne(Integer id) {
        return clientMapper.getOne(id);
    }
}
