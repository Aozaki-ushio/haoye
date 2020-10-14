package com.soon.haoye.service;

import com.soon.haoye.dao.BackstageJpa;
import com.soon.haoye.dao.ProjectMahoushyojoJpa;
import com.soon.haoye.entity.ProjectMahoushyojyoEntity;
import com.soon.haoye.model.ProjectMahoushyojyomodel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceOne {

    @Autowired
    ProjectMahoushyojoJpa pjmJpa;
    @Autowired
    BackstageJpa btJpa;
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
                    mapOfPjm.put(String.valueOf(pjm.getUid()),pjm);
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

    public List<ProjectMahoushyojyoEntity> findAll(Object isSelled,Integer pageNum,Integer pageSize ){
        if (pageNum == null){
            pageNum = 1;
        }
        Sort sort =  Sort.by(Sort.Direction.ASC,"uid");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        if (isSelled==null){
            return btJpa.findAll(pageable).getContent();
        }else {
            Page<ProjectMahoushyojyoEntity> p =  btJpa.selectAllByIsSelled(isSelled.toString(),pageable);
            return p.getContent();
        }

    }

    public ProjectMahoushyojyoEntity findObjByUid(Integer uid){
        Optional<ProjectMahoushyojyoEntity> opt = btJpa.findById(uid);
        if (opt.isPresent()){
            return opt.get();
        }
        return null;
    }

    public Object findObjByNumber(String num){
        return btJpa.FindNumberByCode("%"+num+"%");
    }

    public Object addAccountNumber(ProjectMahoushyojyoEntity pje) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        pje.setNumber(uuid);//生成选号码
       return btJpa.save(pje);
    }

    public Object modifyAccountMumer(ProjectMahoushyojyoEntity pje){
            ProjectMahoushyojyoEntity pre = findObjByUid(pje.getUid());
            if (pre!=null){
                if (pje.getUser() != null) pre.setUser(pje.getUser());
                if (pje.getPassword() != null) pre.setPassword(pje.getPassword());
                if(pje.getBind_mailbox()!=null) pre.setBind_mailbox(pje.getBind_mailbox());
                if(pje.getMail_password()!=null) pre.setMail_password(pje.getMail_password());
                if (pje.getListOf4X() != null) pre.setListOf4X(pje.getListOf4X());
                if (pje.getListOfMemoryCrystalls() != null) pre.setListOfMemoryCrystalls(pje.getListOfMemoryCrystalls());
                if (pje.getPrice() != null) pre.setPrice(pje.getPrice());
                if (pje.getIsSelled()!=null) pre.setIsSelled(pje.getIsSelled());
                return btJpa.save(pre);
            }
            return null;
    }

    public String deleteAccountNumberByUid(int uid) {
        ProjectMahoushyojyoEntity pre = findObjByUid(uid);
        if(pre != null){
            btJpa.delete(pre);
            return "删除成功";
        }else {
            return "删除失败，无效的uid";
        }

    }

}
