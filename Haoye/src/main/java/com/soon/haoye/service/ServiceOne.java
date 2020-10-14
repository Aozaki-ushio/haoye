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
                    pjm.setNumberOfsame(1);
                    pjm.setUid(pje.getUid());
                    pjm.setNumber(pje.getNumber());
                    pjm.setListOf4X(pje.getListOf4X());
                    pjm.setListOfMemoryCrystalls(pje.getListOfMemoryCrystalls());
                    pjm.setIsSelled(pje.getIsSelled());
                    mapOfPjm.put(pjm.getUid(),pjm);
                }
                else {
                    ProjectMahoushyojyomodel pjm = mapOfPjm.get(pje.getUid());
                    pjm.setNumberOfsame(pjm.getNumberOfsame()+1);
                }
            }
        }
        List<ProjectMahoushyojyomodel> listOfpjm = new ArrayList<>();
        for (String t : mapOfPjm.keySet()){
            ProjectMahoushyojyomodel pjm = mapOfPjm.get(t);
            if (pjm.getNumberOfsame()==list.size()){
                listOfpjm.add(pjm);
            }
        }
        return listOfpjm;
    }

    public Object addAccountNumber(ProjectMahoushyojyoEntity pje) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        pje.setNumber(uuid);//生成选号码
       return pjmJpa.save(pje);
    }

    public void deleteAccountNumberByUid(String uid) {
        pjmJpa.deleteById(uid);
    }

    public void changeAccountNumber(ProjectMahoushyojyoEntity Pre) {
        pjmJpa.save(Pre);
    }


}
