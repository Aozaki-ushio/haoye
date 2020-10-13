package com.soon.haoye.service;

import com.soon.haoye.dao.ProjectMahoushyojoJpa;
import com.soon.haoye.entity.ProjectMahoushyojyoEntity;
import com.soon.haoye.model.ProjectMahoushyojyomodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceOne {

    @Autowired
    ProjectMahoushyojoJpa pjmJpa;
//     public List findnumber(){
//         List list = new ArrayList();
//         for(ProjectMahoushyojyoEntity  proj: pjmJpa.findAll()){
//             Map map=new HashMap();
//             map.put("uid",proj.getUid());
//             map.put("list3x",proj.getList3X());
//             map.put("list4x",proj.getList4X());
//             map.put("number",proj.getNumber());
//             list.add(map);
//         }
//         return list;
//     }
    public List FindNumerByCode(List<String> list){
        Map<String , ProjectMahoushyojyomodel> mapOfPjm= new HashMap<>();
        for(String str :list ){
            String str1 = "%"+str+"%";
            List<ProjectMahoushyojyoEntity> listOfPje = pjmJpa.FindNumberByCode(str1);
            for(ProjectMahoushyojyoEntity pje: listOfPje ){
                if (mapOfPjm.get(pje.getUid())==null){
                    ProjectMahoushyojyomodel pjm = new ProjectMahoushyojyomodel();
                    pjm.setNumberofsame(1);
                    pjm.setList3X(pje.getList3X());
                    pjm.setList4X(pje.getList4X());
                    pjm.setNumber(pje.getNumber());
                    pjm.setUid(pje.getUid());
                    mapOfPjm.put(pjm.getUid(),pjm);
                }
                else {
                    ProjectMahoushyojyomodel pjm = mapOfPjm.get(pje.getUid());
                    pjm.setNumberofsame(pjm.getNumberofsame()+1);
                }
            }
        }
        List<ProjectMahoushyojyomodel> listOfpjm = new ArrayList<>();
        for (String t : mapOfPjm.keySet()){
            ProjectMahoushyojyomodel pjm = mapOfPjm.get(t);
            if (pjm.getNumberofsame()==list.size()){
                listOfpjm.add(pjm);
            }
        }
        return listOfpjm;
    }

    public Object addAccountNumber(List<ProjectMahoushyojyoEntity> listOfPre) {
       return pjmJpa.saveAll(listOfPre);
    }

    public void deleteAccountNumberByUid(String uid) {
        pjmJpa.deleteById(uid);
    }

    public void changeAccountNumber(ProjectMahoushyojyoEntity Pre) {
        pjmJpa.save(Pre);
    }


}
